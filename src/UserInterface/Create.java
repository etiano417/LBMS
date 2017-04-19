package UserInterface;

import Request.CreateNewAccount;
import Request.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a CreateNewAccount command
 */
public class Create implements UICommand {
    public String perform(List<String> params){
        //removes client ID
        String clientId = params.remove(0);

        if(params.size()<4) {
            List<String> requiredParams = new ArrayList<>();
            requiredParams.add("username");
            requiredParams.add("password");
            requiredParams.add("role");
            requiredParams.add("visitor id");

            return MissingParameters.missingParameters(requiredParams,params.size(),clientId);
        }

        boolean employee = false;
        if(params.get(2).equals("employee")){
            employee = true;
        }else if(!params.get(2).equals("visitor")){
            return "create,missing-parameters,role";
        }

        String username = params.get(0);
        String password = params.get(1);
        String visitorId = params.get(3);
        CreateNewAccount cna = new CreateNewAccount(username,password,employee,visitorId);
        List<Object> result = cna.executeCommand();
        //LBMS.LBMS.ur.getUser(clientId).clearCommandStack();
        if(!(result.isEmpty()) && result.get(0) instanceof Problem){
            return clientId + ",create," + ((Problem) result.get(0)).getType()+";";
        }

        return clientId + ",create,success;";
    }
}
