package UserInterface;

import java.util.List;

/**
 * Checks for Missing parameters
 */
class MissingParameters {

    public static String missingParameters(List<String> requiredParams, int numParams){
        for(int i = 0; i < numParams; i++){
            requiredParams.remove(0);
        }
        String output = "missing-parameters";
        for(String p : requiredParams){
            output = output + String.format(",%s",p);
        }
        return output;
    }
}
