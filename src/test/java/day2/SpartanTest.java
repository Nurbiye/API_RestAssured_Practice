package day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanTest {

    @DisplayName("Testing/api/spartan endpint")
       @Test
    public void testGetAllSpartan(){
        // baseURI and basePath is static fields of RestAssured Class
        // Since we static imported RestAssured, we can access all static field directly just like it's in our own class here
        // you can use static way as below
        //RestAssured.baseURI = "http://100.26.101.158:8000";
        // or you can directly use as below
        baseURI = "http://100.26.101.158:8000";
        //RestAssured.basePath = "/api" ;
        basePath = "/api" ;
        // baseURI + basePath + whatever you provided in http method like get post
        // for example :
        // get("/spartans") -->>  get(baseURI + basePath + "/spartans")
    }

    @DisplayName("Testing/api/spartan endpint XML Response")
    @Test
    public void testGetAllSpartanXML(){

        /*
        * given
        *      -- RequestSpecification
        *       used to provide additional information about the request
        *       base url base path
        *       header, query params, path variable, payload(body), authentication authorization
        *       logging , cookie
        * when
        *     --This is where you actually send the request with http method
        *     --like GET POST PUT DELETE ..  with the URL
        *     --we get Response object after sending the request
        * then
        *     --ValidatableResponse
        *     --Validate status code, header, payload, cookie
        *     --responseTime
        * */

        given()
                .header("accept","application/xml").
        when()
                .get("http://100.26.101.158:8000/api/spartans").
        then()
//   .assertThat() //this is not required, but can be added to make it obvious that this is where we start assertion
                .statusCode(200)
             //   .and() //this is not required at all, just for readability, optional
                .header("Content-Type","application/xml");


        //this will do same exact thing as above in slightly different way
        //since accept header

        given()
                .accept(ContentType.XML).
                when()
                .get("/spartans").
                then()
                .assertThat()
                .statusCode(  is(200)  )
                .and()
                .contentType(ContentType.XML) ;



    }



}
