package day09;

import day07.HR_ORDS_Test;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Department;
import testBase.HR_ORDS_TestBase;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TestingOutLombokDependency extends HR_ORDS_TestBase {

    @Test
    public void testDepartmentPOJO(){
        Department d = new Department();
        d.setDepartment_id(100);
        System.out.println(d.getDepartment_id());

        Department d2 =new Department(100,"ABC",12,170);
        System.out.println("d2 = " + d2);
    }


    @Test
    public void testDepartmentJsonArrayToListOfPojo(){
        List<Department> allDeps = get("/departments").jsonPath().getList("items",Department.class);
        allDeps.forEach(System.out::println);

        //copy the content of this list into new List
    //ONLY PRINT NOT NULL DEPARTMENT ID
        List<Department> allDepsCopy= new ArrayList<>(allDeps);
        allDepsCopy.removeIf( eachDep -> eachDep.getManager_id()==0);
       // allDeps.removeIf( eachDep -> eachDep.getManager_id()==0);
        allDepsCopy.forEach(System.out::println);

    }


    @DisplayName("GET /departments and filter the result with JsonPath groovy")
    @Test
    public void testFilterResultWithGroovy(){
        JsonPath jp = get("/departments").jsonPath();
       List<Department> allDeps =
               jp.getList("items.findAll { it.manager_id !=null }" , Department.class);
        allDeps.forEach(System.out::println);


        //what if I just want to get List<String> to store Department
        List<String> depNames = jp.getList("items.department_name");
        System.out.println("depNames = " + depNames);

        // -->> items.department_name (all)
        // -->> items.findAll {it.manager_id>0 }.department_name (filtered for manager_id more than 0)
        List<String> depNamesFiltered = jp.getList("items.findAll {it.manager_id>0 }.department_name") ;
        System.out.println("depNamesFiltered = " + depNamesFiltered);



        //get all departments Id if it is more than 70
        List<Integer> allDepIds = jp.getList("items.department_id");
        System.out.println("allDepIds = " + allDepIds);

        List<Integer> allDepIdsFiltered =
                jp.getList("items.department_id.findAll{ it > 70 } ") ;
        System.out.println("allDepIdsFiltered = " + allDepIdsFiltered);
// what if we have more than one condition for example : department_id between 70 - 100
        List<Integer> deps70to100 =
                jp.getList("items.department_id.findAll{ it >= 70 && it <= 100  }") ;
        System.out.println("deps70to100 = " + deps70to100);

// get the name of departments if department_id between 70 - 100
        List<String> allDeps70To100 =
                jp.getList("items.findAll{ it.department_id >=70 && it.department_id <=100 }.department_name");
        System.out.println("allDeps70To100 = " + allDeps70To100);

        /*
        * There are other interesting methods that we can use on collections in Groovy as well, for example:
        find – finds the first item matching a closure predicate
        collect – collect the return value of calling a closure on each item in a collection
        sum – Sum all the items in the collection
         max/min – returns the max/min values of the collection*/



        // findAll-->> will return all matching result
        // find -->> will return first match for the condition
        String dep10 =  jp.getString("items.find{ it.department_id==10 }.department_name");
        System.out.println("department 10 name = " + dep10);
       //department 10 name = Administration

        // sum / min / max  collect
       // get the sum of entire department_id
        int sumOfAllDepIDs = jp.getInt("items.department_id.sum()") ;
        int sumOfAllDepIDs2 = jp.getInt("items.sum {it.department_id} ") ;
        System.out.println("sumOfAllDepIDs = " + sumOfAllDepIDs);
        System.out.println("sumOfAllDepIDs2 = " + sumOfAllDepIDs2);

        // get the lowest department_id
        int lowestDepId = jp.getInt("items.department_id.min()") ;
        System.out.println("lowestDepId = " + lowestDepId);

        // get the highest department_id
        int highestDepId = jp.getInt("items.department_id.max()") ;
        System.out.println("highestDepId = " + highestDepId);

        // print number 5 dep ID
        System.out.println("number 5 dep ID" + jp.getInt("items.department_id[4]")   );
// print number last dep ID
        System.out.println("last dep ID " + jp.getInt("items.department_id[-1]")   );
// print from index 7 till index 10 dep ID
        System.out.println("index 7-10 dep ID " + jp.getInt("items.department_id[7..10]")   );

    }


}
