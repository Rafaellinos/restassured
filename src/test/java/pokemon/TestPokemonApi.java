package pokemon;

import org.junit.jupiter.api.BeforeEach;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



import static io.restassured.RestAssured.get;

public class TestPokemonApi {

    @BeforeAll
    public static void setBaseURI() {
        RestAssured.baseURI = "http://pokeapi.co";
        RestAssured.basePath = "/api/v2";
        get("/pokemon").then().statusCode(200);
    }

    @Test
    public void testGetGyarados() {
        String pokemonName = get("/pokemon/gyarados").then().assertThat().statusCode(200).extract().path("name");
        assertTrue(pokemonName.equalsIgnoreCase("gyarados"));
    }

    @Test
    public void testPokemonNotFound() {
        get("/pokemon/doesNotExist").then().assertThat().statusCode(404);
    }
}
