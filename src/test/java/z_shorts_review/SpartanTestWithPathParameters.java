package z_shorts_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;


public class SpartanTestWithPathParameters {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.87.179.158:8000";
    }


    /*Given accept type is Json
    and ID parameter value is 18
    when user send get request to /api/spartans/{id}
    then response status code should be 200
    and "Allen" should be in response payload
    */
   @Test
public void pathTest1(){
 Response response = RestAssured.given().accept(ContentType.JSON)
         .and().pathParam("id",18)
         .when().get("/api/spartans/{id}");
 //verify status code,content type, Allen exists
       Assertions.assertEquals(200, response.statusCode() );
       Assertions.assertEquals("application/json",response.contentType() );
       Assertions.assertTrue(response.body().asString().contains("Allen"));

   }

   /*Negative Scenario:
   Given accept type is Json
    and ID parameter value is 500
    when user send get request to /api/spartans/{id}
    then response status code should be 404
    the response content type: application/json
    and "Spartan Not Found" message should be in response payload
    */
    @DisplayName("getSpartanNegativeTest")
@Test
    public void negativeTestPath(){
                given().accept(ContentType.JSON)
                .pathParam("id",500).
         when().get("/api/spartans/{id}").
        then().assertThat()
               .statusCode( is(404) )
                .contentType(ContentType.JSON)
                .body("error",is("Not Found"))
                .extract().response().prettyPrint()
                ;
//        Assertions.assertEquals(404, response.statusCode());
//        Assertions.assertEquals("application/json",response.contentType() );
//        Assertions.assertTrue(response.body().asString().contains("Spartan Not Found") );

    }



}
