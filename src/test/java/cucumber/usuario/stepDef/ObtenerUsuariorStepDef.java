package cucumber.usuario.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import cucumber.UsuarioAPI;
import cucumber.utils.Constantes;

import java.io.File;

import static org.hamcrest.Matchers.*;


public class ObtenerUsuariorStepDef {
    @Steps
    UsuarioAPI usuarioAPI;

    @Given("el usuario sin parametros")
    public void usuarioSinParametros() {
        SerenityRest.given();
    }

    @When("se envia solicitud de usuario")
    public void enviarSolicitudUsuario() {
        SerenityRest.when().get(UsuarioAPI.GET_USER);
    }

    @And("validar el usuario de la lista de esquemas json")
    public void validarUsuarioJsonEsquema() {
        File jsonSchema = new File(Constantes.JSON_SCHEMA + "/ResponseUserSchemaValid.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Given("el usuario con genero {string}")
    public void usuarioConParametro(String genero) {
        usuarioAPI.obtenerUSuarios("", "", genero, "");
    }

    @And("validar el genero del usuario cual debe ser {string}")
    public void validarGeneroUsuario(String parameter) {
        SerenityRest.then()
                .assertThat()
                .body("gender", everyItem(hasToString(parameter)));
    }

    @Given("el usuario con nombre {string}")
    public void usuarioConNombre(String nombre) {
        usuarioAPI.obtenerUSuarios(nombre, "", "", "");
    }

    @And("validar el nombre de usuario debe contener {string}")
    public void validarNombreUsuarioDebeContener(String name) {
        SerenityRest.then()
                .assertThat()
                .body("name", everyItem(containsStringIgnoringCase(name)));
    }

    @Given("el usuario con estado {string}")
    public void usuarioConEstado(String estado) {
        usuarioAPI.obtenerUSuarios("", "", "", estado);
    }

    @And("validar el estado del usuario debe ser {string}")
    public void validarEstadoUsuario(String status) {
        SerenityRest.then()
                .assertThat()
                .body("status", everyItem(hasToString(status)));
    }

    @Given("el usuario con email {string}")
    public void usuarioConEmail(String email) {
        usuarioAPI.obtenerUSuarios("", email, "", "");
    }

    @And("validar el email del usuario debe contener {string}")
    public void validarEmailUsuariDebeContener(String email) {
        SerenityRest.then()
                .assertThat()
                .body("email", everyItem(containsStringIgnoringCase(email)));
    }

}
