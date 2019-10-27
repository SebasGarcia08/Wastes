package model;

import java.util.Arrays;

public class Controller {
    private Product[] products;
    private Waste[] wastes;
    private int[] relations;

    public Controller() {
        this.products = new Product[0];
        this.wastes = new Waste[0];
        this.relations = new int[0];
    }

// ================================================================= VALIDATORS =================================================================
public boolean wasteNameExists(String name){
    boolean duplicated = false;
    for(Waste waste : wastes){
        if(waste != null && waste.getName().equalsIgnoreCase(name))
            duplicated = true;
    }
    return duplicated;
}

public boolean productNameExists(String name){
    boolean duplicated = false;
    for(Product product : products){
        if(product != null && product.getName().equalsIgnoreCase(name))
            duplicated = true;
    }
    return duplicated;
}


public boolean wasteIdExists(String waste_id){
    return (searchWasteById(waste_id) != -1);
}

public boolean productIdExists(String product_id){
    return (searchProductById(product_id) != -1);
}

public Product getLastProductAdded(){
    return getProducts()[getProducts().length - 1];
}

// ================================================================= WASTE ADDERS =================================================================
    public void addWasteR(String waste_id, String name, String origin, String color, int decomposition_days, String type, String description){
            appendWaste(new Recyclable(waste_id, name, origin, color, decomposition_days, type, description));
    }
    
// ================================================================= PORDUCT ADDER =================================================================
    public void addProduct(String product_id, String name, String description){
            appendProduct(new Product(product_id, name, description));
    }
// ================================================================= RELATION MAKER =================================================================
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

// ================================================================= APPEND =================================================================
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
    public String showWastes(){
        String res = "";
        for(int i = 0; i < wastes.length; i++) {
            res += wastes[i].toString() + "\n";
        }
        return res;
    }

    public String showProducts(){
        String res = "LIST OF PRODUCTS ADDED:\n";
        for(int i = 0; i < products.length; i++) {
            res += i+")" + ".  " + products[i].toString() + "\n";
        }
        return res;
    }

    public String showRelations(){
        String msg = "LIST OF RELATIONSHIPS:\n";
        for(int i = 0; i < products.length; i++) { 
            String header = "";
            String corpus = "";
            String indentation = "";
            if( products[i] != null && getWastesOf(getProducts()[i].getId()).length > 0){
                Waste[] wastes_of_product_i = getWastesOf(getProducts()[i].getId());
                header = products[i].toString() + "\n";
                for(int j = 0; j < header.length()-30; j++){
                    indentation += " ";
                } 
                for(int n = 0; n < wastes_of_product_i.length; n++){
                    corpus += indentation + "|\n" + indentation + "---> " + wastes_of_product_i[n].toString() + "\n";
                }
            }
            msg += header + corpus;
        }
        return msg;
    }

// ================================================================= GETTERS AND SETTERS =================================================================
    
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

    public Product getProductOf(String waste_id){
        int[] relations = getRelations();
        int waste_position = searchWasteById(waste_id);
        return getProducts()[ relations[waste_position] ];
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Waste[] getWastes() {
        return wastes;
    }

    public void setWastes(Waste[] wastes) {
        this.wastes = wastes;
    }

    public int[] getRelations() {
        return this.relations;
    }

    public void setRelations(int[] relations) {
        this.relations = relations;
    }

}