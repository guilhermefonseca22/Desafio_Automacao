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
    @DisplayName("Validar que é possivel alterar um usuario com sucesso")
    void alterar_usuario_com_sucesso() {


        //  Gerando massa
        String userId = criarUsuario();

        // Alterando usuário criado
        String payloadAlteracao = PayloadLoader.load("usuario_post.json")
                .replace("{{email}}", EmailUtils.emailDinamico());

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
