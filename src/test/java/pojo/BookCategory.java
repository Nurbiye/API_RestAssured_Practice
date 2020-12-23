package pojo;

// we want to be able to name the fields any name we want
// rather than being limited to use same name as json object key
// but we need to tell Jackson data-bind
// which json key map to which pojo class field
// we use annotation @JsonProperties for this

public class BookCategory {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "BookCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }






}
