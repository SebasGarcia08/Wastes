package model;

public class Biodegradable extends Waste implements Useful{
    private boolean isSuitableForComposting;
    private static int numberOfObjs = 0;

    public Biodegradable(String id, String name, String origin, String color, int decomposition_days, boolean isSuitableForComposting) {
        super(id, name, origin, color, decomposition_days);
        this.isSuitableForComposting = isSuitableForComposting;
        ++numberOfObjs;
    }

    @Override
    public boolean isUseful(){
        return (super.getDecompositionDays() < 365 && getIsSuitableForComposting());
    }

    public static int getNumberOfObjs(){
        return numberOfObjs;
    }

    public boolean isUsable(){
        return (super.getDecompositionDays() < 1 && isSuitableForComposting);
    }
    
    @Override
    public double calculateHarmfulEffect(){
        double earlierHarmfulEffect = super.calculateHarmfulEffect();
        return  (isSuitableForComposting) ? (earlierHarmfulEffect - 0.01 * earlierHarmfulEffect) : earlierHarmfulEffect; 
    }

    public boolean getIsSuitableForComposting() {
        return this.isSuitableForComposting;
    }

    public void setIsSuitableForComposting(boolean isSuitableForComposting) {
        this.isSuitableForComposting = isSuitableForComposting;
    }

    @Override
    public String toString() {
        return super.toString() + ", is suitable for composting: " + ((isSuitableForComposting) ? "YES" : "NO") +
                                ", is useful: " + (isUseful() ? "YES" : "NO") + ") \n";
    }
}