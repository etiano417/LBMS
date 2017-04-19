package UserInterface;

import LBMS.LBMS;
import Request.EndVisit;
import Request.Problem;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * Creates and executes an EndVisit request
 */
public class Depart implements UICommand {

    public String perform(List<String> params) {

        String clientId = params.remove(0);

        List<Object> results = null;
        if(params.isEmpty()) {
            EndVisit ev = new EndVisit(clientId);
            //results = new EndVisit(clientId).executeCommand();
            results = ev.executeCommand();
            LBMS.ur.getUser(clientId).pushToCommandStack(ev);
        } else {
            EndVisit ev = new EndVisit(clientId, params.get(0));
            //results = new EndVisit(clientId,params.get(0)).executeCommand();
            results = ev.executeCommand();
            LBMS.ur.getUser(clientId).pushToCommandStack(ev);
        }

        if(results.get(0) instanceof Problem){
            return String.format("%s,depart,%s;",clientId,results.get(0));
        }

        LocalTime endTime = (LocalTime) results.get(1);

        Duration length = (Duration) results.get(2);

        long hours = length.toHours();
        length.minusHours(hours);

        long minutes = length.toMinutes();
        length.minusMinutes(minutes);

        long seconds = length.getSeconds();

        //String time = String.format("%02d:%02d:%02d",hours,minutes,seconds);
        String visitorId = (String) results.get(0);

        String output = String.format("%s,depart,%s,%02d:%02d:%02d,%02d:%02d:%02d;",clientId,visitorId,endTime.getHour(),
                endTime.getMinute(),endTime.getSecond(),hours,minutes,seconds);

        return output;
    }
}
