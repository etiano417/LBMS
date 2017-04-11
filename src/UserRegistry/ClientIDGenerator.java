package UserRegistry;

/**
 * Creates new unique client IDs
 */
public class ClientIDGenerator {
    private long number = 0;

    /**
     * Returns a unique ID number the system can use to identify a client
     * @return a unique client ID
     */
    public String newId(){
        number++;
        return String.format("%04d",number);
    }
}
