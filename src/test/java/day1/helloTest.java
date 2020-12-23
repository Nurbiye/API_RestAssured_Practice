package day1;

import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Day1 Hello Test")  //use this annotation to create a custom name
public class helloTest {
    //junit5 Annotations
    //@BeforeAll  @AfterAll  @BeforeEach  @AfterEach

    @BeforeAll
    public static void setUp(){

        System.out.println("@BeforeAll is running");
    }

    @AfterAll
    public static void tearDown(){   //everything that run once is "static"

        System.out.println("@BeforeAll is running");
    }

    @BeforeEach
    public void setupTest(){

        System.out.println("@BeforeEach is running");
    }
    @AfterEach
    public void tearDownTest(){

        System.out.println("@AfterEach is running");
    }

    @DisplayName("Test 1+3=4")
    @Test
    public void test(){
        System.out.println("test1 is running");
        Assertions.assertEquals(4,1+3);
    }

    @Disabled
    @DisplayName("Test 4*3=12")
    @Test
    public void test2(){
    System.out.println("test2 is running");
       assertEquals(12, 4*3);
}



}
