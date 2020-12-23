package day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class JUnit5_ParameterizedTest {

    @ParameterizedTest
    @ValueSource(ints = {5,6,7,8,9})
    public void test1(int myNumber){         //testNG data provider
        System.out.println("myNumber = " + myNumber);
        //assert 5,6,7,..all less than 10
        assertTrue(myNumber<100);


    }

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv" , numLinesToSkip = 1)  //slash / in the beginning is important
    public void test2( String zip ){
        System.out.println("zip = " + zip);

        //sending request to zipcode endpoint here
        // api.zippopotam.us/us/{zipcode}

        given()
                .log().uri()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode",zip).
        when().get("us/{zipcode}").
        then().statusCode(200);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/country_zip.csv" , numLinesToSkip = 1)
    public void testCountryZipCodeCombo(String csvCountry, int csvZip){
        given()
                .log().uri()
            .baseUri("https://api.zippopotam.us")
            .pathParam("country",csvCountry)
            .pathParam("zipcode",csvZip).
        when().get("/{country}/{zipcode}").
        then()
                .statusCode(200);
    }




}
