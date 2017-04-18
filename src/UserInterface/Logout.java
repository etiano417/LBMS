package UserInterface;

import Request.LogOut;

import java.util.List;

/**
 * Created by Eric Tiano on 4/18/2017.
 */
public class Logout implements UICommand{
    public String perform(List<String> params){

        String clientId = params.get(0);

        new LogOut(clientId).executeCommand();

        return String.format("%s,logout,success",clientId);
    }
}
