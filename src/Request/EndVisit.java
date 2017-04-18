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

    public EndVisit(String _clientId, String _visitorID){
        visitorID = _visitorID;
    }

    public EndVisit(String _clientId){
        visitorID = LBMS.ur.getVisitor(_clientId);
    }

    public List<Object> executeCommand(){

        List<Object> output = new ArrayList<>();

        if(!LBMS.vr.isVisiting(visitorID)){
            output.add(new Problem("invalid-id",""));
            return output;
        }

        LocalDateTime endTime = LBMS.clock.getDateTime();

        LocalTime startTime = LBMS.vr.getVisiting(visitorID);

        LBMS.vr.endVisit(visitorID, endTime.toLocalTime());

        Duration length = Duration.between(startTime,endTime);

        output.add(visitorID);
        output.add(endTime.toLocalTime());
        output.add(length);

        return output;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        return output;
    }
}
