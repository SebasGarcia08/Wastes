package model;

public abstract class Waste {
    public static final String IND = "industrials";
    public static final String DOM = "domiciliaries";
    public static final String MUN = "municipals";
    public static final String CON = "construction";
    public static final String HSP = "hospitaller";
    
    public static final String[] ORIGINS = {IND, DOM, MUN, CON, HSP};
    public static final double[] RATES = {0.10, 0.05, 0.08, 0.12, 0.15};

    private String id;
    private String name;
    private String origin;
    private String color;
	private int decomposition_days; // In days

	public Waste(String id, String name, String origin, String color, int decomposition_days) {
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.color = color;
		this.decomposition_days = decomposition_days;
	}
	
    public double calculateHarmfulEffect() {
        double harmfulEffect = 0;
        for(int i=0; i < RATES.length; i++){
            if (this.origin.equalsIgnoreCase(ORIGINS[i])) {
                harmfulEffect = getDecompositionDays() * RATES[i];
            }
        }
        return harmfulEffect;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getDecompositionDays() {
		return decomposition_days;
	}

	public void setDecompositionDays(int decompositionDays) {
		this.decomposition_days = decompositionDays;
	}

	@Override
	public String toString() {
		return "Waste [color=" + color + ", decomposition_days=" + decomposition_days + ", id=" + id + ", name=" + name
				+ ", origin=" + origin;
	}
}
