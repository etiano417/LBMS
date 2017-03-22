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

    //TODO needs to know if a visitor is already visiting when a visit occurs
    public List<Object> executeCommand(){
        List<Object> output = new ArrayList<>();

        LocalDateTime dt = LBMS.clock.getDateTime();
        String result = LBMS.vr.beginVisit(Integer.toString(visitorID),dt);

        if(result.equals("duplicate")){
            output.add(new Problem("duplicate","A visitor with the id is already visiting the library."));
            return output;
        }

        if(result.equals("invalid id")){
            output.add(new Problem("invalid-id","The specified visitor ID is not a valid ID or has not been assigned " +
                    "to any registered visitor."));
            return output;
        }

        output = new ArrayList<Object>();
        output.add(visitorID);
        output.add(dt.toLocalDate());
        output.add(dt.toLocalTime());
        return output;
    }
}
