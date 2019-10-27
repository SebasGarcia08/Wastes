package model;

public class Product{
    private String id;
    private String name;
    private String description;
    private static int numberOfObjs = 0;

    public Product(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        ++numberOfObjs;
    }

    public static int getNumberOfObjs(){
        return numberOfObjs;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product [description=" + description + ", id=" + id + ", name=" + name + "]";
    }
}