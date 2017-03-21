package Request;

import java.util.List;

/**
 * Records the time a visitor is beginning a visit.
 */
public class BeginVisit implements Request{
    int visitorID;

    public BeginVisit(int _visitorID){
        visitorID = _visitorID;
    }

    //TODO
    public List<Object> executeCommand(){
        //LBMS.vr.beginVisit(visitorID);
        return null;
    }
}
