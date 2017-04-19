package UserRegistry;

import BookRegistry.Book;
import UserInterface.UICommand;

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
    private List<Long> borrowedBookOptions;

    //private Stack<UICommand> commandStack;
    //private Stack<UICommand> undoneCommandStack;

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
        if(selection - 1 < bookStoreOptions.size())
            return bookStoreOptions.get(selection - 1);
        return new Long(-1);
    }

    public Long selectLibraryBook(int selection){
        if(selection < libraryBookOptions.size())
            return libraryBookOptions.get(selection);
        return new Long(-1);
    }

    public Long selectBorrowedBook(int selection){
        if(selection - 1 < borrowedBookOptions.size()){
            return borrowedBookOptions.get(selection - 1);
        }
        return new Long (-1);
    }

    public void setStoreSelection(List<Long> bookSelection){
        bookStoreOptions = bookSelection;
    }

    public void setLibrarySelection(List<Long> bookSelection){
        libraryBookOptions = bookSelection;
    }

    public void setBorrowedSelection(List<Long> bookSelection) { borrowedBookOptions = bookSelection; }

}
