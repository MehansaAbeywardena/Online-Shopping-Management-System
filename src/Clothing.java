public class Clothing extends Product{

    private String size;
    private String colour;


    public Clothing(String productID, String productName, double price, int noOfItems, String size, String colour) {
        super(productID, productName, price, noOfItems);
        this. size = size;
        this. colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColor(String colour) {
        this.colour = colour;
    }

    public String toString() {
        return "Product Information\n\t" +
                "Product Type :"+ "Clothing "+"\n\t"+
                "Product ID = " + this.getProductID() + "\n\t" +
                "Product Name = " + this.getProductName() + "\n\t" +
                "Product price = " + this.getPrice() + "\n\t" +
                "Number of items = " + this.getNoOfItems() + "\n\t" +
                "Product Colour = " + this.colour + "\n\t" +
                "Product Size = " + this.size + "\n";
    }
}
