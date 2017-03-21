package Request;

import java.util.List;

/**
 * Registers a new visitor in the system.
 */
public class RegisterVisitor implements Request {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public RegisterVisitor(String _firstName, String _lastName, String _address, String _phoneNumber){
        firstName = _firstName;
        lastName = _lastName;
        address = _address;
        phoneNumber = _phoneNumber;
    }

    //RegisterVisitor needs access to the visitor's id after registration
    public List<Object> executeCommand(){
        return null;
    }
}
