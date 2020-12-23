package day05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;


public class HamcrestCollectionSupport {

    @Test
    public void testList(){

        List<Integer> numList = Arrays.asList(4,6,7,9,5,88,90);

        //use hamcrest matcher to test the size of this list
        assertThat( numList , hasSize(7) );

        //assertThat this list contains 9
        assertThat(numList,hasItem(9) );   //hamcrest assertion power
//        for (Integer each : numList) {
//            if (each==9){
//               return true;
//            }
//        }
//        return false;

        //assert that this list contains 9, 88, 7
        assertThat(numList, hasItems(9,7,88) );

        //assert that every item in the list is more than 3
        assertThat(numList , everyItem( greaterThan(3) ) );


        assertThat(numList , everyItem( is( greaterThan(3) )  ) );


        List<String> allNames  = Arrays.asList("Rory Hogan", "Mariana","Olivia","Gulbadan","Ayxamgul","Kareem","Virginia","Tahir Zohra") ;
        assertThat(allNames, hasSize(8) );

        assertThat(allNames, hasItems("Virginia","Ayxamgul", "Rory Hogan") );

        //check every items if has letter a
        assertThat(allNames, everyItem( containsString("a" ) ) );
        //check every items has letter a in case insensitive manner






    }



}
