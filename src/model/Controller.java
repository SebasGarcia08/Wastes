package model;

import java.util.Arrays;
import java.util.Comparator;

public class Controller {
    private Product[] products;
    private Waste[] wastes;
    private int[] relations;

    /**
     * Constructor.
     */
    public Controller() {
        this.products = new Product[0];
        this.wastes = new Waste[0];
        this.relations = new int[0];
    }

// ================================================================= VALIDATORS =================================================================
/**
 * Validates if some waste in wastes array has the name specified in parameter
 * @param name String, name by which waste will be searched in wastes array
 * @return True if name already exists, False if not.
 */
public boolean wasteNameExists(String name){
    boolean duplicated = false;
    for(Waste waste : wastes){
        if(waste != null && waste.getName().equalsIgnoreCase(name))
            duplicated = true;
    }
    return duplicated;
}

/**
 * Validates if some product in products array has the name specified in parameter
 * @param name String, name by which product will be searched in products array
 * @return True if name already exists, False if not.
 */
public boolean productNameExists(String name){
    boolean duplicated = false;
    for(Product product : products){
        if(product != null && product.getName().equalsIgnoreCase(name))
            duplicated = true;
    }
    return duplicated;
}

/**
 * Answer if exist a waste that already has the id specified in parameter
 * @return true if exist a waste with id specified, false if not
 */
public boolean wasteIdExists(String waste_id){
    return (searchWasteById(waste_id) != -1);
}

/**
 * Answer if exist a product that already has the identifier specified in parameter
 * @return true if exist a product with id specified, false if not
 */
public boolean productIdExists(String product_id){
    return (searchProductById(product_id) != -1);
}

/**
 * @return product in the last position of porducts array, if array is empty, returns null.
 */
public Product getLastProductAdded(){
    return getProducts()[getProducts().length - 1];
}

// ================================================================= WASTE ADDERS =================================================================
    
    /**
     * Create an instance of Biodegradable waste and append it to wastes array
     */
    public void addWasteB(String id, String name, String origin, String color, int decomposition_days, boolean isSuitableForComposting){
             appendWaste(new Biodegradable(id, name,  origin,  color,  decomposition_days, isSuitableForComposting));
    }

    /**
     * Create an instance of Recyclable waste and append it to wastes array
     */
    public void addWasteR(String waste_id, String name, String origin, String color, int decomposition_days, String type, String description){
            appendWaste(new Recyclable(waste_id, name, origin, color, decomposition_days, type, description));
    }

    /**
     * Create an instance of Inert waste and append it to wastes array
     */
    public void addWasteI(String id, String name, String origin, String color, int decomposition_days, String tips){
            appendWaste(new Inert(id, name, origin, color, decomposition_days, tips));
    }
    
// ================================================================= PORDUCT ADDER =================================================================
    /**
     * Create an instance of product and append it to product array
     */
    public void addProduct(String product_id, String name, String description){
            appendProduct(new Product(product_id, name, description));
    }

    // /**
    //  * Asumo que me pasás como parámetro esto: product[i].getWastes(). Un array de Wastes con posiciones nulas
    //  * @return array de wastes creados 
    //  */
    // public Waste[] getNotNullWastes(Wastes[] wastes_with_null_positions){
    //     // Count how many positions aren't null
    //     int num_of_not_null_positions = 0;
    //     for(Waste waste: wastes_with_null_positions){
    //         num_of_not_null_positions = (waste != null) ? 1 : 0; 
    //     }
        
    //     // Intiialize clean waste array
    //     Waste[] clean_array = new Waste[num_of_not_null_positions];
    //     int j= 0; // Counter for clean array
    //     for(int i = 0; i < wastes_with_null_positions.length; i++){
    //         if(wastes_with_null_positions[i] != null){
    //             clean_array[j] = wastes_with_null_positions[i];
    //             j++;
    //         }
    //     }
    //     return clean_array;
    // }
// ================================================================= RELATION MAKER =================================================================
    /**
     * IMPORTATN METHOD: this method is encharged of append the relation between the last waste created and the product with id specified as parameter.
     * The i-th position of relation array corresponds to the i-th position of waste in wastes array, 
     * and the value in the i-th position corresponds to the n-th position of product in products array.
     * So that the waste in in the i-th position of relation array is linked with the value that corresponds to the prodcut in product array 
     * Example: if relation array equals = [0,0,1] means that the wastes in the positions 0 and 1 of wastes array are produced 
     * by the product in the 0 position or products array. 
     * Meanwhile, the waste in the position 2 of wastes array is produced by the product in the position 1 of products array  
     * That means that the produc in position 0 produces two wastes, and the product of position 1 produces only the waste in position 2. 
     * @param product_id String, id of the product that porduces the last waste created.
     */
    public void makeRelation(String product_id) {
        int[] old_relations = getRelations();
        int[] new_relations = new int[old_relations.length + 1];
        for (int i = 0; i < old_relations.length; i++) {
            new_relations[i] = old_relations[i];
        }
        new_relations[new_relations.length - 1] = searchProductById(product_id);
        old_relations = null;
        setRelations(new_relations);
    }
// ================================================================= SEARCHERS =================================================================
    /**
     * Searches the product that has the specified id
     * @param pid id of the product to search
     * @return idx int, the index in products array of product with id specified. If is not found, returns -1.
     */
    public int searchProductById(String pid) {
        boolean found = false;
        int idx = -1;
        for (int i = 0; i < products.length && !found; i++) {
            if (products[i].getId().equalsIgnoreCase(pid)) {
                idx = i;
                found = true;
            }
        }
        return idx;
    }

