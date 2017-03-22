package UserInterface;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

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

        String visitorID = LBMS.vr.RegisterVisitor(params.get(0), params.get(1), params.get(2), params.get(3));

        return String.format("register,%s,%s;",visitorID,LBMS.clock.getDate());


    }
}
