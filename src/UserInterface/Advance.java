package UserInterface;

import LBMS.LBMS;
import Request.AdvanceTime;
import Request.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and executes an AdvanceTime request
 */
public class Advance implements UICommand{

    public String perform(List<String> params) {

        String clientId = params.remove(0);

        if(params.size() < 2) {
            List<String> requiredParams = new ArrayList<>();
            requiredParams.add("number-of-days");
            requiredParams.add("number-of-hours");

            return String.format("%s,advance,%s",clientId,
                    MissingParameters.missingParameters(requiredParams,params.size()));
        }

        int days = 0;
        int hours = 0;

        try {
            days = Integer.parseInt(params.get(0));
        } catch (NumberFormatException e){
            return String.format("%s,invalid-number-of-days,%s",clientId,params.get(0));
        }

        try {
            hours = Integer.parseInt(params.get(1));
        } catch (NumberFormatException e){
            return String.format("%s,invalid-number-of-hours,%s",clientId,params.get(1));
        }

        if(days < 0 || days > 7){
            return String.format("%s,invalid-number-of-days,%s",clientId,params.get(0));
        }

        if(hours < 0 || hours > 23 || (hours == 0 && days == 0)){
            return String.format("%s,invalid-number-of-hours,%s",clientId,params.get(1));
        }

        new AdvanceTime(days,hours).executeCommand();
        //LBMS.ur.getUser(clientId).clearCommandStack();
        return String.format("%s,advance,success;",clientId);
    }
}