    /**
     * Searches the waste that has the specified id
     * @param wid id of waste to search
     * @return idx int, the index in wastes array of waste with id specified. If is not found, returns -1.
     */
    public int searchWasteById(String wid) {
        boolean found = false;
        int idx = -1;
        for (int i = 0; i < wastes.length && !found; i++) {
            if (wastes[i].getId().equalsIgnoreCase(wid)) {
                idx = i;
                found = true;
            }
        }
        return idx;
    }

    /**
     * Searches waste with specified identifier
     * @param wname name of the waste searched
     * @return idx int, the index in wastes array of waste with id specified. If is not found, returns -1.
     */
    public int searchWasteByName(String wname) {
        boolean found = false;
        int idx = -1;
        for (int i = 0; i < wastes.length && !found; i++) {
            if (wastes[i].getName().equalsIgnoreCase(wname)) {
                idx = i;
                found = true;
            }
        }
        return idx;
    }
// ================================================================= APPEND =================================================================
    /**
     * Expands array of products and adds product passed as argument
     * @param new_product new array of products with the new element added.
     */
    public void appendProduct(Product new_product) {
        Product[] old_products = getProducts();
        Product[] new_products = new Product[old_products.length + 1];
        for (int i = 0; i < old_products.length; i++) {
            new_products[i] = old_products[i];
        }
        new_products[new_products.length - 1] = new_product;
        old_products = null;
        setProducts(new_products);
    }

    /**
     * Expands array of wastes and adds product passed as argument
     * @param new_wastes new array of wastes with the new element added.
     */
    public void appendWaste(Waste new_waste) {
        Waste[] old_wastes = getWastes();
        Waste[] new_wastes = new Waste[old_wastes.length + 1];
        for (int i = 0; i < old_wastes.length; i++) {
            new_wastes[i] = old_wastes[i];
        }
        new_wastes[new_wastes.length - 1] = new_waste;
        old_wastes = null;
        setWastes(new_wastes);
    }

// ================================================================= SHOWS =================================================================
    /**
     * Shows information about the wastes passed as arguments in the specified format in statement. For example:
     * 
     * @param wastes Waste[], array of wastes to show
     * @return res String containing the formatted information about each wasted passed in array.
     */
    public String showWastes(Waste[] wastes){
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int alfa_counter = 0;
        String res = "";

        Biodegradable[] bio_array = new Biodegradable[Biodegradable.getNumberOfObjs()];
        int j = 0;
        Recyclable[] rec_array  = new Recyclable[Recyclable.getNumberOfObjs()];
        int n = 0;
        Inert[] inert_array = new Inert[Inert.getNumberOfObjs()];
        int r = 0;

        for(int i = 0; i < wastes.length; i++) {
            if( wastes[i] instanceof Biodegradable){
                bio_array[j] = (Biodegradable) wastes[i];
                j++;
            } else if( wastes[i] instanceof Recyclable){
                rec_array[n] = (Recyclable) wastes[i];
                n++;
            } else {
                inert_array[r] = (Inert) wastes[i];
                r++;
            }
        }

        res += "Biodegradable \n";
        for(int i = 0; i < bio_array.length; i++) {
            res += "   " + alphabet[alfa_counter] + ".    " + bio_array[i].toString();
            alfa_counter = (alfa_counter > alphabet.length-1) ? 0 : alfa_counter + 1;
        }

        res +="Recyclable \n";
        for(int i = 0; i < rec_array.length; i++) {
            res += "   " + alphabet[alfa_counter] + ".    " + rec_array[i].toString();
            alfa_counter = (alfa_counter > alphabet.length-1) ? 0 : alfa_counter + 1;
        }

        res +="Inert \n";
        for(int i = 0; i < inert_array.length; i++) {
            res += "   " + alphabet[alfa_counter] + ".    " + inert_array[i].toString();
            alfa_counter = (alfa_counter > alphabet.length-1) ? 0 : alfa_counter + 1;
        }
        return res;
    }

