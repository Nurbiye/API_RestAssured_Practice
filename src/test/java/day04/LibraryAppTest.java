package day04;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
public class LibraryAppTest {

    private static String myToken;


    @BeforeAll
    public static void setUp(){
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }



    @DisplayName("Testing /login Endpoint")
    @Test
    public void testLogin(){
        /*
        * Librarian1  username	librarian69@library
        * Librarian1 password		KNPXrm3S
         */

  myToken=
        given()
                .log().all()
                .contentType( ContentType.URLENC)
                .formParam("email","librarian69@library")
                .formParam("password","KNPXrm3S").
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
                .body("token",is( not( emptyString() ) ) )
                .extract()    //extract the information by providing json path and get string.
                 .jsonPath()
                .getString("token")
        ;

        System.out.println("myToken = \n" + myToken);

        //how to extract the some data out of response object
        //after doing the validation in the section
        //without breaking the chain -->use extract method that return

    }



    @DisplayName("Testing GET /dashboard_stats Endpoint")
    @Test
    public void ztestDashboard_stats(){
        //this is how we provide header .header()
        given()
                .header("x-library-token",myToken)
        . when()
                .get("/dashboard_stats").
       then()
                .log().all()
                .assertThat()
                .statusCode( is(200))
                .contentType(ContentType.JSON)
                ;


    }


    // create a utility class LibraryUtility
    // create a static method called getToken(environment, username, password)



}