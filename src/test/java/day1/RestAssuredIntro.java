package day1;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredIntro {

    @DisplayName("Spartan Get /Api/hello EndPoint Test")
    @Test
    public void TestHello(){
        //this is the public IP :   http://100.26.101.158:8000
        //mine is: http://54.87.179.158:8000 add endpoint : /api/hello

        //import io.restassured.response.Response;
        Response response = get("http://54.87.179.158:8000/api/hello");
        //get status code out of this response object
        //  response.statusCode();
        System.out.println("status code is: " + response.statusCode() );  //getStatusCode  also works

        //assert the status code is 200
        assertThat(response.statusCode() , is(200));
           //   actual                            expected

        //how to pretty print entire response body
        response.prettyPrint(); //prettyPrint --> print and return the payload(body) as String
        String payload = response.prettyPrint();  //--> response.body().prettyPrint()
                                                 //--> response.body().asString();

        //assertThat the body is Hello from spartan
        assertThat(payload, is("Hello from Sparta"));


        //get the header called content type from the response  --> 3 ways of get the header
        System.out.println( response.getHeader("Content-Type"));
        System.out.println( response.getContentType() );
        System.out.println( response.contentType() );


        //assertThat Content_Type is text/plain;charset=UTF-8
        assertThat(response.contentType(), is("text/plain;charset=UTF-8"));

        //assert that content type startWith text
        assertThat(response.contentType(), startsWith("text") );

        //easy way to work with content-type without typing much
        assertThat(response.contentType(), startsWith(ContentType.TEXT.toString()));   //ContentType--is enum
        assertThat(response.contentType(), is( not(ContentType.JSON)) );
        //we can use ContentType Enum from RestAssured to easily get main part content-type
        //ContentType.TEXT --> text/plain as Enum
        //startsWith accept a String
    }



}
