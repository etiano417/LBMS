package UserInterface;

import java.util.List;
import LBMS.LBMS;
import Request.SafeShutDown;

/**
 * Created by Eric Tiano on 4/19/2017.
 */
public class ShutDown implements UICommand{

    public String perform(List<String> params){

        String clientId = params.remove(0);
        if(!LBMS.ur.isEmployee(clientId)){
            return EmployeeChecking.message(clientId,"shutdown");
        }

        new SafeShutDown().executeCommand();
        System.exit(0);
        return null;
    }
}
