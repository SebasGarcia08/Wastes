package model;

public class Recyclable extends Waste {
    
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

    public static int getNumberOfObjs(){
        return numberOfObjs;
    }

    // public boolean isUsable(){
    //     boolean hasValidDescription = true;
    //     return hasValidDescription;
    // }

    @Override
    public double calculateHarmfulEffect(){
        double earlierHarmfulEffect = super.calculateHarmfulEffect();
        return  earlierHarmfulEffect - 0.02 * earlierHarmfulEffect; 
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() +
            " type='" + type + "'" +
            ", description='" + description + "'" +
            "}";
    }
}