package Request;

import java.util.List;

/**
 * A request to advance the system's clock forward
 */
public class AdvanceTime implements Request {

    public int numHours;

    public List<Object> executeCommand(){
        return null;
    }

}
