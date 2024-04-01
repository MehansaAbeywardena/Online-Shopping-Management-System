import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ShoppingCentre extends JFrame{

    private JComboBox<String> categoryBox;
    private JTable table;
    private CustomTableModel tableModel;
    private JLabel productIDLabel;
    private JLabel categoryLabel;
    private JLabel nameLabel;
    private JLabel sizeLabel;
    private JLabel colourLabel;
    private JLabel itemAvailableLabel;
    private ShoppingCart shoppingCart;

    public ShoppingCentre() {


        // Add labels for the product catergory
        JLabel productCategoryLabel = new JLabel("Select Product Category");
        productCategoryLabel.setBounds(60, 20, 200, 25); // similar to a layout/ can be used as long as the layout of that particular container in null
        add(productCategoryLabel);

        // Category selector
        String[] categories = {"All", "Electronics", "Clothing"};
        categoryBox = new JComboBox<>(categories);
        categoryBox.addActionListener(new UpdateTableData());
        categoryBox.setBounds(230, 20, 165, 25);
        add(categoryBox);

        // Button to open shopping cart
        JButton cartButton = new JButton("Shopping Cart");
        cartButton.setBounds(634, 5, 90, 30);
        cartButton.setMargin(new Insets(0, 0, 0, 0));
        cartButton.setFocusable(false);

        // Event lister for the cart button opens the Shopping Cart page when clicked.
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCart = new ShoppingCart();
            }
        });

        add(cartButton);

        // Table of data
        tableModel = new CustomTableModel();
        table = new JTable(tableModel);

        // Allow column sorting
        table.setRowSorter(new TableRowSorter<>(tableModel));
        table.addMouseListener(new RowDetails());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 120, 704, 200);
        add(scrollPane);

        // Product details when a row is clicked
        JLabel selectedProductLabel = new JLabel("Selected Product - Details");
        selectedProductLabel.setBounds(60, 420, 200, 25);
        add(selectedProductLabel);

        productIDLabel = new JLabel("ProductID");
        productIDLabel.setBounds(60, 445, 200, 25);
        add(productIDLabel);

        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(60, 470, 200, 25);
        add(categoryLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(60, 495, 200, 25);
        add(nameLabel);

        sizeLabel = new JLabel("Size");
        sizeLabel.setBounds(60, 520, 200, 25);
        add(sizeLabel);

        colourLabel = new JLabel("Colour");
        colourLabel.setBounds(60, 545, 200, 25);
        add(colourLabel);

        itemAvailableLabel = new JLabel("Items available");
        itemAvailableLabel.setBounds(60, 570, 200, 25);
        add(itemAvailableLabel);

        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.setBounds(302, 610, 130, 30);
        addToCartButton.setMargin(new Insets(0, 0, 0, 0));
        addToCartButton.setFocusable(false);
        addToCartButton.addActionListener(new AddToCart());
        add(addToCartButton);

        setLayout(null); // get to use setBounds
        setTitle("Westminster Shopping Centre");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 700);
        setVisible(true);
    }


    /**
     * Filters the products corresponding to the type specified
     * @param type the type of product to be filtered
     * @return list of filtered products
     */
    public static ArrayList<Product> filterProducts(String type){

        ArrayList <Product> filteredProducts = new ArrayList<>();

        for (Product product: WestminsterShoppingManager.productList) {

            // Avoid possible null indexes in the productList array
            if(product!= null){
                if (type.equals("Clothing")) {
                    if(product instanceof Clothing){
                        filteredProducts.add(product);
                    }
                }

                else if (type.equals("Electronics")) {
                    if(product instanceof Electronics){
                        filteredProducts.add(product);
                    }
                }

                else {
                    filteredProducts.add(product);
                }
            }
        }
        return filteredProducts;
    }

    private class UpdateTableData implements ActionListener{

        /**
         * Handles filtering the products in the table based on the category selected in the category selector
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            String type = (String) categoryBox.getSelectedItem();
            CustomTableModel tableModel = (CustomTableModel) table.getModel();
            tableModel.updateData(type);

        }
    }

    private class CustomTableModel extends AbstractTableModel{

        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Info"};
        ArrayList<Product> showFilteredData ;

        // Constructor
        public CustomTableModel(){
            updateData((String) categoryBox.getSelectedItem());

        }

        /**
         * Updates the data for the table when products in the table are filtered. Fires an update to the JTable as well.
         * @param type the type of products to be filtered
         */
        public void updateData(String type) {
            showFilteredData = filterProducts(type);
            fireTableDataChanged();
        }

        /**
         * Retrieves the number of rows in the table
         * @return number of rows
         */
        @Override
        public int getRowCount() {
            return (showFilteredData != null) ? showFilteredData.size() : 0;
        }

        /**
         * Retrieves the number of columns in the table
         * @return number of columns
         */
        @Override
        public int getColumnCount() {
            return columnNames.length ;

        }

        /**
         * Gets the column name for the corresponding column index
         * @param colIndex  the column being queried
         * @return the column name
         */
        @Override
        public String getColumnName(int colIndex){
            return columnNames[colIndex];
        }

        /** Retrieves appropriate product details to add to the table
         * @param rowIndex the row whose value is to be queried
         * @param columnIndex the column whose value is to be queried
         * @return relevant product details as a string object
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0 ){
                return showFilteredData.get(rowIndex).getProductID();
            }

            else if (columnIndex == 1 ){
                return showFilteredData.get(rowIndex).getProductName();
            }

            else if (columnIndex == 2 ){
                if (showFilteredData.get(rowIndex) instanceof Electronics){
                    return "Electronics";
                }
                else if (showFilteredData.get(rowIndex) instanceof Clothing){
                    return "Clothing";
                }
            }

            else if (columnIndex == 3 ){
                return showFilteredData.get(rowIndex).getPrice();
            }

            else{
                if (showFilteredData.get(rowIndex) instanceof Electronics){
                    return ((Electronics) showFilteredData.get(rowIndex)).getBrand()+","+((Electronics) showFilteredData.get(rowIndex)).getWarrantyPeriod();
                }

                else if (showFilteredData.get(rowIndex) instanceof Clothing){
                    return ((Clothing) showFilteredData.get(rowIndex)).getSize()+","+((Clothing) showFilteredData.get(rowIndex)).getColour();
                }
            }

            return null;
        }
    }

    private class RowDetails implements MouseListener {

        /**
         * Handles the event of clicking a row in the table
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {

            // Retrieves the product the row is referring to.
            Product product = tableModel.showFilteredData.get(table.convertRowIndexToModel(table.getSelectedRow()));

            // Update all the labels to display the product information
            productIDLabel.setText("Product ID : "+product.getProductID());
            if (product instanceof Clothing){
                categoryLabel.setText("Category : Clothing ");
                sizeLabel.setText("Size : "+ ((Clothing) product).getSize());
                colourLabel.setText("Colour : "+ ((Clothing) product).getColour());
            }
            else{
                categoryLabel.setText("Category : Electronics ");
                sizeLabel.setText("Brand : "+ ((Electronics) product).getBrand());
                colourLabel.setText("Warranty Period : "+ ((Electronics) product).getWarrantyPeriod());
            }
            nameLabel.setText("Name : " + product.getProductName());
            itemAvailableLabel.setText("Items Available : "+ product.getNoOfItems());

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class AddToCart implements ActionListener {

        /**
         * Handles clicks on the add to cart button by adding the selected products to the shopping cart
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = tableModel.showFilteredData.get(table.convertRowIndexToModel(table.getSelectedRow()));
            if (product != null) {
                shoppingCart.addItem(product);
            }
        }
    }


}