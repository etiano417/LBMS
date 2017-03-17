package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

/**
 *  Determines the current date and time.
 */
public class CurrentDateAndTime implements Request {
    /**
     * Returns the current date and time from the system clock as a LocalDateTime.
     *
     * @return the current date and time
     */
    public List<Object> executeCommand(){
        List<Object> output = new ArrayList<>();
        output.add(0,LBMS.clock.getDateTime());
        return output;
    }
}
