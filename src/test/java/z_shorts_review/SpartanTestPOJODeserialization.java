package z_shorts_review;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testBase.SpartanAdminTestBase;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanTestPOJODeserialization extends SpartanAdminTestBase {
 @Test
    public void testWithPOJO(){
     Response response = given()
             .auth().basic("admin","admin")
             .accept(ContentType.JSON)
             .pathParam("id",15)
             .when().get("/api/spartans/{id}");
    // response.prettyPrint();


     //how to convert json response to spartan class, basically this is where the magic is happening
     Spartan_POJO spartan1 = response.body().as(Spartan_POJO.class);
     System.out.println(spartan1.toString());

     assertThat(spartan1.getName(), is("Karleen"));
     assertThat(spartan1.getId(),is(15));
     assertThat(spartan1.getGender(),equalTo("Male"));
     assertThat(spartan1.getPhone(),is(8639769776L));

 }

 @Test
 public void jacksonExample(){
   //in this test we create Gson object to convert response to java object
  //and also use Spartan_pojo class instance to get the java object and convert it to json response
  //basically achieve serialization


 }


}
