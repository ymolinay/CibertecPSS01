package cucumber.usuario.stepDef;

import io.cucumber.java.en.Then;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import cucumber.UsuarioAPI;

public class GeneralStepDef {
    @Steps
    UsuarioAPI usuarioAPI;
    @Then("debería devolver el codigo {int}")
    public void deberiaRetornarCodigo(int statusCode) {
        SerenityRest.then().assertThat().statusCode(statusCode);
    }

}
