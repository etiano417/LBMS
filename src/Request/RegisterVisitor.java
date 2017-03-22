package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

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

    //TODO handle duplicate user error
    public List<Object> executeCommand(){
        List<Object> output = new ArrayList<Object>();

        String id = LBMS.vr.RegisterVisitor(firstName,lastName,address,phoneNumber);

        output.add(id);
        output.add(LBMS.clock.getDate());

        return output;
    }
}
