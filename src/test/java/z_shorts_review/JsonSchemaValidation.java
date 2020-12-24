package z_shorts_review;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import testBase.SpartanAdminTestBase;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidation extends SpartanAdminTestBase {

    @Test
    public void testSchema(){
        given().auth().basic("admin","admin").
                accept(ContentType.JSON)
                .pathParam("id",6)
                .when().get("/spartans/{id}").
                then().assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("spartanSchema.json"));


    }

}
