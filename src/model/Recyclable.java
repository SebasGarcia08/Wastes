package model;

public class Recyclable extends Waste implements Useful{
    
    public static final String PAP = "PAPER";
    public static final String CAR = "CARTON";
    public static final String GLA = "GLASS";
    public static final String PLS = "PLASTIC";
    public static final String MET = "METALS";

    private String type;
    private String description;
    private static int numberOfObjs = 0;
    
    public Recyclable(String id, String name, String origin, String color, int decomposition_days, String type, String description) {
        super(id, name, origin, color, decomposition_days);
        this.type = type;
        this.description = description;
        ++numberOfObjs;
    }

    /**
     * Static method that returns the number of objects instaciated for this class
     */
    public static int getNumberOfObjs(){
        return numberOfObjs;
    }

     /**
     * Calculates whether the class is or no useful depending on its attributes
     * @return true or false, whether the waste is useful or not.
     */
    @Override
    public boolean isUseful(){
        return (description != "Not required");
    }

   	/**
	 * Calculates the harmful effect according to the specified in the problem analysis of this project. 
	 * <b><b>pre: </b>: </b> class should be instaciated.
	 * @return harmful effect double
	 */ 
    @Override
    public double calculateHarmfulEffect(){
        double earlierHarmfulEffect = super.calculateHarmfulEffect();
        return  earlierHarmfulEffect - 0.02 * earlierHarmfulEffect; 
    }

    /**
     * Retrieves the type for this class
     * @return type attribute for the instance of this class
     */
    public String getType() {
        return this.type;
    }

    
    /**
     * updates the type for this class
     * @param type attribute for the instance of this class to update
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the description for this Recyclable object
     * @return description attribute for the instance
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the description for this Recyclable object
     * @param description new attribute for the instance to be updated for
     */
    public void setDescription(String description) {
        this.description = description;
    }

     /**
     * @return Strign containing the information of instance of this class
     */
    @Override
    public String toString() {
        return super.toString() + ", type : " + getType() + ", description : " + getDescription() + 
                                ", is useful: " + (isUseful() ? "YES" : "NO") + ") \n";
    }
}