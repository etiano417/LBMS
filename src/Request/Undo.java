package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

public class Undo implements Request{

    private String clientId;

    public Undo(String _clientId){
        clientId = _clientId;
    }

    public List<Object> executeCommand() {
        boolean result = LBMS.ur.undo(clientId);

        List<Object> output = new ArrayList<>();
        output.add(result);

        return output;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }

}
