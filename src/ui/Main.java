package ui;

import model.*;
import static java.lang.System.out;
import java.util.Scanner;

public class Main {
    private static Controller controller;
    private static Scanner sc_num;
    private static Scanner sc_str;

    // Constructor intiializer
    public Main(){
        controller = new Controller();
        sc_str = new Scanner(System.in);
        sc_num = new Scanner(System.in);
    }

    /**
     * Main method that initializes the program
     * @param args
     */
    public static void main(String[] args){
        Main program = new Main();
        out.println("WELCOME to the wastes manager!");
        int election = 1;
        while(election != 9){
            out.print("\n=============== MENU ==============="+ 
                        "\n[1] Add a waste"+ 
                        "\n[2] Generate wastes report"+
                        "\n[3] Add a product and the wastes it could generate."+
                        "\n[4] Search waste by name"+
                        "\n[5] Search wastes by product id"+
                        "\n[6] Show products added" +
                        "\n[7] List wastes by harmful effect" +
                        "\n[8] Show all products and the their produced wastes" + 
                        "\n[9] Exit" +
                        "\nElection: ");
            election = sc_num.nextInt();
            switch(election){
                case 1:
                    program.registerWaste(); break;
                case 2:
                    out.println( program.controller.showWastes(controller.getWastes()) ); break;
                case 3:
                    program.registerProductWithProvidedWaste(); break;
                case 4: 
                    program.findWasteByName(); break;
                case 5:
                    program.findWastesByProductId(); break;
                case 6:
                    out.println(program.controller.showProducts()); break;
                case 7:
                    program.showSortedWasteByHarmfulEffectForProduct(); break;
                case 8:
                    out.println(program.controller.showRelations()); break;
                case 9:
                    out.println("Goodbye!"); break;
                default: 
                    out.println("Invalid option. Try again."); break;
            }
        }
    }
// ============================================================== REGISTER WASTE CENTER ================================================================
    /**
    * Method encharged of registering the waste and inmediately link it with a product if it hasn't yet
    * <b> pre: </b> Main class is initialized
    * <b> post: </b> Waste will be added with valid attributes, since these are validated inmediately
    */
    public void registerWaste(){
        out.println(controller.showProducts());
        String product_id = reqProductIdForAddingWaste();
        out.println("REGISTERING WASTE...");
        String id, name, origin, color; 
        int decomposition_days;

        id = reqWasteId();
        name = reqWasteName();
        origin = reqOrigin();
        out.print("Decomposition days: ");
        decomposition_days = sc_num.nextInt();
        out.print("Color: ");
        color = sc_str.nextLine();

        boolean valid_type_of_waste = false;
        char type_of_waste;
        while(!valid_type_of_waste){
            out.print("Choose the type of waste produced by your selected product: \n[B]iodegradable\n[R]ecyclable\n[I]nert\nChoose [B/R/I]: ");
            type_of_waste = sc_str.nextLine().toUpperCase().charAt(0);
            switch(type_of_waste){
                case 'B':
                    valid_type_of_waste = true;
                    registerWasteB(id, name, origin, color, decomposition_days, product_id);
                    break;
                case 'R':
                    valid_type_of_waste = true;
                    registerWasteR(id, name, origin, color, decomposition_days, product_id);
                    break;
                case 'I': 
                    valid_type_of_waste = true;
                    registerWasteI(id, name, origin, color, decomposition_days, product_id);
                    break;
                default:
                    out.println("Invalid choice. Choose 'B', 'R', or 'I'");
                    break;
            }
        }
    }

