package VisitorRegistry;

import Transaction.Borrow;

import java.util.ArrayList;

/**
 * Created by Tyler on 3/13/2017.
 */
 
public class Visitor {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private long visitorID;
    private ArrayList<Borrow> borrows = new ArrayList<>();

    private static long idTracker;


    /**
     * Visitor constructor
     * @param first - First name
     * @param last - Last name
     * @param add - Address
     * @param phone - Phone number
     */
    public Visitor(String first, String last, String add, String phone) {
        firstName = first;
        lastName = last;
        address = add;
        phoneNumber = phone;
        borrows = new ArrayList<Borrow>();
        visitorID = idTracker++;
    }

    public void addBorrow(Borrow borrow) {
        borrows.add(borrow);
    }

    public void removeBorrow() {

    }

    public long getVisitorID() {
        return visitorID;
    }

    public ArrayList<Borrow> getBorrowing() {
        ArrayList<Borrow> borrowing = new ArrayList<>(5);
        for (Borrow b : borrows) {
            if () {              //TODO: If the borrow's state is not 'Complete'
                borrowing.add(b);
            }
        }
        return borrowing;
    }
}