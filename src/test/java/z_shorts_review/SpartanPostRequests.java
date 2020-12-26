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

public class SpartanPostRequests extends SpartanAdminTestBase {

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




    //now we learn how to send the post request with custom POJO class

    @Test
    public void POSTwithPOJO(){
        //create Spartan object and use as a body for post request(I create Spartan_POJO object)
        Spartan_POJO sp = new Spartan_POJO();
        sp.setName("Mike");
        sp.setGender("Male");
        sp.setPhone(2328495486L);

       // Response response =
         given().
                 auth().basic("admin","admin")
                 .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(sp)
         .when().post("/spartans")
         .then().
                 log().all().
                assertThat().
                statusCode(is(201))
                .contentType(is("application/json") )
                // .extract().response().prettyPrint()      //-->we can comment this out, since we have log in before
         ;

       //ABOVE WE USE SPARTAN OBJECT TO CREATE THE JSON RESPONSE, -->SERIALIZATION


        System.out.println("========================GET REQUEST=====================");
     Response response2  = given().auth().basic("admin","admin")
             .accept(ContentType.JSON)
             .pathParam("id",166)
             .when().get("/spartans/{id}");
             //.then().assertThat().log().body().statusCode(200);  //--> get information from API
        //now let's save this json object in another spartan Response object
        //---json to  java custom object --> deserialization
        Spartan_POJO spResponse = response2.as(Spartan_POJO.class);

        System.out.println(spResponse.toString());



    }







}
