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
     */
    public void beginVisit(long id, LocalDateTime beginTime){
        LocalDate visitDate = beginTime.toLocalDate();
        LocalTime visitTime = beginTime.toLocalTime();
        Visit newVisit = new Visit(id, visitDate, visitTime);
        visits.add(newVisit);
    }

    /**
     * The visitor's visit ends.  The visitor's ongoing visit is given a
     * departure time.
     * @param id - The ID of the visitor in the collection
     */
    public void endVisit(long id, LocalTime time){
        for (Visit v : visits) {
            if (v.getVisitorID() == id && v.isOngoing()) {
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
    public void borrowBook(long id, Borrow borrow){
        for (Visitor v : visitors) {
            if (v.getVisitorID() == id && v.getBorrowing().size() < 5) {
                borrow.setState(borrow.getOngoing());  //Placeholder for borrowState setter
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
    public void returnBook(long id, Borrow borrow){
        for (Visitor v : visitors) {
            if (v.getVisitorID() == id) {
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
    public void payFine(long id, Fine fine, int amount) {
        for (Visitor v : visitors) {
            if (v.getVisitorID() == id) {
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
    public ArrayList<Borrow> findBorrowedBooks(long id){
        for (Visitor v : visitors) {
            if (v.getVisitorID() == id) {
                return v.getBorrowing();
            }
        }
        return null;
    }

    /**
     * Create a new visitor and add it to the collection
     */
    public void RegisterVisitor(String firstName, String lastName, String address, String phoneNumber) {

        visitors.add(new Visitor(firstName, lastName, address, phoneNumber));
    }
}
