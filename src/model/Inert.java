package model;

public class Inert extends Waste{
    private String tips;
    private static int numberOfObjs = 0;

    public Inert(String id, String name, String origin, String color, int decomposition_days, String tips) {
        super(id, name, origin, color, decomposition_days);
        this.tips = tips;
        ++numberOfObjs;
    }
  
    
    public static int getNumberOfObjs(){
        return numberOfObjs;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return super.toString() +
            " tips='" + getTips() + "'" +
            "}";
    }
}