package ui;

import model.*;
import static java.lang.System.out;
import java.util.Scanner;

public class Main {
    private static Controller controller;
    private static Scanner sc_num;
    private static Scanner sc_str;

    public Main(){
        controller = new Controller();
        sc_str = new Scanner(System.in);
        sc_num = new Scanner(System.in);
    }

    public static void main(String[] args){
        Main program = new Main();
        out.println("WELCOME");
        out.println("To begin, please create first a product and later the waste that it produces.");
        program.registerProduct();
        out.println(program.controller.showProducts());
        program.registerWaste();
        out.println(program.controller.showRelations());
        out.println("Perfect, now, see the complete menu.");

        int election = 1;
        
        while(election != 10){
            out.print("WLECOME to the wastes manager. Choose: \n[1] To add a waste\n[2] To add a product \n[10] to exit\nElection: ");
            election = sc_num.nextInt();
            switch(election){
                case 1:
                    program.registerWaste();
                    break;
                case 2:
                    program.registerProduct();
                    break;
                case 10:
                    out.println("Goodbye");
                    break;
                default: 
                    out.println("Invalid option. Try again.");
                    break;
            }
            out.println(controller.showRelations());
        }
    }

    public void registerWaste(){
        out.println(controller.showProducts());
        String product_id = reqProductIdForAddingWaste();
        boolean valid_type_of_waste = false;
        char type_of_waste;
        while(!valid_type_of_waste){
            out.print("Choose the type of waste that the product you selected produces: \nB: Biodegradable\nR: Recyclable\nI: Inert\nChoose [B/R/I]: ");
            type_of_waste = sc_str.nextLine().toUpperCase().charAt(0);
            switch(type_of_waste){
                case 'B':
                    valid_type_of_waste = true;
                    // registerWasteB();
                    break;
                case 'R':
                    valid_type_of_waste = true;
                    registerWasteR(product_id);
                    break;
                case 'I': 
                    valid_type_of_waste = true;
                    // registerWasteI();
                    break;
                default:
                    out.println("Invalid choice. Choose 'B', 'R', or 'I'");
                    break;
            }
        }
    }

    public void registerWasteR(String product_id){
        String id, name, origin, color, type; 
        int decomposition_days;
        id = reqWasteId();
        name = reqWasteName();
        origin = reqOrigin();
        out.print("Color: ");
        color = sc_str.nextLine();
        out.print("Type: ");
        type = sc_str.nextLine();
        out.print("Decomposition days: ");
        decomposition_days = sc_num.nextInt();
        controller.addWasteR(id, name, origin, color, decomposition_days, type);
        controller.makeRelation(product_id);
    }

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
                }
            }
        }
        return valid_id;
    }

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
}