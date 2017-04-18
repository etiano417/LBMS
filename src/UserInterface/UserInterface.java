package UserInterface;

import BookRegistry.Book;
import Request.ClientConnect;
import Request.CurrentDateAndTime;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import GraphicalUserInterface.*;

/**
 * Responsible for providing a user interface
 */
public class UserInterface {
    public static List<Book> librarySearch = new ArrayList<>();
    public static List<Book> storeSearch = new ArrayList<>();
    HashMap<String, UICommand> requests = new HashMap<>();
    String buffer = "";
    Scanner input = new Scanner(System.in);
    String cid;

    /**
     * Displays a text-based commandline interface
     */
    public void displayTextBasedUI(){
        setup();
        greeting();
        timestamp();
        ClientConnect cc = new ClientConnect();
        cid = (String)cc.executeCommand().get(0);
        System.out.println(String.format("Client ID: %s",cid));
        //System.out.print(String.format("Client #%s: ",cid));
        while(input.hasNext()){
            //System.out.print(String.format("Client #%s: ",cid));
            submit(input.nextLine());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch(Exception e){

            }
        }
        exit();
    }

    /**
     * attaches all the UI commands to the strings that invoke them in the requests HashMap.
     */
    private void setup(){

        requests.put("register", new Register());
        requests.put("create", new Create());
        requests.put("login", new Login());
        requests.put("datetime", new Datetime());
        requests.put("search", new Search());
        requests.put("disconnect", new Disconnect());
        requests.put("buy", new Buy());
        requests.put("info", new Info());
        requests.put("arrive", new Arrive());
        requests.put("borrow", new Borrow());
        requests.put("advance", new Advance());
        requests.put("borrowed", new Borrowed());
        requests.put("logout", new Logout());
        requests.put("pay", new Pay());
        requests.put("depart", new Depart());

        //Note: Depart does not work because the stored visit returns ongoing = false
        //requests.put("depart", new Depart());

        //

    }

    public void setupMultiConnect(){

        setup();
        requests.remove("disconnect");
    }

    /**
     * The system's startup greeting
     */
    private void greeting(){
        System.out.println("Welcome to LBMS");
    }

    /**
     * The current system time.
     */
    private void timestamp() {
        LocalDateTime dt = (LocalDateTime) new CurrentDateAndTime().executeCommand().get(0);
        System.out.printf("[%s]\n",dt.toString());
    }

    /**
     * The sequence of shutdown operations for the system.
     */
    private void exit(){
        System.out.println("Shutting Down...");
        System.exit(0);
    }

    /**
     * submits a single line to be analysed for commands. If the command is incomplete, it will be added to the buffer,
     * which will then be output for the user. If it is complete, it will be parsed.
     *
     * @param input the user input
     */
    public void submit(String input){

        //This is repeated as long as there are any ';' symbols remaining in the input string
        while(input.indexOf(';') > -1) {

            //saves the location of the first ';' in the input string
            int end = input.indexOf(';');
            //submits a full command (delimited with ';') for parsing
            parse(buffer + input.substring(0,end));
            //removes the parsed String from the input String
            input = input.substring(end + 1);
        }

        //adds un-parsed input to the buffer
        buffer = buffer + input;
        //shows the user the unparsed input remaining
        System.out.print(buffer);
        //System.out.println("partial-request;");
    }

    public void submitGUICommand(UserTab userTab, String commandText){
        //converts the string to a list of strings (delimited with ',' characters)
        List<String> command = Arrays.asList(commandText.split(","));
        //retrieves the UICommand that cooresponds the input command (from the requests HashMap)
        UICommand uic = requests.get(command.get(0));
        //Check to see if there was a cooresponding UICommand. If there was not, the user is informed that the command
        //is not recognized. If there was a cooresponding UICommand, the input parameters are passed as a list to the
        //UICommand
        if(uic == null){
            userTab.pushToTerminal(command.get(0) + " is not a recognized command");
        } else {
            List<String> performParams = new ArrayList<>();
            for(String s : command.subList(1, command.size())){
                performParams.add(s);
            }
            performParams.add(0,cid);
            userTab.pushToTerminal(uic.perform(performParams));
        }
        //userTab.pushToTerminal();
    }

    /**
     * Matches the input command to a UICommand if possible, and informs the user if the input command is not recognized
     * by the system.
     *
     * @param input a user input command
     */
    private void parse(String input){
        //converts the string to a list of strings (delimited with ',' characters)
        List<String> command = Arrays.asList( input.split(","));
        //retrieves the UICommand that cooresponds the input command (from the requests HashMap)
        UICommand uic = requests.get(command.get(0));
        //Check to see if there was a cooresponding UICommand. If there was not, the user is informed that the command
        //is not recognized. If there was a cooresponding UICommand, the input parameters are passed as a list to the
        //UICommand
        if(uic == null){
            System.out.println(command.get(0) + " is not a recognized command");
        } else {
            List<String> performParams = new ArrayList<>();
            for(String s : command.subList(1, command.size())){
                performParams.add(s);
            }
            performParams.add(0,cid);
            System.out.println(uic.perform(performParams));
        }

        //empties the buffer
        buffer = "";
    }

    public String getClientId(){
        return cid;
    }

    public void connect(){
        cid = (String) new ClientConnect().executeCommand().get(0);
    }
}
