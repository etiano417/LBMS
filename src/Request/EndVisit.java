package Request;

import java.util.List;

/**
 * Records the time a visitor is ending a visit.
 */
public class EndVisit implements Request{
    int visitorID;

    public EndVisit(int _visitorID){
        visitorID = _visitorID;
    }

    public List<Object> executeCommand(){
        //LBMS.vr.endVisit(visitorID);
        return null;
    }
}
