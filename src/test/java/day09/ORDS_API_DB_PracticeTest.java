package day09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import testBase.HR_ORDS_TestBase;
import utility.DB_Utility;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDS_API_DB_PracticeTest extends HR_ORDS_TestBase {

    @DisplayName("GET /countries/{country_id} Compare Resut with DB")
    @Test
    public void testResponseMatchDatabaseData(){
        String myCountry_id = "AR";
        //send request to /countries/{country_id} for AR
        //save the result as Country POJO
        Country arPOJO = given()
                .pathParam("country_id",myCountry_id).
         when()
                .get("/countries/{country_id}").prettyPeek()
                .as(Country.class);

        //shorter way of above code
       // Country arPOJO1 = get("/countries/{country_id}",myCountry_id ).as(Country.class);
        System.out.println("arPOJO = " + arPOJO);


        //above part is about API, now i want to write query to get Argentina

        String query = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID = '" +myCountry_id + "' ";
        System.out.println("query = " + query);
        DB_Utility.runQuery(query);
        Map<String, String> dbResultMap = DB_Utility.getRowMap(1);

        //now start validating the actual response to expected result from database
        assertThat(arPOJO.getCountry_id(), is(dbResultMap.get("COUNTRY_ID") ));
        assertThat(arPOJO.getCountry_name(),is(dbResultMap.get("COUNTRY_NAME") ) );

        //save region_id from map as number
        int expectedRegionID = Integer.parseInt(dbResultMap.get("COUNTRY_ID"));
        assertThat(arPOJO.getCountry_id(), equalTo( expectedRegionID) );


    }

    @DisplayName("GET /countries Capture All CountryID and Compare Result with DB")
    @Test
    public void testResponseAllCountryIDsMatchDatabaseData(){
        List<String> allCountriesIds = get("/countries").jsonPath().getList("items.country_id");
        allCountriesIds.forEach(System.out::println);


        DB_Utility.runQuery("SELECT * FROM COUNTRIES");
        List<String> expectedListFromDB = DB_Utility.getColumnDataAsList("COUNTRY_ID");
        expectedListFromDB.forEach(System.out::println);

        //assert both list has same information
        assertThat(allCountriesIds, equalTo( expectedListFromDB ));


    }



}
