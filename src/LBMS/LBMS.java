package LBMS;

import UserInterface.UserInterface;
import Clock.Clock;
import Clock.ClockObserver;
import VisitorRegistry.VisitorRegistry;
import BookRegistry.BookRegistry;
import BookRegistry.BookStoreTextDatabase;

import java.io.File;
import java.util.ArrayList;

/**
 * Main class for the LBMS system
 */
public class LBMS {

    public static Clock clock;
    public static VisitorRegistry vr;
    public static BookRegistry br;

    public static void main(String[] args){

        clock = new Clock(0,new ArrayList<ClockObserver>(),"08:00:00","19:00:00");

        vr = new VisitorRegistry();

        br = new BookRegistry(new BookStoreTextDatabase(new File("books.txt")));

        UserInterface UI = new UserInterface();

        UI.displayTextBasedUI();

    }
}
