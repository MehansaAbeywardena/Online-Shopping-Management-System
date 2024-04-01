import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ShoppingCart extends JFrame {

    private CustomCartTableModel tableModel;

    // Constructor
    public ShoppingCart() {

        setSize(750, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setTitle("Shopping Cart");

        // Table
        tableModel = new CustomCartTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 25, 634, 200);
        add(scrollPane);

        JLabel totalLabel = new JLabel("Total");
        totalLabel.setBounds(460, 240, 30, 25);
        add(totalLabel);

        JLabel firstPurchaseDiscountLabel = new JLabel("First Purchase Discount (10%)");
        firstPurchaseDiscountLabel.setBounds(320, 275, 170, 25);
        add(firstPurchaseDiscountLabel);

        JLabel threeTimeDiscountLabel = new JLabel("Three Items in Same Category Discount (20%)");
        threeTimeDiscountLabel.setBounds(232, 310, 260, 25);
        add(threeTimeDiscountLabel);

        JLabel finalTotalLabel = new JLabel("Final total");
        finalTotalLabel.setBounds(435, 360, 90, 25);
        add(finalTotalLabel);

        JLabel total = new JLabel("85.79 £");
        total.setBounds(570, 240, 60, 25);
        add(total);

        JLabel firstPurchaseDiscount = new JLabel("-8.58 £");
        firstPurchaseDiscount.setBounds(570, 275, 60, 25);
        add(firstPurchaseDiscount);

        JLabel threeTimeDiscount = new JLabel("-17.16 £");
        threeTimeDiscount.setBounds(570, 310, 60, 25);
        add(threeTimeDiscount);

        JLabel finalTotal = new JLabel("60.05 £");
        finalTotal.setBounds(570, 360, 60, 25);
        add(finalTotal);
    }

    /**
     * Adds a product to the shopping cart
     *
     * @param item product to be added
     */
    public void addItem(Product item) {
        tableModel.shoppingCartData.add(item);
        JOptionPane.showMessageDialog(ShoppingCart.this, item.getProductName() + " has been added to your cart successfully!");
    }

    private class CustomCartTableModel extends AbstractTableModel {

        String[] columnNames = {"Product", "Quantity", "Price"};
        ArrayList<Product> shoppingCartData = new ArrayList<>();

        // Constructor
        public CustomCartTableModel() {

        }

        /**
         * Retrieves the number of rows in the table
         *
         * @return number of rows
         */
        @Override
        public int getRowCount() {
            return shoppingCartData.size();
        }

        /**
         * Retrieves the number of columns in the table
         *
         * @return number of columns
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * Gets the column name for the corresponding column index
         *
         * @param colIndex the column being queried
         * @return the column name
         */
        @Override
        public String getColumnName(int colIndex) {
            return columnNames[colIndex];
        }

        /**
         * Retrieves appropriate product details to add to the table
         *
         * @param rowIndex    the row whose value is to be queried
         * @param columnIndex the column whose value is to be queried
         * @return relevant product details as a string object
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                if (shoppingCartData.get(rowIndex) instanceof Electronics) {
                    return shoppingCartData.get(rowIndex).getProductID() + "\n" + shoppingCartData.get(rowIndex).getProductName() + "\n" + ((Electronics) shoppingCartData.get(rowIndex)).getBrand() + ", " + ((Electronics) shoppingCartData.get(rowIndex)).getWarrantyPeriod();
                } else {
                    return shoppingCartData.get(rowIndex).getProductID() + "\n" + shoppingCartData.get(rowIndex).getProductName() + "\n" + ((Clothing) shoppingCartData.get(rowIndex)).getSize() + ", " + ((Clothing) shoppingCartData.get(rowIndex)).getColour();
                }
            } else if (columnIndex == 1) {
                return "Quantity";
            } else if (columnIndex == 2) {
                return shoppingCartData.get(rowIndex).getPrice();
            }

            return null;
        }
    }

}