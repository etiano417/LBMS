package Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Pays outstanding fines for a user
 */
public class PayFine implements Request {
    private String visitorID;
    private int amount;

    public PayFine(String _visitorID, int _amount){
        visitorID = _visitorID;
        amount = _amount;
    }

    public List<Object> executeCommand(){
        return null;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        return output;
    }
}
