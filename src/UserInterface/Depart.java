package UserInterface;

import Request.EndVisit;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * Creates and executes an EndVisit request
 */
public class Depart implements UICommand {

    public String perform(List<String> params) {
        List<Object> results = new EndVisit(params.get(0)).executeCommand();

        LocalTime endTime = (LocalTime) results.get(1);

        Duration length = (Duration) results.get(2);

        long hours = length.toHours();
        length.minusHours(hours);

        long minutes = length.toMinutes();
        length.minusMinutes(minutes);

        long seconds = length.getSeconds();

        String time = String.format("%02d:%02d:%02d",hours,minutes,seconds);

        String output = String.format("depart,%s,%02d:%02d:%02d,%02d:%02d:%02d;",params.get(0),endTime.getHour(),
                endTime.getMinute(),endTime.getSecond(),hours,minutes,seconds);

        return output;
    }
}
