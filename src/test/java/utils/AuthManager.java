package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthManager {

    private static String token;

    // Retorna token do administrador, já com "Bearer "
    public static String getToken() {
        if (token == null) {
            token = generateAdminToken();
        }
        return token;
    }

    private static String generateAdminToken() {
        // Usuário admin padrão do ServeRest
        String adminPayload = "{\n" +
                "  \"email\": \"fulano@qa.com\",\n" +
                "  \"password\": \"teste\"\n" +
                "}";

        Response response = given()
                .baseUri("https://serverest.dev")
                .contentType(ContentType.JSON)
                .body(adminPayload)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Já vem com "Bearer "
        return response.jsonPath().getString("authorization");
    }
}