    /**
     * Overloaded version of RegisterWaste. This method is encharged of receive common fields of sperclass waste
     * <b>pre: </b> product with product_id is already in Product array in controller class 
     * <b> post: </b> Later wastes will be appended in Wastes array in controller class with common attributes valid, since they are validated inmediately
     * @param product_id
     */
    public void registerWasteWithProvidedProduct(String product_id){
        out.println("REGISTERING WASTE...");
        String id, name, origin, color; 
        int decomposition_days;
        
        id = reqWasteId();
        name = reqWasteName();
        origin = reqOrigin();
        out.print("Decomposition days: ");
        decomposition_days = sc_num.nextInt();
        out.print("Color: ");
        color = sc_str.nextLine();

        boolean valid_type_of_waste = false;
        char type_of_waste;
        while(!valid_type_of_waste){
            out.print("Choose the type of waste produced by your selected product: \n[B]iodegradable\n[R]ecyclable\n[I]nert\nChoose [B/R/I]: ");
            type_of_waste = sc_str.nextLine().toUpperCase().charAt(0);
            switch(type_of_waste){
                case 'B':
                    valid_type_of_waste = true;
                    registerWasteB(id, name, origin, color, decomposition_days, product_id);
                    break;
                case 'R':
                    valid_type_of_waste = true;
                    registerWasteR(id, name, origin, color, decomposition_days, product_id);
                    break;
                case 'I': 
                    valid_type_of_waste = true;
                    registerWasteI(id, name, origin, color, decomposition_days, product_id);
                    break;
                default:
                    out.println("Invalid choice. Choose 'B', 'R', or 'I'");
                    break;
            }
        }
    }

// ============================================================== REGISTER Biodegradable WASTE  ================================================================
    /**
     * Receive particular fields of Biodegradable waste and append it to Wastes[] array in controller class
     * <b>post:</b> relation will be stablished bewtween product with product_id
     * <b>pre: </b> product should be a valid id, i.e. already exists 
     * @param id String, identifier of the waste
     * @param name String, name of the waste
     * @param origin String, origin of the waste
     * @param color String, color of the waste
     * @param decomposition_days int, days of decomposition of the waste
     * @param product_id String, product id of the product that produces this biodegradable waste
     */
    public void registerWasteB(String id, String name, String origin, String color, int decomposition_days, String product_id){
        char y_n;
        boolean valid_y_n = false;
        boolean isSuitableForComposting = false;
        while(!valid_y_n){
            out.print("This biodegradable waste is suitable for composting? [y/n]: ");
            y_n = sc_str.nextLine().toLowerCase().charAt(0);
            switch(y_n){
                case 'y':
                    isSuitableForComposting = true; valid_y_n = true; break;
                case 'n':
                    isSuitableForComposting = false; valid_y_n = true; break;
                default:
                    out.println("Invalid choice. Try again:"); break;    
            }
        }
        controller.addWasteB(id, name,  origin,  color,  decomposition_days, isSuitableForComposting);
        controller.makeRelation(product_id);
    }

// ============================================================== REGISTER Recyclable WASTE  ================================================================
    /**
     * Receive particular fields of Recyclable waste and append it to Wastes[] array in controller class
     * <b>post:</b> relation will be stablished bewtween product with product_id
     * <b>pre: </b> product should be a valid id, i.e. already exists 
     * @param id String, identifier of the waste
     * @param name String, name of the waste
     * @param origin String, origin of the waste
     * @param color String, color of the waste
     * @param decomposition_days int, days of decomposition of the waste
     * @param product_id String, product id of the product that produces this Recyclable waste
     */
    public void registerWasteR(String id, String name, String origin, String color, int decomposition_days, String product_id){        
        String description = "Not required", type = "";
        if(origin == Waste.DOM || origin == Waste.IND){
            out.print("For industrials and domicilaries recyclable wastes is required a description...\n...of what is the most appropriate way to make the disposition of these elements: ");
            description = sc_str.nextLine();
        }
        // Requesting a valid choice of type
        boolean valid_type = false;
        int election;
        while(!valid_type){
            out.print("Choose the type of recyclable waste: \n[1] PAPER\n[2] CARTON\n[3] GLASS\n[4] PLASTIC\n[5] METALS\nElection[1/2/3/4/5]: ");
            election = sc_num.nextInt();
            switch(election){
                case 1:
                    type = Recyclable.PAP; valid_type = true; break;
                case 2:
                    type = Recyclable.CAR; valid_type = true;  break;
                case 3:
                    type = Recyclable.GLA; valid_type = true; break;
                case 4:
                    type = Recyclable.PLS; valid_type = true; break;
                case 5: 
                    type = Recyclable.MET; valid_type = true; break;
                default:
                    out.println("Invalid type. Try again:"); break;
            }
        }
        controller.addWasteR(id, name, origin, color, decomposition_days, type, description);
        controller.makeRelation(product_id);
    }

// ============================================================== REGISTER Inert WASTE  ================================================================
    /**
     * Receive particular fields of Inert waste and append it to Wastes[] array in controller class
     * <b>post:</b> relation will be stablished bewtween product with product_id
     * <b>pre: </b> product should be a valid id, i.e. already exists 
     * @param id String, identifier of the waste
     * @param name String, name of the waste
     * @param origin String, origin of the waste
     * @param color String, color of the waste
     * @param decomposition_days int, days of decomposition of the waste
     * @param product_id String, product id of the product that produces this Inert waste
     */
    public void  {
        out.print("Inert wastes have tips for dealing with them, write them: ");
        String tips = sc_str.nextLine();
        controller.addWasteI(id, name, origin, color, decomposition_days, tips);
        controller.makeRelation(product_id);
    }

// ============================================================== PRODUCT REGISTERERS  ================================================================
    /**
     * This method is encharged of registering append a product in products array in controller class
     * <b>post:</b> all field fo products will be valid, since they are validated inmediately and user will be notified about
     */
    public void registerProduct(){
        // String id, String name, String description
        out.println("REGISTERING PRODUCT...");
        String id, name, description;
        id = reqProductId();
        name = reqProductName();
        out.print("Description: ");
        description = sc_str.nextLine();
        controller.addProduct(id, name, description);
        out.println("Successfully added");
    }

