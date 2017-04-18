package LBMS;

import UserInterface.UserInterface;
import GraphicalUserInterface.GUI;
import Clock.Clock;
import Clock.ClockObserver;
import VisitorRegistry.VisitorRegistry;
import BookRegistry.BookRegistry;
import BookRegistry.BookStoreTextDatabase;
import UserRegistry.UserRegistry;
import Library.Library;

import java.io.File;
import java.util.ArrayList;

/**
 * Main class for the LBMS system
 */
public class LBMS {

    public static Clock clock;
    public static Library library;
    public static VisitorRegistry vr;
    public static BookRegistry br;
    public static UserRegistry ur;

    public static void main(String[] args){

        clock = new Clock(0,new ArrayList<ClockObserver>(),"08:00:00","19:00:00");

        vr = new VisitorRegistry();

        library = new Library(clock,vr);

        br = new BookRegistry(new BookStoreTextDatabase(new File("books.txt")));

        ur = new UserRegistry();

        if(args.length > 0 && (args[0].equals("ptui")||args[0].equals("PTUI"))) {
            UserInterface UI = new UserInterface();
            UI.displayTextBasedUI();
        } else {
            String[] params = {};
            GUI.main(params);
        }

    }
}
