package day05;

//by default test is running on alphabetical order
//or the test method name

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.MethodOrderer.*;


//@TestMethodOrder(OrderAnnotation.class)   //in order (numerical)
//@TestMethodOrder(Random.class)          //randomly order
//@TestMethodOrder(MethodName.class)       //by name order

@TestMethodOrder(MethodOrderer.DisplayName.class)       //by name display name

public class TestOrderingInJunit {

    @Order(3)
    @DisplayName("3.Test A method")
    @Test
    public void testA(){
        System.out.println("running test is A");
    }

    @Order(1)
    @DisplayName("1.Test C method")
    @Test
    public void testC(){
        System.out.println("running test is C");
    }

    @Order(4)
    @DisplayName("4.Test D method")
    @Test
    public void testD(){
        System.out.println("running test is D");
    }

    @Order(2)
    @DisplayName("2.Test B method")
    @Test
    public void testB(){
        System.out.println("running test is B");
    }




}
