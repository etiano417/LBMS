package Request;

/**
 * Runs tests on requests
 */
public class TestRequests {
    public static void main(String[] args){
        ClientConnect cc0 = new ClientConnect();
        assert cc0.executeCommand().get(0) instanceof String;
    }
}
