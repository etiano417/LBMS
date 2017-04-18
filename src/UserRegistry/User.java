package UserRegistry;

import BookRegistry.Book;
//import UserInterface.UICommand;
import Request.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    private Stack<Request> commandStack;
    private Stack<Request> undoneCommandStack;

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

        commandStack = new Stack<Request>();
        undoneCommandStack = new Stack<Request>();
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

    public void setStoreSelection(List<Long> bookSelection){
        bookStoreOptions = bookSelection;
    }

    public void setLibrarySelection(List<Long> bookSelection){
        libraryBookOptions = bookSelection;
    }

    private void undo(){
        Request request = commandStack.pop();
        request.undoCommand();
        undoneCommandStack.push(request);
    }

    private void redo(){
        Request request = undoneCommandStack.pop();
        request.executeCommand();
        commandStack.push(request);
    }

    private void clearCommandStackS(){
        commandStack.clear();
        undoneCommandStack.clear();
    }

    private void pushToCommandStack(Request request){
        commandStack.push(request);
        undoneCommandStack.clear();
    }

}
