package UserInterface;

import LBMS.LBMS;
import Request.PayFine;
import Request.Problem;

import java.util.List;

/**
 * Created by Eric Tiano on 4/18/2017.
 */
public class Pay implements UICommand{
    public String perform(List<String> params){

        String clientId = params.remove(0);

        if(params.size() < 1){
            return String.format("%s,pay,missing-parameters,amount");
        }

        PayFine pf = null;

        int amount = Integer.parseInt(params.get(0));

        if(params.size() == 1){
            pf = new PayFine(clientId,amount);
        } else {
            String visitorId = params.get(1);
            //pf = new PayFine(clientId,amount,visitorId);
        }

        List<Object> result = pf.executeCommand();
        LBMS.ur.getUser(clientId).pushToCommandStack(pf);
        if(result.get(0) instanceof Problem){
            return String.format("%s,pay,%s;",clientId,result.get(0));
        }

        int remainingFine = (Integer) result.get(0);
        return String.format("%s,pay,$%d;",clientId,remainingFine);
    }
}
