package UserRegistry;

/**
 * Represents a single user account in the system
 */
public class User {

    private String username;
    private String password;
    private String id;
    private boolean employee;

    /**
     * Constructs a new user object
     *
     * @param _password the user's password
     * @param _id the id of the user's visitor
     * @param _employee whether the user is an employee (or a visitor)
     */
    public User(String _password, String _id, boolean _employee){
        password = _password;
        id = _id;
        employee = _employee;

    }

    public User(){
        password = "";
        id = "";
        employee = false;
    }

    public boolean isPassword(String _password){
        return password.equals(_password);
    }

    public String getId(){
        return id;
    }

    public boolean isEmployee(){
        return employee;
    }

    public String toString(){
        return id;
    }
}
