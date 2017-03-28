package UserInterface;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;
import Request.Problem;
import Request.RegisterVisitor;

/**
 * Creates a RegisterVisitor request
 */
public class Register implements UICommand {
    public String perform(List<String> params){

        if(params.size() < 4) {
            List<String> requiredParams = new ArrayList<>();
            requiredParams.add("first name");
            requiredParams.add("last name");
            requiredParams.add("address");
            requiredParams.add("phone number");

            return "register," + MissingParameters.missingParameters(requiredParams, params.size());
        }

        List<Object> output = new RegisterVisitor(params.get(0), params.get(1), params.get(2), params.get(3)).executeCommand();
        //String visitorID = LBMS.vr.RegisterVisitor(params.get(0), params.get(1), params.get(2), params.get(3));
        if(output.get(0) instanceof Problem){
            return String.format("register,%s;",((Problem) output.get(0)).getType());
        }
        return String.format("register,%s,%s;",output.get(0),LBMS.clock.getDate());


    }
}
