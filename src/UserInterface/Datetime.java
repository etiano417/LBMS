package UserInterface;

import LBMS.LBMS;
import Request.CurrentDateAndTime;
import Request.Request;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The command for retrieving the system's Date and Time.
 */
public class Datetime implements UICommand {

    /**
     * Creates a CurrentDateAndTime Request, without reference to any parameters given. The current date and time is
     * then returned in the following format:
     *
     *   datetime,dd/mm/yy,hh:mm:ss;
     *
     * @param params input parameters
     * @return current date and time
     */
    public String perform(List<String> params){
        //removes the client id
        String clientId = params.remove(0);
        Request cdat = new CurrentDateAndTime();
        LocalDateTime dt = (LocalDateTime) cdat.executeCommand().get(0);
        //LBMS.ur.getUser(clientId).clearCommandStack();
        LBMS.ur.clearDone(clientId);
        return String.format("%s,datetime,%02d/%02d/%d,%02d:%02d:%02d;",clientId,dt.getDayOfMonth(),dt.getMonthValue(),dt.getYear(),
                dt.getHour(),dt.getMinute(),dt.getSecond());
    }
}
