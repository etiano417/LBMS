package Request;

import java.util.List;

/**
 * A request to be invoked by the UI on the Model of the system
 */
public interface Request {
    List<Object> executeCommand();
    List<Object> undoCommand();
}
