package Request;

/**
 * Is passed as the first element of a Request's output whenever a request can not be performed
 */
public class Problem {
    String type;
    String message;

    public Problem(String _type, String _message){
        type = _type;
        message = _message;
    }

    public String getType(){
        return type;
    }

    public String getMessage(){
        return message;
    }
}
