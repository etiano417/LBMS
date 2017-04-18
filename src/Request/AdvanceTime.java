package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

/**
 * A request to advance the system's clock forward
 */
public class AdvanceTime implements Request {

    public int numHours;

    public AdvanceTime(int _numDays, int _numHours){
        numHours = 24 * _numDays + _numHours;
    }


    public List<Object> executeCommand(){

        LBMS.clock.moveForward(numHours);

        return new ArrayList<Object>();
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }

}
