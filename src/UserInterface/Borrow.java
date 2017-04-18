package UserInterface;

import Request.BorrowBook;
import Request.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Tiano on 4/16/2017.
 */
public class Borrow implements UICommand {
    public String perform(List<String> params){
        String clientId = params.remove(0);

        BorrowBook bb;
        if(params.get(params.size() - 1).length() == 10){
            String visitorId = params.remove(params.size() - 1);
            bb = new BorrowBook(clientId,stringToInteger(params),visitorId);
        } else {
            bb = new BorrowBook(clientId,stringToInteger(params));
        }

        List<Object> result = bb.executeCommand();

        if(result.get(0) instanceof Problem){
            return String.format("%s,borrow,%s;",clientId,result.get(0));
        }

        return String.format("%s,borrow,%s;",clientId,result.get(0));
    }

    private List<Integer> stringToInteger(List<String> input){
        List<Integer> output = new ArrayList<>();
        for(String s : input){
            output.add(Integer.parseInt(s));
        }
        return output;
    }
}
