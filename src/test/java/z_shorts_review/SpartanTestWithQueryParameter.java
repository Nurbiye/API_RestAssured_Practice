package z_shorts_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestWithQueryParameter {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.87.179.158:8000" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }


    /*
    * Given accept type is json ,
    * and query parameter values are:  gender:Female
    *                                  nameContains:J
    * when user sends get request to api/spartans/search
    * Then response status code should be 200
    * And response content type :application/json
    * And "Female" should be in response payload
    * And"Janette" should be in response payload
    * */
    @Test
    public void QueryParam1(){
       Response response =
                given().accept(ContentType.JSON)
                .queryParam("gender","Female")
                .queryParam("nameContains","J").
        when().get("/api/spartans/search");
//         then().
//                assertThat().
//                statusCode(is(200))
//                .contentType(ContentType.JSON)
//                .body("gender",is("Female"))
//                .body("name",contains("Janette"))
//                .extract().response().prettyPrint()
        ;
        assertThat(response.statusCode(),is(200) );
        assertThat(response.contentType(),is("application/json"));
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }

        //query parameter is in map : key value format, we also can use map

@Test
    public void queryParamMapTest(){
    Map<String, Object> queryParamMap = new LinkedHashMap<>();
    queryParamMap.put("gender","Female");
    queryParamMap.put("nameContains","J");

    //send request
      Response response =  given().accept(ContentType.JSON)
            .queryParam(queryParamMap.toString()).
              when().get("/api/spartans/search");
    assertThat(response.statusCode(),is(200) );
    assertThat(response.contentType(),is("application/json"));
    assertTrue(response.body().asString().contains("Female"));
    assertTrue(response.body().asString().contains("Janette"));
    response.prettyPrint();




}



}
