package Request;

import LBMS.LBMS;

import java.util.ArrayList;
import java.util.List;

public class Redo implements Request{

    private String clientId;

    public Redo(String _clientId){
        clientId = _clientId;
    }

    public List<Object> executeCommand() {
        boolean result = LBMS.ur.redo(clientId);

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
