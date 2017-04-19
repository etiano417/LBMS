package VisitorRegistry;

import Transaction.Borrow;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tyler on 3/13/2017.
 */

public class Visitor implements Serializable
{
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String visitorID;
    private ArrayList<Borrow> borrowing = new ArrayList<>();
    private ArrayList<Borrow> removedBorrowing;
    private int amountOwed = 0;

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
        removedBorrowing = new ArrayList<Borrow>();
        visitorID = generateID(idTracker++);
    }

    public void addBorrow(Borrow borrow) {
        borrowing.add(borrow);
    }

    public void removeBorrow(Borrow borrow) {
        borrowing.remove(borrow);
        removedBorrowing.add(borrow);
    }

    public Borrow getPreviousBookReturn() {
        return removedBorrowing.get(removedBorrowing.size() - 1);
    }

    public Borrow getBorrow(Long isbn){
        for(Borrow b : borrowing){
            if(b.getBook().getISBN().equals(isbn)){
                return b;
            }
        }
        return null;
    }

    public boolean isBorrowing(Long isbn){
        for(Borrow b : borrowing){
            if(b.getBook().getISBN().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    public double overdueFee(Long isbn){
        for(Borrow b : borrowing){
            if(b.getBook().getISBN().equals(isbn)){
                return b.getFee();
            }
        }

        return 0;
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