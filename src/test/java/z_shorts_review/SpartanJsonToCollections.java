package z_shorts_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanJsonToCollections {

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
    And path parameter Id id 11
    when user sends get request to "api/spartans/{id}
    then status code is 200, and content type: application/json
    and response payload value match as below:
    id:11  , name:"Nona", gender:"Female" phone: 7959094216
     */

    @Test
    public void test1(){
        Response response =  given().accept(ContentType.JSON)
                .pathParam("id",11).
        when().get("/api/spartans/{id}");

        //convert Json response to Java map
        Map<String,Object> spartanMap = response.body().as(Map.class);
        System.out.println(spartanMap.get("name"));
        System.out.println(spartanMap.get("id"));

        assertThat(spartanMap.get("name"),is("Nona" ) );

    }


    //what if we get multiple data, not only one ...-->we can use a list of Map
   @Test
    public void test2(){
     Response response = given().accept(ContentType.JSON)
                         .when().get("/api/spartans");
     //response.prettyPrint();

     //convert full json body to list of map
       List< Map<String,Object> > listOfSpartans = response.body().as(List.class);
       System.out.println(listOfSpartans);  //it will print all the map data in one line
       //print all the data of first spartan
       System.out.println(listOfSpartans.get(0));
       Map<String, Object> firstSpartan = listOfSpartans.get(0);
       System.out.println(firstSpartan.get("name"));

       //now let's print them in separate line by giving order number
       int counter = 1; //list starts from 1
       for (Map<String, Object> listOfSpartan : listOfSpartans) {
           System.out.println( counter +"-spartan: " + listOfSpartan);
           counter++;
       }




   }



}
