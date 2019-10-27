package model;

public class Biodegradable extends Waste{
    private boolean isSuitableForComposting;
    private static int numberOfObjs = 0;

    public Biodegradable(String id, String name, String origin, String color, int decomposition_days, boolean isSuitableForComposting) {
        super(id, name, origin, color, decomposition_days);
        this.isSuitableForComposting = isSuitableForComposting;
        ++numberOfObjs;
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
        return  earlierHarmfulEffect - 0.01 * earlierHarmfulEffect; 
    }

    public boolean getIsSuitableForComposting() {
        return this.isSuitableForComposting;
    }

    public void setIsSuitableForComposting(boolean isSuitableForComposting) {
        this.isSuitableForComposting = isSuitableForComposting;
    }

    @Override
    public String toString() {
        return super.toString() +
            " isSuitableForComposting='" + isSuitableForComposting + "'" +
            "}";
    }
}