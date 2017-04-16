package UserRegistry;

import BookRegistry.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single user account in the system
 */
public class User {

    //private String username;
    private String password;
    private String id;
    private boolean employee;
    private List<Long> bookStoreOptions;
    private List<Long> libraryBookOptions;

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
        bookStoreOptions = new ArrayList<>();
        libraryBookOptions = new ArrayList<>();
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

    public Long selectStoreBook(int selection){
        return bookStoreOptions.get(selection);
    }

    public Long selectLibraryBook(int selection){
        return bookStoreOptions.get(selection);
    }

    public void setStoreSelection(List<Long> bookSelection){
        bookStoreOptions = bookSelection;
    }

    public void setLibrarySelection(List<Long> bookSelection){
        libraryBookOptions = bookSelection;
    }
}
