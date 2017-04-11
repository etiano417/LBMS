package UserRegistry;

import java.util.*;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

/**
 * Responsible for dispensing User objects when offered the proper username and password. Stores all system users.
 */
public class UserRegistry {

    private static final String E1 = "duplicate-username";
    private static final String E2 = "duplicate-id";
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
     * When given a username and password, returns the cooresponding user.
     *
     * @param username the user's username
     * @param password the user's password
     * @return the user with the given username and password
     */
    public User getUser(String username, String password){
        User user = users.get(username);
        if(user.isPassword(password)){
            return user;
        }
            else return null;
    }

    /**
     * adds a user with the given attributes.
     *
     *
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
     * Tells whether the given id belongs to an employee
     *
     * @param id
     * @return
     */
    public boolean isEmployee(String id){

        Iterator<User> iterate = users.values().iterator();
        for(User u : users.values()){
            if (u.getId().equals(id)){
                return u.isEmployee();
            }
        }

        return false;
    }

    public String connect(){
        return cig.newId();
    }
}
