package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

/**
 * Pays outstanding fines for a user
 */
public class PayFine implements Request {
    private String visitorID;
    private int amount;

    public PayFine(String _clientId, int _amount, String _visitorID){
        visitorID = _visitorID;
        amount = _amount;
    }

    public PayFine(String _clientId, int _amount){
        visitorID = LBMS.ur.getVisitor(_clientId);
        amount = _amount;
    }

    public List<Object> executeCommand(){

        List<Object> output = new ArrayList<>();
        String result = LBMS.vr.payFine(visitorID, amount);
        if(!result.equals("success")){
            output.add(new Problem(result,""));
            return output;
        }

        output.add(LBMS.vr.getFine(visitorID));
        return output;

    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }
}
