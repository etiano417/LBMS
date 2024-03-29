package Request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;
import Library.Library;

/**
 * Records the time a visitor is beginning a visit.
 */
public class BeginVisit implements Request{
    String visitorID;

    public BeginVisit(String _visitorID){
        visitorID = _visitorID;
    }

    //TODO needs to know if a visitor is already visiting when a visit occurs
    public List<Object> executeCommand(){
        List<Object> output = new ArrayList<>();

        LocalDateTime dt = LBMS.clock.getDateTime();
        String result = LBMS.vr.beginVisit(visitorID,dt);

        if(result.equals("duplicate")){
            output.add(new Problem("duplicate","A visitor with the id is already visiting the library."));
            return output;
        }

        if(result.equals("invalid id")){
            output.add(new Problem("invalid-id","The specified visitor ID is not a valid ID or has not been assigned " +
                    "to any registered visitor."));
            return output;
        }

        if(!LBMS.library.isOpen()){
            output.add(new Problem("library-closed",""));
            return output;
        }

        output = new ArrayList<Object>();
        output.add(visitorID);
        output.add(dt.toLocalDate());
        output.add(dt.toLocalTime());
        return output;
    }

    public List<Object> undoCommand() {
        List<Object> output = new ArrayList<>();
        String result = LBMS.vr.undoBeginVisit(visitorID);
        if (result.equals("success")) {
            output.add("success");
        }
        return output;
    }

}
