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
    private String visitorID;
    private ArrayList<Borrow> borrowing = new ArrayList<>();
    private int amountOwed;

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
        borrowing = new ArrayList<Borrow>();
        visitorID = generateID(idTracker++);
    }

    public void addBorrow(Borrow borrow) {
        borrowing.add(borrow);
    }
    void removeBorrow(Borrow borrow) {
        borrowing.remove(borrow);
    }
    public String getVisitorID() {
        return visitorID;
    }
    String getFirstName() {
        return firstName;
    }
    String getLastName() {
        return lastName;
    }
    String getAddress() {
        return address;
    }
    String getPhone() {
        return phoneNumber;
    }
    ArrayList<Borrow> getBorrowing() {
        return borrowing;
    }
    void changeAmountOwed(int amount) { amountOwed += amount; }
    int getAmountOwed() { return amountOwed; }
    private String generateID(long id) {
        return String.format("%010d", id);
    }
}