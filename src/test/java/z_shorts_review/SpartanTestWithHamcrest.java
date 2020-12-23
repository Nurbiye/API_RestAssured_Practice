package z_shorts_review;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestWithHamcrest {


    //given()   when()  then().statusCode(is(200) )
                                     //"is"  is matchers here
                          //    .body("id", equals to(11) )
   //most of the time we used it:  in the response body
    //we also can use 4 variable inside the one body:
    //then()
    // .body("id",equal to(15), "name", equal to("meta"), "gender",equal to("Male") );

    //hamcrest matchers:
    //(matchers)equal to  == is
    //hasItem
    //getList   --> THERE R LOTS OF METHODS IN Hamcrest matchers library





}