    /**
     * This method is encharged of registering append a product in products array in controller class and link it to a waste
     * <b>post:</b> all field fo products will be valid, since they are validated inmediately and user will be notified about
     * <b>post: </b>Guarantee that the product registered is inmediately linked to a waste. 
     */
    public void registerProductWithProvidedWaste(){
         // String id, String name, String description
         out.println("REGISTERING PRODUCT...");
         String id, name, description;
         id = reqProductId();
         name = reqProductName();
         out.print("Description: ");
         description = sc_str.nextLine();
         controller.addProduct(id, name, description);
         out.println("Successfully added");
         registerWasteWithProvidedProduct(controller.getLastProductAdded().getId());
    }

    /**
     * This method is encharged of register of give a valid product id to be linked with a waste
     * <b>post: </b> in case that user want to link a waste to a product that doesn't exist, add it. 
     * @return valid_id, String, an existing product id corresponding to a valid product  
     */
    public String reqProductIdForAddingWaste(){
        boolean is_valid_id = false;
        String valid_id = "";
        while (!is_valid_id){
            out.print("Type the id of the product you want to link the waste for: ");
            String product_id = sc_str.nextLine();
            if(controller.productIdExists(product_id)){
                valid_id = product_id;
                is_valid_id = true;
            } else {
                out.print("This product id doesn't exist. Press 't' to try again or 'a' to add it [t/a]: ");
                char selection = sc_str.nextLine().charAt(0);
                if(selection == 't'){
                    continue;
                } else if(selection == 'a'){
                    registerProduct();
                    valid_id = controller.getLastProductAdded().getId();
                    is_valid_id = true;
                } else {
                    out.println("Invalid choice, try again");
                }
            }
        }
        return valid_id;
    }
// ============================================================== REQUESTER METHODS  ================================================================
    /**
     * This method guarantees that the product id returned is valid and there will not exist duplicated product id
     * @return valid_id, String, unique identifier for product to be added
     */
    public String reqProductId(){
        boolean valid = false;
        String id = "";
        while(!valid){
            out.print("Type the product id: ");
            id = sc_str.nextLine();
            if(!controller.productIdExists(id))
                valid = true;
            else
                out.println("Id already exists. Try again:\n");
        }
        return id;
    }

    /**
     * This method guarantees that the product name returned is valid and there will not exist duplicated product id
     * @return valid_name, String, unique name for product to be added
     */
    public String reqProductName(){
        boolean valid = false;
        String name = "";
        while(!valid){
            out.print("Type the name of the product: ");
            name = sc_str.nextLine();
            if(!controller.productNameExists(name))
                valid = true;
            else
                out.println("Name already exists. Try again:\n");
        }
        return name;
    }

