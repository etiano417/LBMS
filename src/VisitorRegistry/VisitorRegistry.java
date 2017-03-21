package VisitorRegistry;

import Transaction.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Responsible for keeping track of the Library's visitors and visits.
 */
public class VisitorRegistry {
    private Collection<Visitor> visitors;
    private Collection<Visit> visits;


    /**
     * The visitor with the given ID begins a visit.  A visit object is created.
     * The visit is given the visitor ID, the date, and the starting time.
     * @param id - The ID of the visitor in the collection
     * @param beginTime - The time of the visitor's arrival
     */
    public String beginVisit(String id, LocalDateTime beginTime){
        //Check if the visitor already has an ongoing visit
        for (Visit v : visits) {
            if (v.getVisitorID().equals(id) && v.isOngoing()) {
                return "duplicate";
            }
        }

        //Check if the visitor is in the registry
        boolean isInRegistry = false;
        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                isInRegistry = true;
            }
        }
        if (!isInRegistry) {
            return "invalid id";
        }

        LocalDate visitDate = beginTime.toLocalDate();
        LocalTime visitTime = beginTime.toLocalTime();
        Visit newVisit = new Visit(id, visitDate, visitTime);
        visits.add(newVisit);
        return "success";
    }

    /**
     * The visitor's visit ends.  The visitor's ongoing visit is given a
     * departure time.
     * @param id - The ID of the visitor in the collection
     * @param time - The time the visit ends
     */
    public void endVisit(String id, LocalTime time){
        for (Visit v : visits) {
            if (v.getVisitorID().equals(id) && v.isOngoing()) {
                v.setDepartureTime(time);
            }
        }
    }

    /**
     * The specified visitor borrows a book from the library.
     * A new Borrow object is created with an initial state of Ongoing.
     * @param id - The ID of the visitor
     * @param borrow - The transaction to be added to the visitor's transactions
     */
    public void borrowBook(String id, Borrow borrow){
        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id) && v.getBorrowing().size() < 5) {
                v.addBorrow(borrow);
            }
        }
    }

    /**
     * The specified visitor returns a checked out book to the library.
     * The state of the Borrow transaction becomes Complete
     * @param id - Visitor id
     * @param borrow The borrow transaction
     */
    public void returnBook(String id, Borrow borrow){
        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                borrow.setState(borrow.getComplete());  //Placeholder for borrowState setter
                v.removeBorrow(borrow);
            }
        }
    }

    /**
     * The visitor pays a certain amount towards a fine
     * @param id - Visitor id
     * @param fine - The fine to be paid
     * @param amount - The amount paid towards the fine
     */
    public void payFine(String id, Fine fine, int amount) {
        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                fine.fee -= amount;
                if (fine.fee <= 0) {
                    fine.paid = true;
                }
            }
        }
    }

    /**
     * Returns an ArrayList of the visitor's ongoing Borrow transactions.
     * @param id
     */
    public ArrayList<Borrow> findBorrowedBooks(String id){
        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                return v.getBorrowing();
            }
        }
        return null;
    }

    /**
     * Create a new visitor and add it to the collection
     * @param firstName - The first name of the visitor
     * @param lastName - Last name of the visitor
     * @param address - Address of the visitor
     * @param phoneNumber - Phone number of the visitor
     */
    public String RegisterVisitor(String firstName, String lastName, String address, String phoneNumber) {
        Visitor newVisitor = new Visitor(firstName, lastName, address, phoneNumber);
        visitors.add(newVisitor);
        return newVisitor.getVisitorID();
    }
}
