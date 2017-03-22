package UserInterface;

import Request.AdvanceTime;

import java.util.List;

/**
 * Creates and executes an AdvanceTime request
 */
public class Advance implements UICommand{

    public String perform(List<String> params) {
        new AdvanceTime(Integer.parseInt(params.get(0)),Integer.parseInt(params.get(1))).executeCommand();
        return "advance,success;";
    }
}
