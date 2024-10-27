package cucumber;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import cucumber.utils.Constantes;

public class UsuarioAPI {

    public static String GET_USER = Constantes.BASE_URL + "/public/v2/users";

    public static String PUT_USER = Constantes.BASE_URL + "/public/v2/users/{user_id}";

    @Step("Get user")
    public void obtenerUSuarios(String nombre, String email, String genero, String estado) {
        SerenityRest.given()
                .queryParam("name", nombre)
                .queryParam("email", email)
                .queryParam("gender", genero)
                .queryParam("status", estado);

    }

    @Step("Actualizar usuari con string json")
    public void actualizarUsuario(String json, String id) {
        SerenityRest.given()
                .header("Authorization", "Bearer " + Constantes.TOKEN)
                .pathParam("user_id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Actualizar usuario sin token")
    public void actualizarUsuarioSinToken(String json) {
        SerenityRest.given()
                .pathParam("user_id", "7475802")
                .contentType(ContentType.JSON)
                .body(json);
    }
}
