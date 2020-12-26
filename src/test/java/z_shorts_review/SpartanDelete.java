package z_shorts_review;

import org.junit.jupiter.api.Test;
import testBase.SpartanAdminTestBase;

import static io.restassured.RestAssured.*;

public class SpartanDelete extends SpartanAdminTestBase {
    @Test
    public void deleteRequest(){
        given().auth().basic("admin","admin").
                pathParam("id",103).when().delete("/spartans/{id}")
                .then().assertThat().statusCode(204);

        //verify part:
        given().auth().basic("admin","admin")
                .pathParam("id",103).when().delete("/sparatns/{id}").
                then().statusCode(404);
    }




}
