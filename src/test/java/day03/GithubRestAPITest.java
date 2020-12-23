package day03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;
public class GithubRestAPITest {

    //create a test for testing github rest api user/user endpoint

    @DisplayName("Test Github Get / user/ {username} ")
    @Test
    public void testGitHUB(){

        //provide your username as path variable in given section of the chain
        given()
                .pathParam("username","Nurbiye").
        when()
                .get("https://api.github.com/users/{username}"). //when I try to send the request to this url
                                                   //{username}appears in give, just like variable
        then()
                .assertThat()
                .statusCode( is(200) )
                .contentType( ContentType.JSON)//.header("content-type","application/json; charset=utf-8")
                .header("server","GitHub.com")
                .body("login", is("Nurbiye"))
        ;
    }





}
