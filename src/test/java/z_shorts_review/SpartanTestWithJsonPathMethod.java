package z_shorts_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.MatcherAssert.assertThat;


public class SpartanTestWithJsonPathMethod {

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
    public void jsonPathTest(){
        Response response=
                   given().accept(ContentType.JSON)
                          .pathParam("id",11).
                   when().get("/api/spartans/{id}");
//                   then().statusCode(is(200))
//                         .contentType(ContentType.JSON)
//                         .body("id",is(11))
//                         .body("name",is("Nona"));
        //i can continue in this way..
        // but we want to compare path() and jsonPath()
        assertThat(response.statusCode(),is(200));
        assertThat(response.contentType(), is("application/json"));


        //how to read value with the path() method
        int id = response.path("id");
        System.out.println("id = " + id);

        //how to read value with jsonPath?
        JsonPath jp = response.jsonPath();
        int id1 = jp.getInt("id");
        String name = jp.getString("name");
        String gender = jp.getString("gender");
        long phone = jp.getLong("phone");
        System.out.println("id1 = " + id1);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
        //verify json payload with json path
        assertThat(id1,is(11));
        assertThat(name,is("Nona"));
        assertThat(gender,is("Female") );
        assertThat(phone, is(7959094216L));




    }






}
