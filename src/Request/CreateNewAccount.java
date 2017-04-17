package Request;

import LBMS.LBMS;
import UserRegistry.UserRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * A request to create a new user account
 */
public class CreateNewAccount implements Request{

    private String username;
    private String password;
    private boolean employee;
    private String visitorId;

    public CreateNewAccount(String _username, String _password, boolean _employee, String _visitorId){
        username = _username;
        password = _password;
        employee = _employee;
        visitorId = _visitorId;
    }

    public List<Object> executeCommand() {
        String result = LBMS.ur.addUser(username, password, visitorId, employee);

        List<Object> output = new ArrayList<>();

        if(result.equals(UserRegistry.E1)){
            output.add(new Problem(UserRegistry.E1,""));
            return output;
        } else if(result.equals(UserRegistry.E2)){
            output.add(new Problem(UserRegistry.E2,""));
            return output;
        } else if(!LBMS.vr.isInRegistry(visitorId)){
            output.add(new Problem("invalid-visitor",""));
        }
        return output;
    }
}
