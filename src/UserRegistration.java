import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistration extends JFrame{

    private JPasswordField passwordInput;
    private JButton registerButton;
    private JTextField usernameInput;

    // Constructor
    public UserRegistration(){

        //Add the components
        JLabel username = new JLabel("Username:");
        username.setBounds(10, 20, 80, 25);
        add(username);

        JLabel password = new JLabel("Password:");
        password.setBounds(10, 50, 80, 25);
        add(password);

        usernameInput = new JTextField(20);
        usernameInput.setBounds(100, 20, 165, 25);
        add(usernameInput);

        passwordInput = new JPasswordField(20);
        passwordInput.setBounds(100, 50, 165, 25);
        add(passwordInput);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 100, 100, 25);
        registerButton.addActionListener(new ButtonClickHandler());
        add(registerButton);

        // Set the frame
        setSize(300, 200);
        setLayout(null);
        setTitle("User Registration");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private class ButtonClickHandler implements ActionListener{
        /**
         * Handles the press of the register button by validating the input fields and creating a new user object
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            String username = usernameInput.getText();
            String password = String.valueOf(passwordInput.getPassword());

            if(username.isBlank()){
                JOptionPane.showMessageDialog(UserRegistration.this, "Username cannot be empty.");
            }
            else if (password.isBlank()) {
                JOptionPane.showMessageDialog(UserRegistration.this, "Password cannot be empty.");
            }
            else{
                new User(username, password);
                JOptionPane.showMessageDialog(UserRegistration.this, "Registration Successful!");
                new ShoppingCentre();
                dispose();
            }
        }
    }

}