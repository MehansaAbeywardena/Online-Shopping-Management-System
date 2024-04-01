public abstract class Product {

    private String productID ;
    private String productName;

    private double price;
    private int noOfItems ;


    public Product(String productID, String productName, double price, int noOfItems) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.noOfItems = noOfItems;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }


}
