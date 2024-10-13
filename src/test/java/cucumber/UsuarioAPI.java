package cucumber;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import cucumber.utils.Constantes;

public class UsuarioAPI {
    public static String GET_USER = Constantes.BASE_URL + "/public/v2/users";

    @Step("Get user")
    public void obtenerUSuarios(String nombre, String email, String genero, String estado) {
        SerenityRest.given()
                .queryParam("name", nombre)
                .queryParam("email", email)
                .queryParam("gender", genero)
                .queryParam("status", estado);

    }
}
