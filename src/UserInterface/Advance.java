package UserInterface;

import Request.AdvanceTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and executes an AdvanceTime request
 */
public class Advance implements UICommand{

    public String perform(List<String> params) {
        if(params.size() < 2) {
            List<String> requiredParams = new ArrayList<>();
            requiredParams.add("number-of-days");
            requiredParams.add("number-of-hours");

            return "advance," + MissingParameters.missingParameters(requiredParams, params.size());
        }

        new AdvanceTime(Integer.parseInt(params.get(0)),Integer.parseInt(params.get(1))).executeCommand();
        return "advance,success;";
    }
}
