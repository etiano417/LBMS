package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

/**
 * A request to log into a user account
 */
public class LogIn implements Request {

    private String clientId;
    private String username;
    private String password;

    public LogIn(String _clientId, String _username, String _password){
        clientId = _clientId;
        username = _username;
        password = _password;
    }

    public List<Object> executeCommand(){
        List<Object> output = new ArrayList<>();
        if(!LBMS.ur.logIn(clientId,username,password)){
            output.add(new Problem("bad-username-or-password",""));
        }
        return output;
    }
}
