package Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static LBMS.LBMS.ur;

/**
 * A class to protect commands from users that are not logged in
 */
public class UserCommand implements Request {

    Request request;
    String clientId;

    public UserCommand(String _clientId, Request _request){
        request = _request;
        clientId = _clientId;
    }

    /**
     * checks to see if the user has authority to perform the command, and fails if they do not.
     * @return
     */
    public List<Object> executeCommand() {
        if(ur.isUser(clientId)){
            return request.executeCommand();
        } else {
            return new ArrayList<Object>();
        }
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }
}
