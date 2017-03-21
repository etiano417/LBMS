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
    private ArrayList<Borrow> borrowing = new ArrayList<>();

    private static long idTracker = 0;


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
        borrowing = new ArrayList<Borrow>(5);
        visitorID = idTracker++;
    }

    public void addBorrow(Borrow borrow) {
        borrowing.add(borrow);
    }

    public void removeBorrow(Borrow borrow) {
        borrowing.remove(borrow);
    }

    public long getVisitorID() {
        return visitorID;
    }

    public ArrayList<Borrow> getBorrowing() {
        return borrowing;
    }
}