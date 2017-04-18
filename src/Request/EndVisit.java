package Request;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

/**
 * Records the time a visitor is ending a visit.
 */
public class EndVisit implements Request{
    String visitorID;

    public EndVisit(String _visitorID){
        visitorID = _visitorID;
    }

    public List<Object> executeCommand(){

        LocalDateTime endTime = LBMS.clock.getDateTime();

        LBMS.vr.endVisit(visitorID, endTime.toLocalTime());

        //LocalTime startTime = LBMS.vr.getVisiting(visitorID);

        //Duration length = Duration.between(startTime,endTime);

        List<Object> output = new ArrayList<>();

        output.add(visitorID);
        output.add(endTime);
        //output.add(length);

        return null;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        return output;
    }
}
