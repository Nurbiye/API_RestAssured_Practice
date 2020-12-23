package z_shorts_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanTestsWithPathMethod {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.87.179.158:8000" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }



    /*
    Given accept type is json
    And path parameter Id id 10
    when user sends get request to "api/spartans/{id}
    then status code is 200, content type: application/json
    response payload value match as below:
    id:10  , name:"Lorenza", gender:"Female" phone: 3312820936
     */
    @Test
    public void test1(){

        given().accept(ContentType.JSON).
                pathParam("id",10).
        when().get("/api/spartans/{id}").
        then()
              .assertThat().statusCode(is(200)).
                contentType(ContentType.JSON)
                .body("id",is(10))
                .body("name",is("Lorenza"))
                .body("gender",is("Female"))
                .body("phone",is(3312820936L))
                .extract().response().prettyPrint();
//we got the specific one spartan's information,
// what if we have a bunch of spartans, and how we can get one specific spartan?let's look at below test
    }
@Test
    public void test2(){
    Response response = get("/api/spartans");//this is a regular get method like in postman

    //extract first id
    int firstId = response.path("id[0]"); //it is an Array of spartans,get the first id
    System.out.println("firstId = " + firstId);

    //extract the first name and last name
    String firstName = response.path("name[0]");
    String lastName = response.path("name[-1]");
    System.out.println("firstName = " + firstName);
    System.out.println("lastName = " + lastName);

    //extract all the first names and print them
    List<String> names = response.path("name");
   // List<Object> names = response.path("name");
        System.out.println(names);
        System.out.println("names = " + names.size() );

      //all the phone numbers
//    List<Long> phoneNumbers = response.path("phone");
//    for (Long phoneNumber : phoneNumbers) {
//        System.out.println("phoneNumber = " + phoneNumber);
//    }





}





}
