package model;

public class Inert extends Waste{
    private String tips;
    private static int numberOfObjs = 0;

    // Constructor
    public Inert(String id, String name, String origin, String color, int decomposition_days, String tips) {
        super(id, name, origin, color, decomposition_days);
        this.tips = tips;
        ++numberOfObjs;
    }

    /**
     * Static method that returns the number of objects instaciated for this class
     */
    public static int getNumberOfObjs(){
        return numberOfObjs;
    }

    /**
     * Retrieves the tips for this type of waste
     * @return tips attribute for this class
     */
    public String getTips() {
        return this.tips;
    }

    /**
     * Updates the tips 
     * @param tips String new tips for this object
     */
    public void setTips(String tips) {
        this.tips = tips;
    }

    /**
     * @return Strign containing the information of instance of this class
     */
    @Override
    public String toString() {
        return super.toString() + ", tips: " + getTips() + ")\n";
    }
}