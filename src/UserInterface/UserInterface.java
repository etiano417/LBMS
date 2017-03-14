package UserInterface;

/**
 * Responsible for providing a user interface
 */
public class UserInterface {

    /**
     * Displays a text-based commandline interface
     */
    public void displayTextBasedUI(){
        greeting();

        exit();
    }

    private void greeting(){
        System.out.println("Welcome to LBMS");
    }

    private void exit(){
        System.out.println("Shutting Down...");
        System.exit(0);
    }
}
