package Request;

import java.util.ArrayList;
import java.util.List;

/**
 * returns a report of library information
 */
public class LibraryStatisticsReport implements Request{

    private int days;

    public LibraryStatisticsReport(int _days){
        days = _days;
    }

    public List<Object> executeCommand() {
        return null;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }
}
