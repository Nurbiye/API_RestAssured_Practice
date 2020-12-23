package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter  @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Data

@JsonIgnoreProperties(ignoreUnknown=true)
//is applicable at deserialization of JSON to Java object ( POJO ) only.
// If your POJO does not contain certain properties that JSON does contain,
// they are ignored and no error is thrown.
//Annotation can be applied both to classes and to properties.
// If used for both, actual set will be union of all ignorals: that is, you can only add properties to ignore,
// not remove or override. So you can not remove properties to ignore using per-property annotation.
public class Department {

    private int department_id;
    private String department_name;
    private int manager_id;
    private int location_id;

//    public Department() {
//    }
//
//    public Department(int department_id, String department_name, int manager_id, int location_id) {
//        this.department_id = department_id;
//        this.department_name = department_name;
//        this.manager_id = manager_id;
//        this.location_id = location_id;
//    }
//
//    public int getDepartment_id() {
//        return department_id;
//    }
//
//    public void setDepartment_id(int department_id) {
//        this.department_id = department_id;
//    }
//
//    public String getDepartment_name() {
//        return department_name;
//    }
//
//    public void setDepartment_name(String department_name) {
//        this.department_name = department_name;
//    }
//
//    public int getManager_id() {
//        return manager_id;
//    }
//
//    public void setManager_id(int manager_id) {
//        this.manager_id = manager_id;
//    }
//
//    public int getLocation_id() {
//        return location_id;
//    }
//
//    public void setLocation_id(int location_id) {
//        this.location_id = location_id;
//    }
//
//
//    @Override
//    public String toString() {
//        return "Department{" +
//                "department_id=" + department_id +
//                ", department_name='" + department_name + '\'' +
//                ", manager_id=" + manager_id +
//                ", location_id=" + location_id +
//                '}';
//    }


}
