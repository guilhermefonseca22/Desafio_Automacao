package gerencia_usuarios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseApiTest;
import utils.SchemaLoader;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class BuscarUsuarioIdTest extends BaseApiTest {

    @Test
    @DisplayName("Validar que é possivel buscar um usuario pelo ID")
    void buscar_usuario_por_id_com_sucesso() {

        String schemaFile = "usuario_get_id.schema.json";
        String jsonSchema = SchemaLoader.load(schemaFile);

        String usuarioId = "0S4k4s2xyMLkFwlH";

        given()
                .log().all()
                .when()
                .get("/usuarios/" + usuarioId)// Busca pelo usuario informado na variavel
                .then()
                .log().all()
                .statusCode(200)
                .body("nome", instanceOf(String.class))
                .body("email", instanceOf(String.class))
                .body("_id", equalTo(usuarioId))
                .body(matchesJsonSchema(jsonSchema));
    }

    @Test
    @DisplayName("Validar que ao buscar por um ID inexistente é apresentada a mensagem Usuário não encontrado ")
    void nao_encontra_usuario_inexistente() {

        String schemaFile = "usuario_inexistente.schema.json";
        String jsonSchema = SchemaLoader.load(schemaFile);
        String usuarioId_inexistente = "0000000000000000";

        given()
                .log().all()
                .when()
                .get("/usuarios/" + usuarioId_inexistente)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"))
                .body(matchesJsonSchema(jsonSchema));
    }

    @Test
    void nao_deve_buscar_usuario_inexistente() {

        String schemaFile = "usuario_inexistente_ID.schema.json";
        String jsonSchema = SchemaLoader.load(schemaFile);

        given()
                .log().all()
                .when()
                .get("/usuarios/0000000000000000") // ID numérico inexistente
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"))
                .body(matchesJsonSchema(jsonSchema));
    }



}
