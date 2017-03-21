package Request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

/**
 * Records the time a visitor is beginning a visit.
 */
public class BeginVisit implements Request{
    int visitorID;

    public BeginVisit(int _visitorID){
        visitorID = _visitorID;
    }

    //needs to know if a visitor is already visiting when a visit occurs
    public List<Object> executeCommand(){
        LocalDateTime dt = LBMS.clock.getDateTime();
        LBMS.vr.beginVisit(visitorID,dt);

        List<Object> output = new ArrayList<Object>();
        output.add(visitorID);
        output.add(dt.toLocalDate());
        output.add(dt.toLocalTime());
        return output;
    }
}
