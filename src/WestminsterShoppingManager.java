import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {

    static Product[] productList = new Product[50];

    static File file = new File("products.txt");

    static Scanner input = new Scanner(System.in);

    // Constructor
    WestminsterShoppingManager() {

    }

    /**
     * Allows the user to add a product to the system by providing all the product details
     */
    @Override
    public void addProduct() {

        product_enter_loop:
        for (int i = 0; i < productList.length; i++) {

            if (productList[i] == null) {

                while_loop:
                while (true) {
                    System.out.println("Please Enter the type of product you want to add. \n\t 1) Electronics \n\t 2) Clothing \n");
                    int choice = Validation.optionValidation("Enter your option for the product type [or q to quit.]:");

                    switch (choice) {

                        case 1:

                            String electronicsID;

                            electronicsID = Validation.IDValidation(productList,"\nPlease enter the product ID [format : E12345 ] : ","E");

                            System.out.print("Please enter the product name : ");
                            String electronicsName = input.nextLine();

                            double electronicsPrice = Validation.doubleValidation("Please enter the product price : £");

                            int noOfElectronicsItems = Validation.integerValidation("Please enter the number of items : ");

                            System.out.print("Please enter the product brand : ");
                            String electronicsBrand = input.nextLine();


                            System.out.print("Please enter the product warranty period : ");
                            String electronicsWarranty = input.nextLine();

                            Product electronicItem = new Electronics(electronicsID, electronicsName, electronicsPrice, noOfElectronicsItems, electronicsBrand, electronicsWarranty);

                            productList[i] = electronicItem;
                            System.out.println("\nProduct has been added successfully!\n");

                            break while_loop;

                        case 2:
                            String clothingID;

                            clothingID = Validation.IDValidation(productList,"\nPlease enter the product ID [format : C12345 ]:","C");

                            System.out.print("Please enter the product name : ");
                            String clothingName = input.nextLine();

                            double clothingPrice = Validation.doubleValidation("Please enter the product price : £");

                            int clothingNoOfItems = Validation.integerValidation("Please enter the number of items : ");

                            System.out.print("Please enter the product colour : ");
                            String clothingColour = input.nextLine();

                            System.out.print("Please enter the product size : ");
                            String clothingSize = input.nextLine();

                            Clothing clothingItem = new Clothing(clothingID, clothingName, clothingPrice, clothingNoOfItems, clothingSize, clothingColour);

                            productList[i] = clothingItem;
                            System.out.println("\nProduct has been added successfully!\n");

                            break while_loop;

                        case 0, 100:
                            break product_enter_loop;

                        default:
                            System.out.println("Option not valid. Please try again.\n");

                    }
                }
            }
        }

    }

    /**
     * Allows users to delete products from the system by inputting the product ID
     */
    @Override
    public void deleteProduct() {

        while_loop:
        while (true) {
            System.out.print("Enter the product ID of the item you want to delete [or q to quit]: ");
            String ID = input.nextLine();

            if (Objects.equals(ID, "q")){
                break;
            }
            else{

                delete_loop:
                for(Product product : productList){

                    try{
                        if (product.getProductID().equals(ID) ){

                            int index = Arrays.asList(productList).indexOf(product);
                            System.out.println(product);
                            productList[index]=null;
                            System.out.println("The item has been successfully deleted");
                            long count = 50 - Arrays.stream(productList).filter(Objects::nonNull).count();
                            System.out.println("\n There are "+ count +" product slots available.");
                            break while_loop;
                        }


                    }
                    catch (NullPointerException e){

                        for(Product p : productList){
                            if(p != null){
                                continue delete_loop;
                            }
                        }
                        System.out.println("There are currently no products to delete.");
                        break while_loop;
                    }
                }
                System.out.println("This Product ID does not exist. Please Try again.");
            }

        }

    }


    /**
     * Prints a list of products on the console
     */
    @Override
    public void printProducts() {

        System.out.println("---------------");

        for (Product product : productList) {

            if (product instanceof Clothing) {
                System.out.println(product);
            }

        }
        for (Product product : productList) {

            if (product instanceof Electronics) {
                System.out.println(product);
            }
        }

        System.out.println("---------------");

    }

    /**
     * Save the products that are currently in the system to a text file
     */
    @Override
    public void saveToFile() {

        try {
            //Creates an empty file and returns true if the file was created successfully.
            boolean create_file = file.createNewFile();

            if (create_file) {
                System.out.println("The file has been created successfully!");
            }

            if (file.exists()) {

                FileWriter file_write = new FileWriter("products.txt", false);
                for (Product product : productList) {

                    if (product instanceof Clothing) {
                        file_write.write(product.getProductID()+"\n" +product.getProductName()+"\n"+
                                product.getPrice()+"\n"+ product.getNoOfItems()+"\n"+
                                ((Clothing) product).getColour()+"\n"+((Clothing) product).getSize());

                        file_write.write("\n");
                    }

                    if (product instanceof Electronics) {
                        file_write.write(product.getProductID()+ "\n" +product.getProductName()+"\n" +
                                product.getPrice()+ "\n" +product.getNoOfItems()+"\n" +
                                ((Electronics) product).getBrand()+"\n" +((Electronics) product).getWarrantyPeriod());

                        file_write.write("\n");
                    }
                }
                file_write.close();
            }

            System.out.println("The content has been written to the file successfully!");
        } catch (IOException e) {

            System.out.println("There was an error while creating the file. ");
        }
    }

    /**
     * Loads the list of products from the saved text file and restores the list in the system
     */
    public void load() {

        ArrayList<String> loadItem = new ArrayList<String>();
        int n = -1;

        if (file.exists()) {

            try {

                Scanner read_file = new Scanner(file);

                while (read_file.hasNextLine()){

                    for (int i=0 ; i<6; i++ ){
                        String  data = read_file.nextLine();  //Reads the  file line by line
                        loadItem.add(data);
                    }
                    n++;

                    if (loadItem.get(0).startsWith("E")){
                        Product electronic  = new Electronics(loadItem.get(0),loadItem.get(1),Double.parseDouble(loadItem.get(2)),Integer.parseInt(loadItem.get(3)), loadItem.get(4),loadItem.get(5) ) ;
                        productList[n] = electronic;
                    }

                    if (loadItem.get(0).startsWith("C")){
                        Product clothing  = new Clothing(loadItem.get(0),loadItem.get(1),Double.parseDouble(loadItem.get(2)),Integer.parseInt(loadItem.get(3)), loadItem.get(4),loadItem.get(5) ) ;
                        productList[n] = clothing;
                    }

                    loadItem.clear();
                }

                read_file.close();

            } catch (IOException e) {

                System.out.println("There was an error while reading the file.");
            }
        } else {
            System.out.println("This file does not exist. ");
        }
        System.out.println();
        System.out.println("The file has been loaded successfully! The products have been restored.");
        System.out.println("---------------------------------");
        System.out.println("The existing products are, ");
        //Calling the print products' method.
        printProducts();
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {

        WestminsterShoppingManager manager = new WestminsterShoppingManager();

        // Loads all the saved product information
        manager.load();
        System.out.println("\n");

        System.out.println("WELCOME TO THE WESTMINSTER SHOPPING MANAGER !");

        main_loop:
        while (true) {

            System.out.print("\n");

            System.out.println("-------------------------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Add a new Product");
            System.out.println("2) Delete a Product");
            System.out.println("3) Print List of Products");
            System.out.println("4) Save to file");
            System.out.println("5) Enable Customer Access");
            System.out.println("     0) Quit");
            System.out.println("-------------------------------------------------");

            int option = Validation.optionValidation("Enter option [or q to quit ]:");
            System.out.print("\n");

            switch (option) {

                case 1:
                    manager.addProduct();
                    break;

                case 2:
                    manager.deleteProduct();
                    break;

                case 3:
                    manager.printProducts();
                    break;

                case 4:
                    manager.saveToFile();
                    break;

                case 5:
                    new UserRegistration();
                    break main_loop;

                case 0, 100:
                    break main_loop;

                default:
                    System.out.println("The option entered is not correct.");

            }
        }

    }


}


