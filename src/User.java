import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String username;
    private String password;

    private ArrayList <Product> purchaseList;

    HashMap<String, String> registeredUsers = new HashMap<>();

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        registeredUsers.put(username, password);
    }

    public User() {
        purchaseList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
