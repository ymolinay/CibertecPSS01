package cucumber.usuario.stepDef;

import com.google.gson.Gson;
import cucumber.UsuarioAPI;
import cucumber.utils.Constantes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ActualizarUsuarioStepDef {

    @Steps
    UsuarioAPI usuarioAPI;

    @Given("actualizar usuario con ID valido and con request body valido")
    public void updateUserWithValidIDAndRequestBody() {
        Response response = SerenityRest.given().get(usuarioAPI.GET_USER);
        JsonPath jsonPathEvalvator = response.jsonPath();
        Gson gson = new Gson();
        String json = gson.toJson(jsonPathEvalvator.getList("").get(0));
        usuarioAPI.actualizarUsuario(json, jsonPathEvalvator.get("[0].id").toString());
    }

    @When("enviar solicitud actualizar usuario")
    public void enviarSolicituActualizarUsuario() {
        SerenityRest.when().put(usuarioAPI.PUT_USER);
    }

    @And("validar usuario json schema")
    public void validarJsonSchemaActualizarUsuario() {
        File jsonSchema = new File(Constantes.JSON_SCHEMA + "/ResponseUpdateUserSchemaValid.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Given("actualizar usuario con {string} como ID y {string} request body")
    public void actualizarUsuarioConIDYRequestBody(String id, String description) throws IOException {
        File json = new File(Constantes.JSON_REQUEST + "/ValidRequestUpdateUser.json");
        if (description.equals("invalid")) {
            json = new File(Constantes.JSON_REQUEST + "/InvalidRequestUpdateUser.json");
        }
        usuarioAPI.actualizarUsuario(leerContenidoJson(json), id);

    }

    @Given("actualizar usuario sin token")
    public void actualizarUsuarioSinToken() throws IOException {
        File json = new File(Constantes.JSON_REQUEST + "/ValidRequestUpdateUser.json");
        usuarioAPI.actualizarUsuarioSinToken(leerContenidoJson(json));

    }

    private String leerContenidoJson(File archivoJson) throws IOException {
        Path path = Paths.get(archivoJson.getPath());
        return new String(Files.readAllBytes(path));
    }

}
