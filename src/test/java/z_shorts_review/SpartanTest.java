package z_shorts_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   //in order (numerical)
//@TestMethodOrder(MethodOrderer.Random.class)          //randomly order
//@TestMethodOrder(MethodOrderer.MethodName.class)       //by name order
//@TestMethodOrder(MethodOrderer.DisplayName.class)       //by name display name

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   //in order (numerical)
public class SpartanTest {

    String spartanBaseURL = "http://54.87.179.158:8000";

    @Order(1)
    @Test
            public void simpleTest() {
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");

        System.out.println(response.body().prettyPeek());   //print whole response body(as a response)
        // System.out.println(response.body().asString() );  //prints in one line
        //System.out.println(response.body().prettyPrint() );   //print only the body as a String
        System.out.println(response.statusCode());


    }



        /*
        * when user send Get request to api/spartans end point
        *then status code must be 200
        * and body should contains Allen
        * */
    @Order(2)
        @Test
         public void verificationTest(){
          Response response = RestAssured.get(spartanBaseURL + "/api/spartans" );
            Assertions.assertEquals(200, response.statusCode() );
            Assertions.assertTrue( response.body().asString().contains("Allen") );

        }

        @Order(3)
        @Test
        public void BDDTest(){
        /*
        * Given accept type is json
        * when user sends a get request
        * Then response status code is 200
        * And response body should be json format
        * */
            Response response =
                   RestAssured.given().accept(ContentType.JSON).
                           when().get(spartanBaseURL + "/api/spartans");

          Assertions.assertEquals(200, response.statusCode());
          Assertions.assertEquals("application/json",response.contentType() );
  //we use Assertion instead of using "then" for the verification





        }



    }

