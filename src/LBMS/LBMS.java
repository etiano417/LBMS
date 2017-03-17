package LBMS;

import UserInterface.UserInterface;
import Clock.Clock;

/**
 * Main class for the LBMS system
 */
public class LBMS {

    public static Clock clock;

    public static void main(String[] args){

        clock = new Clock();

        UserInterface UI = new UserInterface();

        UI.displayTextBasedUI();

    }
}
