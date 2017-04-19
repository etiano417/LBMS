package UserRegistry;

import java.util.*;

/**
 * Responsible for dispensing User objects when offered the proper username and password. Stores all system users.
 */
public class UserRegistry {

    public static final String E1 = "duplicate-username";
    public static final String E2 = "duplicate-id";
    //private static final String E3 = "invalid-id"; check this at request level

    //maps usernames to users
    private Map<String,User> users = new HashMap<>();
    //maps client ids to users
    private Map<String,User> clients = new HashMap<>();
    //generates client ids
    private ClientIDGenerator cig = new ClientIDGenerator();
    //notes clients without logged in users (may not be used if we can auto-fill client id)
    private List<String> userlessClients = new ArrayList<>();

    /**
     * When given a username and password, returns the corresponding user.
     *
     * @param username the user's username
     * @param password the user's password
     * @return the user with the given username and password
     */
    public User getUser(String username, String password){
        User user = users.get(username);
        if(user != null && user.isPassword(password)){
            return user;
        }
            else return null;
    }

    /**
     * Get user from specified user id
     * @param id The id of the user
     * @return The user object associated with the id
     */
    public User getUser(String id) {
        return users.get(id);
    }

    /**
     * adds a user with the given attributes.
     *
     * @param   username the username of the user
     * @param   password the password of the user
     * @param   id the id of the user's visitor
     * @param   employee whether the user is an employee or not
     * @return  A string noting what error, if any, occurred
     *
     *          Returns "duplicate-username" if the username is taken
     *          Returns "duplicate-id" if the visitor id is taken
     *          Returns "success" otherwise
     */
    public String addUser(String username, String password, String id, boolean employee){

        //check for duplicate username
        if(users.containsKey(username)){
            return E1;
        }

        //check for duplicate id
        for(User u : users.values()){
            if (u.getId().equals(id)){
                return E2;
            }
        }

        User user = new User(password,id,employee);
        users.put(username,user);
        return "success";
    }

    /**
     * Tells whether the given client id is logged in
     *
     * @param id A client's id
     * @return true if the given client is logged in, false if not
     */
    public boolean isUser(String id){

        Iterator<User> iterate = users.values().iterator();
        for(User u : users.values()){
            if (u.getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    /**
     * Tells whether the given client id belongs to an employee
     *
     * @param id A client's id
     * @return true if the given client is logged in as an employee, false if not
     */
    public boolean isEmployee(String id){

        return clients.get(id).isEmployee();
    }

    public String connect(){
        String id = cig.newId();
        clients.put(id,new User());
        return id;
    }

    public boolean isConnected(String clientId){
        return clients.keySet().contains(clientId);
    }

    public Long selectStoreBook(String clientId,int selection){
        return clients.get(clientId).selectStoreBook(selection);
    }

    public Long selectLibraryBook(String clientId,int selection){
        return clients.get(clientId).selectLibraryBook(selection);
    }

    public Long selectBorrowedBook(String clientId,int selection){
        return clients.get(clientId).selectBorrowedBook(selection);
    }

    public void setStoreSelection(String clientId, List<Long> books){
        clients.get(clientId).setStoreSelection(books);
    }

    public void setLibrarySelection(String clientId, List<Long> books){
        clients.get(clientId).setLibrarySelection(books);
    }

    public void setBorrowedSelection(String clientId, List<Long> books){
        clients.get(clientId).setBorrowedSelection(books);
    }

    public boolean logIn(String userId, String username, String password){
        if(users.containsKey(username) && users.get(username).isPassword(password)){
            clients.put(userId,users.get(username));
            return true;
        }
        return false;
    }

    public void logOut(String userId){
        clients.remove(userId);
    }

    public String getVisitor(String clientId){
        return clients.get(clientId).getId();
    }

    public Map<String,User> getUserList()
    {
        return users;
    }
}