    /**
     * This method guarantees that the waste identifier returned is valid and there will not exist duplicated values
     * @return valid_name, String, unique identifier for waste to be added
     */
    public String reqWasteId(){
        boolean valid = false;
        String id = "";
        while(!valid){
            out.print("Type the waste id: ");
            id = sc_str.nextLine();
            if(!controller.wasteIdExists(id)){
                valid = true;
            }
            else{
                out.println("Id already exists. Try again:\n");   
            }
        }
        return id;
    }

    /**
     * This method guarantees that the waste name returned is valid and there will not exist duplicated values
     * @return valid_name, String, unique name for waste to be added
     */
    public String reqWasteName(){
        boolean valid = false;
        String name = "";
        while(!valid){
            out.print("Type the name: ");
            name = sc_str.nextLine();
            if(!controller.wasteNameExists(name)){
                valid = true;
            }
            else{
                out.println("Name already exists. Try again:\n");
            }
        }
        return name;
    }

    /**
     * <b>post: </b> origin returned wil be valid and has to match with the specified rubric names
     * @return "industrials" or "domiciliaries" or "municipals" or "construction" or "hospitaller" origin for waste to be added
     */
    public String reqOrigin(){
        int select;
        String origin = "";
        while(origin.equals("")){
            out.print("Choose the origin of this waste: \n[1] Industrials\n[2] Domicilaries\n[3] Municipals\n[4] Constructions\n[5] Hospitilaries\nElection: ");
            select = sc_num.nextInt();
            switch(select){
                case 1: 
                    origin = Waste.IND;
                    break;
                case 2:
                    origin = Waste.DOM;
                    break;
                case 3: 
                    origin = Waste.MUN;
                    break;
                case 4:
                    origin = Waste.CON;
                    break;
                case 5:
                    origin = Waste.HSP;
                    break;
                default:
                    out.println("Invalid choice. Try again.");
                    break;
            }
        }
        return origin;
    }
// ================================================================= RF 4 and 5 =================================================================
    /**
     * <b>post: </b> information of waste that match name typed by user will be shown, if typed name doesn't exist, is notified to user 
     */
    public void findWasteByName(){
        out.print("Type the name of the searched waste: ");
        String name = sc_str.nextLine();
        if(controller.wasteNameExists(name)){
            String found_waste_info = controller.getWastes()[controller.searchWasteByName(name)].toString(); 
            out.println(found_waste_info);
        } else {
            out.println("ERROR: waste with name '"+name+"' doesn't exist");
        }
    }

    /**
     * <b>post: </b> information of wastes produced by products that match the product id typed by user will be shown, 
     * if typed identifier doesn't exist, issue is notified to user. 
     */
    public void findWastesByProductId(){
        out.print("Type the id of the product that you want to find its wastes: ");
        String product_id = sc_str.nextLine();
        if(controller.productIdExists(product_id)){
            Waste[] wastes_found = controller.getWastesOf(product_id);
            out.println("WASTES found for porduct with id: " + product_id);
            for(Waste waste : wastes_found){
                out.println(waste.toString());
            }
        } else {
            out.println("ERROR: product with id '" + product_id + "' doesn't exist.");
        }
    }

    // RF8
    /**
     * <b>pre: </b> controller class is encharged of returning the String with information to be shown
     * <b>post: </b> Wastes of product with id typed by user will be displayed in descendentially order by its harmful effect. 
     * If product id doesn't exist, user will be notified.
     */
    public void showSortedWasteByHarmfulEffectForProduct(){
        out.print("Type the id of the product that you want to find its wastes: ");
        String product_id = sc_str.nextLine();
        if(controller.productIdExists(product_id)){
            Waste[] wastes_found = controller.getWastesOf(product_id);
            out.println(controller.showWastesSortedByHarmfulEffect(wastes_found));
        } else {
            out.println("ERROR: product with id '" + product_id + "' doesn't exist.");
        }
    }
}