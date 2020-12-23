package day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static testBase.SpartanAdminTestBase.adminReqSpec;

public class Test_XML_Response {


    // get xml response for GET /spartans
    @DisplayName("GET /spartans get xml response")
    @Test
    public void testXML(){
        given()
                .spec(adminReqSpec)
                .accept(ContentType.XML).
                when()
                .get("/spartans").
                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
        ;
    }



}
