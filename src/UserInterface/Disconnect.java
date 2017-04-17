package UserInterface;

import java.util.List;

/**
 * Exits from the UI
 */
public class Disconnect implements UICommand {
    public String perform(List<String> params){
        String clientId = params.get(0);
        System.out.println(String.format("%s,disconnect;",clientId));
        System.exit(0);
        return null;
    }
}
