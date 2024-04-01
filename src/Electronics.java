public class Electronics extends Product{

    private String brand ;
    private String warrantyPeriod;

    public Electronics(String productID, String productName, double price, int noOfItems, String brand , String warrantyPeriod) {
        super(productID, productName, price, noOfItems);
        this. warrantyPeriod = warrantyPeriod;
        this. brand = brand ;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }



    public String toString() {
        return "Product Information\n\t" +
                "Product Type :"+ "Electronics "+"\n\t"+
                "Product ID = " + this.getProductID() + "\n\t" +
                "Product Name = " + this.getProductName() + "\n\t" +
                "Product price = " + this.getPrice() + "\n\t" +
                "Number of items = " + this.getNoOfItems() + "\n\t" +
                "Product Brand= " + this.brand + "\n\t" +
                "Product Warranty Period = " + this.warrantyPeriod + "\n";
    }
}
