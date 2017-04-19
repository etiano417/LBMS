package UserInterface;

import LBMS.LBMS;
import Request.LogIn;
import Request.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a LogIn request
 *
 * Note: We do not need to check if someone is logged in or not. Once
 * we can load persistent data, logging in will be a prerequisite to
 * entering other commands rather than a command itself.
 */
public class Login implements UICommand{
    public String perform(List<String> params){

        String clientId = params.remove(0);
        if(params.size() < 2) {

            List<String> requiredParameters = new ArrayList<>();
            requiredParameters.add("username");
            requiredParameters.add("password");
            return MissingParameters.missingParameters(requiredParameters,params.size());
        }
        List<Object> result = new LogIn(clientId,params.get(0),params.get(1)).executeCommand();
        LBMS.ur.getUser(clientId).clearCommandStack();
        if(!result.isEmpty() && result.get(0) instanceof Problem){
            return String.format("%s,login,%s;",clientId,result.get(0));
        }
        return(String.format("%s,login,success;",clientId));
    }
}
