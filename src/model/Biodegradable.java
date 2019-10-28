package model;

public class Biodegradable extends Waste implements Useful{
    private boolean isSuitableForComposting;
    private static int numberOfObjs = 0;

    public Biodegradable(String id, String name, String origin, String color, int decomposition_days, boolean isSuitableForComposting) {
        super(id, name, origin, color, decomposition_days);
        this.isSuitableForComposting = isSuitableForComposting;
        ++numberOfObjs;
    }

    /**
     * Calculates whether the class is or no useful depending on its attributes
     * @return true or false, whether the waste is useful or not.
     */
    @Override
    public boolean isUseful(){
        return (super.getDecompositionDays() < 365 && getIsSuitableForComposting());
    }

    /**
     * Static method that returns the number of objects instaciated for this class
     */
    public static int getNumberOfObjs(){
        return numberOfObjs;
    }
    
    /**
	 * Calculates the harmful effect according to the specified in the problem analysis of this project. 
	 * <b><b>pre: </b>: </b> class should be instaciated.
	 * @return harmful effect double
	 */ 
    @Override
    public double calculateHarmfulEffect(){
        double earlierHarmfulEffect = super.calculateHarmfulEffect();
        return  (isSuitableForComposting) ? (earlierHarmfulEffect - 0.01 * earlierHarmfulEffect) : earlierHarmfulEffect; 
    }

    /**
     * Retrieves whether this biodegradable waste is suitable for composting or not
     * @return whether this biodegradable waste is suitable for composting or not
     */
    public boolean getIsSuitableForComposting() {
        return this.isSuitableForComposting;
    }

    /**
     * Updates whether this biodegradable waste is suitable for composting or not
     * @param isSuitableForComposting boolean, whether this biodegradable waste is suitable for composting or not
     */
    public void setIsSuitableForComposting(boolean isSuitableForComposting) {
        this.isSuitableForComposting = isSuitableForComposting;
    }

     /**
     * @return Strign containing the information of instance of this class
     */
    @Override
    public String toString() {
        return super.toString() + ", is suitable for composting: " + ((isSuitableForComposting) ? "YES" : "NO") +
                                ", is useful: " + (isUseful() ? "YES" : "NO") + ") \n";
    }
}