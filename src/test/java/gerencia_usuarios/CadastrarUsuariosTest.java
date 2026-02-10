package gerencia_usuarios;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseApiTest;
import utils.PayloadLoader;
import utils.SchemaLoader;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class CadastrarUsuariosTest extends BaseApiTest {



    @Test
    @DisplayName("Validar que é possivel cadastrar um usuario com sucesso")
    void cadastrar_usuario_com_sucesso() {

        String payloadFile = "usuario_post.json";
        String schemaFile = "usuario_post_201.schema.json";

        String jsonBody = PayloadLoader.load(payloadFile)
                .replace("{{email}}", EmailUtils.emailDinamico()); // email dinamico, garante que gera uma massa mova a cada teste

        String jsonSchema = SchemaLoader.load(schemaFile);

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post("/usuarios")
                .then()
                .log().all()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .body(matchesJsonSchema(jsonSchema));
    }




    }






