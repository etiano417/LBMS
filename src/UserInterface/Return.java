package UserInterface;

import Request.Problem;
import Request.ReturnBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Tiano on 4/19/2017.
 */
public class Return implements UICommand{
    public String perform(List<String> params){
        String clientId = params.remove(0);

        ReturnBook rb = null;

        if(params.get(0).length() == 10){

            String visitorId = params.remove(0);

            List<Integer> ids = new ArrayList<>();

            for(String id : params){
                ids.add(Integer.parseInt(id));
            }

            rb = new ReturnBook(clientId,visitorId,ids);
        }else{
            List<Integer> ids = new ArrayList<>();

            for(String id : params){
                ids.add(Integer.parseInt(id));
            }

            rb = new ReturnBook(clientId,ids);
        }

        List<Object> result = rb.executeCommand();
        LBMS.LBMS.ur.getUser(clientId).pushToCommandStack(rb);

        if(result.size() == 0){
            return String.format("%s,return,success;",clientId);
        }

        if(result.get(0) instanceof Problem){
            return String.format("%s,return,%s;",clientId,result.get(0));
        }

        double fine = (double) result.remove(0);
        String ids = "";
        for(Object o : result){
            ids +=  "" + o + ",";
        }
        return String.format("%s,return,overdue,$%.2d,%s;",clientId,fine,ids);
    }
}
