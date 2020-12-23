package utility;

import java.util.LinkedHashMap;
import java.util.Map;
import com.github.javafaker.Faker;
import pojo.Spartan;

public class SpartanUtil {

    public static Map<String,Object> getRandomSpartanRequestPayload(){

     Faker faker = new Faker() ;

        Map<String,Object> payLoadMap = new LinkedHashMap<>();
        payLoadMap.put("name" , faker.name().firstName() ) ;
        payLoadMap.put("gender" , faker.demographic().sex() ) ;
        payLoadMap.put("phone" , faker.number().numberBetween(5000000000L, 9999999999L)) ;

        return payLoadMap ;

    }


    public static Spartan getRandomSpartanPOJO_Payload(){
        Faker faker = new Faker() ;
        Spartan randomSp = new Spartan();

        randomSp.setName(faker.name().firstName());
        randomSp.setGender(faker.demographic().sex());
        randomSp.setPhone(faker.number().numberBetween(5000000000L, 9999999999L));
        return randomSp ;



    }

    public static void main(String[] args) {
        System.out.println(getRandomSpartanPOJO_Payload());
    }

}
