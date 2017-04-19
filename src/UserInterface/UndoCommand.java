package UserInterface;

import Request.Undo;

import java.util.List;

/**
 * Created by Eric Tiano on 4/19/2017.
 */
public class UndoCommand implements UICommand{

    public String perform(List<String> params){
        String clientId = params.get(0);

        List<Object> result = new Undo(clientId).executeCommand();

        if((boolean)result.get(0)){
            return String.format("%s,undo,success;",clientId);
        }

        return String.format("%s,undo,cannot-undo;",clientId);
    }

}
