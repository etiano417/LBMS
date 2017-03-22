package UserInterface;

import Request.BeginVisit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Records the beginning of a visit
 */
public class Arrive implements UICommand {
    public String perform(List<String> params){
        if(params.size() < 1){
            return "arrive,missing-parameters,visitor ID";
        }

        List<Object> results = new BeginVisit(params.get(0)).executeCommand();

        String id = (String) results.get(0);
        LocalDate visitDate = (LocalDate) results.get(1);
        LocalTime visitTime = (LocalTime) results.get(2);

        return String.format("arrive,%s,%02d/%02d/%d,%02d:%02d:%02d;",id,visitDate.getDayOfMonth(),
                visitDate.getMonthValue(), visitDate.getYear(), visitTime.getHour(), visitTime.getMinute(),
                visitTime.getSecond());
    }
}
