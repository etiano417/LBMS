package Request;

import java.util.List;

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
        //if(){
        //    return null;
        return null;
    }
}
