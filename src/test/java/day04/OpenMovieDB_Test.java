package day04;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class OpenMovieDB_Test {

    //http://www.omdbapi.com/?t=The Orville&api=379ad943

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test Search Movie or OpenMovieDB Test")
    @Test
    public void testMovie(){

        given()
                .queryParam("apiKey","379ad943" ) //5b5d0fe8
                .queryParam("t","The Orville").
                when()
                .get().prettyPeek().  // our request URL is already complete , do not need to add anything here
                then()
                .statusCode( is(200) )
                .contentType( ContentType.JSON )
                .body("Title" , is("The Orville"))
                .body("Ratings[0].Source" , is("Internet Movie Database") )

        ;

    }


    @DisplayName("Getting the log of request and response")
    @Test
    public void testSendingRequestAndGetTheLog(){
        given()
                .queryParam("apiKey","379ad943")
                .queryParam("t","John Wick")
                //logging the request should be in given section  -->purpose of log is we can debug, we can know what is happening
                .log().all().
         when()
                .get().
         then()
                //logging the response should be in then section
//                .log().all()
//                .log().status()
//                .log().body()
                .log().ifValidationFails()
                .statusCode( is(200) )
                .body("Plot", containsString("ex-hit-man") )
                //second Ratings source is Rotten Tomatoes
                .body("Ratings[1].Source",is("Rotten Tomatoes"));
    }

}
