package day04;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class SpartanAddingTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.87.179.158:8000";
        basePath = "/api";

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    //use basic Auth
    @DisplayName("Testing Get/ api / spartans with Basic auth")
    @Test
    public void testAllSpartansWithBasicAuth(){
        given()
                .log().all()
                .auth().basic("admin","admin").
        when()
                .get("/spartans").
        then()
        .log().all()
        .statusCode( is(200));

    }

    @DisplayName("Add 1 Data with Raw Json String POST /api/spartans")
    @Test
    public void testAddOneData(){  //this is a POST request , we need to provide body

        String newSpartanStr =  "    {\n" +
                                "        \"name\": \"Gulbadan\",\n" +
                                "        \"gender\": \"Male\",\n" +
                                "        \"phone\": 9876543210\n" +
                                "    }" ;
        System.out.println(newSpartanStr);

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)  //tell the server the type of our body type
                .body(newSpartanStr).
        when()
                .post("/spartans").
         then()
                .log().all()
                .assertThat()
                .statusCode( is(201) )
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!") )
                .body("data.name", is("Gulbadan") )
                .body("data.gender",is("Male") )
                .body("data.phone",is(9876543210L));


    }



    @DisplayName("Add I data with Map Object POST/ api/spartans")
    @Test
    public void testAddOneDataWithMapAsBody() {
        Map<String, Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("name", "Nurhan");
        payloadMap.put("gender", "Female");
        payloadMap.put("phone", 8984374383L);
        System.out.println("payloadMap = " + payloadMap);

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .body(payloadMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Nurhan"))
                .body("data.gender", is("Female"))
                .body("data.phone", is(8984374383L));


        //use jackson.databind library to dependency , we can turn the map object into Json.-->serialization and de-serialization
        //there is also gson same role like jackson databind
    }





        @DisplayName("Add 1 Data with External json file POST /api/spartans")
        @Test
        public void testAddOneDataWithJsonFileAsBody(){



            // Create a file called singleSpartan.json right under root directory
            // with below content
        /*
        {
            "name": "Olivia",
            "gender": "Female",
            "phone": 6549873210
        }
        add below code to point File object to this singleSpartan.json
         */
            File externalJson = new File("singleSpartan.json");

            given()
                    .auth().basic("admin","admin")
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body( externalJson ).
             when()
                    .post("/spartans").
             then()
                    .log().all()
                    .statusCode( is(201) )
                    .contentType(ContentType.JSON)
                    .body("success" , is("A Spartan is Born!") )
                    .body("data.name" ,  is("Olivia")  )
                    .body("data.gender" ,  is("Female")  )
                    .body("data.phone" ,  is(6549873210L)  )
            ;
        }






    }
