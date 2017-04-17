package UserInterface;

import java.util.List;

/**
 * Responsible for checking whether a user is an employee.
 */
class EmployeeChecking {

    /**
     * Checks to see if the output is empty, and if it is, returns a properly formatted error message.
     * @param   output a request's output
     * @param   _clientId the user's client ID
     * @param   request_name the name of the request called
     * @return  if the user is unauthorized: client ID,<request name>,not-authorized;
     *          otherwise, returns an empty String
     */
    public static String isEmployee(List<Object> output, String _clientId, String request_name){
        if(output.isEmpty()) {
            return String.format("%s,%s,not-authorized;",_clientId,request_name);
        }

        else return "";
    }

    public static String message(String _clientId, String request_name){
        return String.format("%s,%s,not-authorized;",_clientId,request_name);
    }
}
