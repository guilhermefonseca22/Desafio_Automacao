package gerencia_usuarios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseApiTest;
import utils.SchemaLoader;



import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringContains.containsString;



public class ExcluirUsuarioTest extends BaseApiTest {

    @Test
    @DisplayName("Validar que é possivel excluir um usuario com sucesso")
    void excluir_usuario_com_sucesso() {

        String schemaFile = "usuario_delete.schema.json";
        String jsonSchema = SchemaLoader.load(schemaFile);
       String usuarioId = criarUsuario();

        given()
                .log().all()
                .when()
                .delete("/usuarios/" + usuarioId)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", containsString("Registro excluído"))
                .body(matchesJsonSchema(jsonSchema));
    }

    @Test
    @DisplayName("Validar que nao é possivel excluir um usuario inexistente")
    void nao_deve_excluir_usuario_inexistente() {

        String schemaFile = "usuario_inexistente.schema.json";
        String jsonSchema = SchemaLoader.load(schemaFile);

        given()
                .log().all()
                .when()
                .delete("/usuarios/0000000000000000")
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Nenhum registro excluído"))
                .body(matchesJsonSchema(jsonSchema));
    }



}
