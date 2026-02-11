package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public abstract class BaseApiTest {

    protected static final String BASE_URI = "https://serverest.dev";

    @BeforeAll
    static void setupBaseApi() {
        RestAssured.baseURI = BASE_URI;
    }

    // Permite setar basePath em cada classe de teste
    protected static void setBasePath(String path) {
        RestAssured.basePath = path;
    }

    // Tornando a inner class estática para permitir métodos estáticos
    public static class EmailUtils {

        public static String emailDinamico() {
            return "user_" + UUID.randomUUID() + "@qa.com.br";
        }
    }

    public static String criarUsuario() {
        // Garante uma criação de massa para o teste de delete utilizar

        String payloadFile = "usuario_post.json";

        String jsonBody = PayloadLoader.load(payloadFile)
                .replace("{{email}}", EmailUtils.emailDinamico());

        return given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract()
                .path("_id");
    }

}
