package gerencia_usuarios;

import io.restassured.http.ContentType;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseApiTest;
import utils.PayloadLoader;

import static io.restassured.RestAssured.given;

public class AlterarUsuarioTest extends BaseApiTest {

    @Test
    @DisplayName("Validar que é possível alterar um usuário com sucesso")
    void alterar_usuario_com_sucesso() {

        // Criando massa
        String userId = criarUsuario();

        //Alterando payload
        String payloadAlteracao = PayloadLoader.load("usuario_post.json")
                .replace("{{email}}", emailDinamico());

        // Executando PUT para alterar usuário
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(payloadAlteracao)
                .when()
                .put("/usuarios/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", IsEqual.equalTo("Registro alterado com sucesso"));
    }
}
