package gerencia_usuarios;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseApiTest;
import utils.PayloadLoader;
import utils.SchemaLoader;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class LoginTest extends BaseApiTest
{

    @BeforeAll
    static void setup() {
        setBasePath("/login"); // define o basePath específico para esse teste
    }

    @Test
    @DisplayName("Validar que é possivel efetuar um Login com sucesso")
    void login_com_sucesso() {

        String payloadFile = "login.json";
        String schemaFile = "login.schema.json";

        String jsonBody = PayloadLoader.load(payloadFile);
        String jsonSchema = SchemaLoader.load(schemaFile);

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Login realizado com sucesso"))  // valida mensagem de login efetuado com sucesso
                .body("authorization", startsWith("Bearer ")) // valida a autorização
                .body(matchesJsonSchema(jsonSchema));
    }


    @Test
    @DisplayName("Validar que ao informar login incorreto não é possivel efetuar o  efetuar um Login com sucesso")
    void login_invalido() {

        String payloadFile = "login_invalido.json";
        String schemaFile = "login_invalido.schema.json";

        String jsonBody = PayloadLoader.load(payloadFile);
        String jsonSchema = SchemaLoader.load(schemaFile);

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(401)
                .body("message", equalTo("Email e/ou senha inválidos"))
                .body(matchesJsonSchema(jsonSchema));

    }
}
