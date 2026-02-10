package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AuthManager;
import utils.BaseApiTest;
import utils.SchemaLoader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ListarUsuariosTest extends BaseApiTest {

    @Test
    @DisplayName("Validar que é possivel fazer uma listagem de todos os usuarios cadastrados")
    void validar_lista_todos_usuarios() {

        String schemaFile = "listar_usuarios.schema.json";
        String jsonSchema = SchemaLoader.load(schemaFile);

        Response response = given()
                .header("Authorization", AuthManager.getToken())
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/usuarios") //
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchema(jsonSchema))
                .extract().response();

        //lista usuarios e valida se existe pelo menos 1 usaurio cadastrado e valida a quantidade de usuarios
        List<Map<String, Object>> usuarios = response.jsonPath().getList("usuarios");

        assertThat("Deve haver pelo menos um usuário", usuarios.size(), greaterThan(0));

        for (Map<String, Object> usuario : usuarios) {
            assertThat("Nome não pode ser nulo", usuario.get("nome"), notNullValue());
            assertThat("Email deve conter @", usuario.get("email").toString(), containsString("@"));
            assertThat("Administrador deve ser 'true' ou 'false'",
                    usuario.get("administrador").toString(),
                    anyOf(equalTo("true"), equalTo("false")));
            assertThat("_id não pode ser vazio", usuario.get("_id").toString(), not(emptyString()));
        }
    }


    @Test
    @DisplayName("Validar que é possivel fazer uma listagem do primeiro usuario cadastrado")
    void validar_primeiro_usuario_cadastrado() {

        String schemaFile = "listar_usuarios.schema.json";
        String jsonSchema = SchemaLoader.load(schemaFile);

        given()
                .header("Authorization", AuthManager.getToken()) // token de admin
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/usuarios") // endpoint correto
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchema(jsonSchema))
                .body("quantidade", greaterThan(0))
                .body("usuarios[0].nome", notNullValue())
                .body("usuarios[0].email", containsString("@"))
                .body("usuarios[0].administrador", anyOf(equalTo("true"), equalTo("false")))
                .body("usuarios[0]._id", not(emptyString()));
    }
}
