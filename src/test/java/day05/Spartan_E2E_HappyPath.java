package day05;

import utility.ConfigurationReader;
import org.junit.jupiter.api.*;

import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
public class Spartan_E2E_HappyPath {

    private static Map<String, Object> payloadMap;
    private static int newID;

    //crud operation -- create--read--update--delete


    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("1. Testing POST / api / spartans Endpoint")
    @Test
    public void testAddData(){

    }







}