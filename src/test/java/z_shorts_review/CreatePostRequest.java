package z_shorts_review;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testBase.SpartanAdminTestBase;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreatePostRequest extends SpartanAdminTestBase {

    @Test
    /*Given accept and content type is Json
    and request Json body is:{
    "gender":"Male",
    "name":"Mike",
    "phone":8877445596
    }
    when user sends post request to "/spartans/",
    then status code 201, content type should be "application/json"
    and json payload response should contain:
    "A Spartan is Born!" message, and same data what is posted
    * */
    public void postWithString(){
         given().auth().basic("admin","admin").accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"gender\":\"Male\",\n" +
                        "    \"name\":\"Mike\",\n" +
                        "    \"phone\":8877445596\n" +
                        "    }").
                when().post("/spartans").
                then().assertThat().statusCode(201).
                contentType("application/json").
                 body("name", is("Mike")).
                 body("gender",is("Male")).
                 body(contains("A Spartan is Born!"));

    }
@Test
    public void test2(){
    Map<String, Object> mapSpartan = new LinkedHashMap<>();
    mapSpartan.put("name","Mike");
    mapSpartan.put("gender","Male");
    mapSpartan.put("phone",8877445596L);

    Response response =  given().auth().basic("admin","admin").accept(ContentType.JSON)
            .and().contentType(ContentType.JSON)
            .body(mapSpartan).when().post("/spartans");
//    assertThat(response.


}



//and also can add external file:
    //  File externalFile = new File("(external file pathName");
   // given().auth.basic("").body(externalFile)
    //---> other part is similar

}
