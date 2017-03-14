package Request;

/**
 * A request to be invoked by the UI on the Model of the system
 */
public interface Request {
    void executeCommand();
    void parseRequest(String request);
}
