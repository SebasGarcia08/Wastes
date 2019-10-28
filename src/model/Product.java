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

    /**
     * <b>pre: </b> object should be instaciated
     * @return numberOfObjs int, number of instances of this class
     */
    public static int getNumberOfObjs(){
        return numberOfObjs;
    }

    /**
     * <b>pre: </b> object should be instaciated
     * @return id, String containing the identifier of this object
     */
    public String getId() {
        return id;
    }

    /**
     * <b>post: </b> id will be updated 
     * @param id new id for this object
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * <b>pre: </b> object should be instaciated
     * @return name, string containing the current name of this object 
     */
    public String getName() {
        return name;
    }

    /**
     * <b> post: </b> name will be updated 
     * @param name String, new name of this object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <b> pre: </b> object should be instaciated
     * @return description, string containing the current description of this object
     */
    public String getDescription() {
        return description;
    }

    /**
     * <b> post: </b> description will be updated
     * @param description string, new description of this object
     */
    public void setDescription(String description) {
        this.description = description;
    }

   	/**
	 * @return String containing the attributes of the object instaciated of this class 
	 */
    @Override
    public String toString() {
        return "id: " + getId() + ", name: " + getName() + ", description: " + getDescription(); 
    }
}