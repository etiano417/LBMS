package LBMS;

import UserInterface.UserInterface;
import Clock.Clock;
import VisitorRegistry.VisitorRegistry;
import BookRegistry.BookRegistry;

/**
 * Main class for the LBMS system
 */
public class LBMS {

    public static Clock clock;
    public static VisitorRegistry vr;
    public static BookRegistry br;

    public static void main(String[] args){

        clock = new Clock();

        vr = new VisitorRegistry();

        br = new BookRegistry();

        UserInterface UI = new UserInterface();

        UI.displayTextBasedUI();

    }
}
