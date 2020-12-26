package z_shorts_review;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import testBase.SpartanAdminTestBase;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanPut extends SpartanAdminTestBase {

    @Test
    public void PutRequest() {
        /*
         * different ways to send json body
         * -String (general body,copy paste general response from postman )
         * -Using Collection (map)
         * -POJO
         * */

        //using map
        Map<String, Object> putMap = new LinkedHashMap<>();
        putMap.put("name", "MikePut");
        putMap.put("gender", "Male");
        putMap.put("phone", 8920373286L);

        //we gonna send request body with updated value and content type header
        given().auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                pathParam("id", 166).
                body(putMap).
                when().put("/spartans/{id}").then().assertThat().statusCode(204);

    }

        //what is the difference between patch and put? interview question
        @Test
    public void PatchRequest(){
            //using map
            Map<String, Object> patchMap = new LinkedHashMap<>();
            patchMap.put("name","MikePatch");

            //we gonna send request body with updated value and content type header
            given().auth().basic("admin","admin").
                    contentType(ContentType.JSON).
                    pathParam("id",166).
                    body(patchMap).
                    when().patch("/spartans/{id}").then().assertThat().statusCode(204);


        }



}
