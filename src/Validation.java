import java.util.InputMismatchException;


public class Validation {


    /**
     * Validates the inputs taken and checks if the input is an integer.
     * @param msg the prompt that is displayed to the user
     * @return a valid integer entered by the user or the value 100 if the input is "q"
     */
    public static int optionValidation(String msg) {

        String user_opt;
        int user_opt_int = 100;

        while (true) {

            try {

                System.out.print(msg);

                user_opt = WestminsterShoppingManager.input.nextLine().toLowerCase();

                if (user_opt.equals("q")) { //This method will let the user enter the letter "q" when taking inputs .
                    break;
                } else {
                    user_opt_int = Integer.parseInt(user_opt);
                }

                break;
            } catch (InputMismatchException | NumberFormatException e) {

                System.out.println("Please Enter an integer.\n");

            }
        }

        return user_opt_int;
    }

    /**
     * Validates the inputs taken and checks if the input is an integer
     * @param msg the prompt that is displayed to the user
     * @return the valid integer entered by the user
     */
    public static int integerValidation(String msg){

        int  value = 0;

        while (true){

            try {
                System.out.print(msg);
                value = Integer.valueOf(WestminsterShoppingManager.input.nextLine());
                break;
            }
            catch (InputMismatchException | NumberFormatException e ){
                System.out.println("Please Enter an integer.");
            }
        }
        return value ;
    }

    /**
     * Validates the inputs taken and checks if the input is a double
     * @param msg the prompt that is displayed to the user
     * @return the valid double entered by the user
     */
    public static double doubleValidation(String msg){

        double value = 0;

        while (true){

            try {
                System.out.print(msg);
                value = Double.valueOf(WestminsterShoppingManager.input.nextLine());

                break;
            }
            catch (InputMismatchException | NumberFormatException e ){
                System.out.println("Please enter a valid price.");
            }
        }
        return value ;
    }

    /**
     * Validates whether the ID entered by the user exists and is of the correct format
     * @param productList the list of products
     * @param msg the prompt that is displayed to the user
     * @param condition the condition to determine whether the ID begins with an E or a C
     * @return the valid ID entered by the user
     */
    public static String IDValidation (Product [] productList,String msg, String condition){

        String ID = " ";

        check_id_loop:
        while (true) {
            System.out.print(msg);
            ID = WestminsterShoppingManager.input.nextLine();

            if (ID.startsWith(condition)) {

                for (Product product : productList) {

                    try{

                        if (ID.equals(product.getProductID())) {
                            System.out.println("This Id already exists. Please enter another ID.");
                            continue check_id_loop;
                        }
                    }
                    catch (NullPointerException e){
                    }
                }
                break;
            }
            else {
                System.out.println("Please enter the product ID in the given format.\n");
            }
        }
        return ID;
    }

}
