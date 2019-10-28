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

// Constructor
	public Waste(String id, String name, String origin, String color, int decomposition_days) {
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.color = color;
		this.decomposition_days = decomposition_days;
	}

	/**
	 * Calculates the harmful effect according to the specified in the problem analysis of this project. 
	 * <b><b>pre: </b>: </b> class should be instaciated.
	 * @return harmful effect double
	 */ 
    public double calculateHarmfulEffect() {
        double harmfulEffect = 0;
        for(int i=0; i < RATES.length; i++){
            if (getOrigin().equalsIgnoreCase(ORIGINS[i])) {
                harmfulEffect = getDecompositionDays() * RATES[i];
            }
        }
        return harmfulEffect;
	}

	/**
	 * <b>pre: </b>: object should be instaciated
	 * @return id, String attribute of this class
	 */
	public String getId() {
		return id;
	}

	/**
	 * <b>pre: </b>: object should be instaciated
	 * @param id String, the new id of the instance of this class
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <b>pre: </b>: object should be instaciated
	 * @return name, String of the instance of this class
	 */
	public String getName() {
		return name;
	}

	/**
	 * <b>pre: </b>: object should be instaciated
	 * @param name String, the new name of the instance of this class
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <b>pre: </b>: object should be instaciated
	 * @return origin, string
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * <b>pre: </b>: object should be instaciated
	 * @param origin string
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * <b>pre: </b> : object should be instaciated
	 * @return string, color of the object of this class
	 */
	public String getColor() {
		return color;
	}

	/**
	 * <b>pre: </b> : object should be instaciated
	 * @param color String
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * <b>pre: </b> : object should be instaciated
	 * @return decomposition days of waste
	 */
	public int getDecompositionDays() {
		return decomposition_days;
	}

	/**
	 * <b>pre: </b>: object should be instaciated 
	 * @param decompositionDays int, days of decomposition of the waste
	 */
	public void setDecompositionDays(int decompositionDays) {
		this.decomposition_days = decompositionDays;
	}

	/**
	 * @return String containing the attributes of the object instaciated of this class 
	 */
	@Override
	public String toString() {
		return "name: " + getName() + ", origin: " + getOrigin() + ", color: " + getColor() + ",  take "  + getDecompositionDays()+ " days to decompose" + 
		", harmful effect: " + calculateHarmfulEffect() + " (id: " + getId();  
	}
}
