package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

    /**
     * Created by Eric Tiano on 4/18/2017.
     */
    public class LogOut implements Request {

    String clientId;

    public LogOut(String _clientId){
        clientId = _clientId;
    }

    public List<Object> executeCommand(){
        //LBMS.ur.logOut(clientId);
        return new ArrayList<>();
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }
}
