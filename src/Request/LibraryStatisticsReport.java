package Request;

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
}