    // RF8
    /**
     * Sorts descendentially the Wastes passed as argument by their harmful effect and returns their information
     * @param wastes Waste[] array containing the wastes to be sorted
     * @return list_sorted String containing the ifnromations of wastes descendentially by harmful effect
     */
    public String showWastesSortedByHarmfulEffect(Waste[] wastes){     
        Waste[] array = wastes.clone();
        String list_sorted = "Wastes sorted descendentially by harmful effect:\n";
        
        Waste temp;
        for(int i = 0; i < array.length; i++){
          for(int j = i +1; j<array.length; j++){
            if(array[i].calculateHarmfulEffect() < array[j].calculateHarmfulEffect()){
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
          }
        }

        for(Waste waste : array){
            list_sorted += waste.toString();
        }
        return list_sorted;
    }

    /**
     * Shows the products added and their information
     * @return res String containing the information of all products added 
     */
    public String showProducts(){
        String res = "LIST OF PRODUCTS ADDED:\n";
        for(int i = 0; i < products.length; i++) {
            res += i+")" + ".  " + products[i].toString() + "\n";
        }
        return res;
    }

    /**
     * <b>IMPORTANT METHOD</b>: Shows all the products and their wastes produced  
     * @return msg string containing a intuitive list of products and their wastes produced
     */
    public String showRelations(){
        String msg = "LIST OF RELATIONS:\n";
        for(int i = 0; i < products.length; i++) { 
            String header = "";
            String corpus = "";
            String indentation = "          ";
            if( products[i] != null && getWastesOf(getProducts()[i].getId()).length > 0){
                Waste[] wastes_of_product_i = getWastesOf(getProducts()[i].getId());
                header = products[i].toString() + "\n";
                for(int j = 0; j < wastes_of_product_i.length; j++){
                    corpus += indentation + "|\n" + indentation + "---- produces --> (" + wastes_of_product_i[j].toString() + "\n";
                }
            }
            msg += header + corpus;
        }
        msg +=  "\n" + Biodegradable.getNumberOfObjs() + " Biodegradable wastes created: " + "\n" + 
                Recyclable.getNumberOfObjs() + " Recyclable wastes created: " + "\n" + 
                Inert.getNumberOfObjs() + " Inert wastes created: " + "\n" + 
                Product.getNumberOfObjs() + " Products created \n";
        return msg;
    }

// ================================================================= GETTERS AND SETTERS =================================================================
    /**
     * Retrieves the wastes produced by product specified 
     * @param product_id id of the product that user wants their wastes produced
     * @return wastes_of_product, Waste[] containing the wastes produced by product specified 
     */
    public Waste[] getWastesOf(String product_id){
        int product_position = searchProductById(product_id); // PRODUCT MUST EXIST AND MUST HAVE AT LEAST A RELATION
        int count_wastes = 0;
        // Count how many waste has product 
        for(int i = 0; i < getRelations().length; i++){
            count_wastes += (getRelations()[i] == product_position) ? 1: 0;
        }
        Waste[] wastes_of_product = new Waste[count_wastes]; //Initialize output array
        int n = 0;
        for(int i = 0; i < getRelations().length; i++){
            if(getRelations()[i] == product_position){
                wastes_of_product[n] = getWastes()[i];
                n++;
            }
        }
        return wastes_of_product;
    }

    /**
     * Retrieves the product associated with the waste specified
     * @param waste_id id of the waste that user wants to see product of
     * @return Product product associated with the waste specified
     */
    public Product getProductOf(String waste_id){
        int[] relations = getRelations();
        int waste_position = searchWasteById(waste_id);
        return getProducts()[ relations[waste_position] ];
    }

    /**
     * Retrieves all the products added 
     * @return Product[] all the products added
     */
    public Product[] getProducts() {
        return products;
    }

    /**
     * Updates the products array with the passed as argument
     * @param products Product[] array
     */
    public void setProducts(Product[] products) {
        this.products = products;
    }

    /**
     * Retrieves all the wastes added 
     * @return Waste[] all the products added
     */
    public Waste[] getWastes() {
        return wastes;
    }

    /**
     * Updates the wastes array with the passed as argument
     * @param wastes Waste[] array 
     */
    public void setWastes(Waste[] wastes) {
        this.wastes = wastes;
    }

    /**
     * Retrieves all the relations added 
     * @return int[] containing all the relations established between unique wastes and products
     */
    public int[] getRelations() {
        return this.relations;
    }

    /**
     * Updates the wastes array with the passed as argument
     * @param relations int[] 
     */
    public void setRelations(int[] relations) {
        this.relations = relations;
    }

}