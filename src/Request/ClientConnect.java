package Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import LBMS.LBMS;

/**
 * A request to connect to the system.
 */
public class ClientConnect implements Request{

    /**
     * Connects the client to the system, assigning the client an ID
     * @return the client ID
     */
    public List<Object> executeCommand(){
        List<Object> output = new ArrayList<>();
        output.add(LBMS.ur.connect());
        return output;
    }

}
