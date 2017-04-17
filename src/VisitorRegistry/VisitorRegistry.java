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
    private Collection<Visitor> visitors = new ArrayList<Visitor>();
    private Collection<Visit> visits = new ArrayList<Visit>();


    /**
     * Gives access to the collection of visitors
     * @return visitor collection
     */
    public Collection<Visitor> getVisitors(){
        return visitors;
    }

    /**
     * Checks to see if the visitor is in the registry
     * @param id The ID of the visitor
     * @return True if the visitor is in the registry
     */
    public boolean isInRegistry(String id) {
        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                return true;
            }
        }
        return false;
    }


    /**
     * The visitor with the given ID begins a visit.  A visit object is created.
     * The visit is given the visitor ID, the date, and the starting time.
     * @param id - The ID of the visitor in the collection
     * @param beginTime - The time of the visitor's arrival
     * @return return a String which indicates if the operation was successful
     */
    public String beginVisit(String id, LocalDateTime beginTime){
        //Check if the visitor is in the registry
        if (!this.isInRegistry(id)) {
            return "invalid id";
        }

        //Make sure visitor isn't already performing a visit
        for (Visit v : visits) {
            if (v.getVisitorID().equals(id) && v.isOngoing()) {
                return "duplicate";
            }
        }

        LocalDate visitDate = beginTime.toLocalDate();
        LocalTime visitTime = beginTime.toLocalTime();
        Visit newVisit = new Visit(id, visitDate, visitTime);
        visits.add(newVisit);
        return "success";
    }

    /**
     * Checks if the visitor is in the library
     * @param id - ID of the visitor
     * @return True if the visitor has any going visits
     */
    public boolean getVisiting(String id) {
        for (Visit v : visits) {
            if (v.getVisitorID().equals(id) && v.isOngoing()) {
                return true;
            }
        }
        return false;
    }

    /**
     * The visitor's visit ends.  The visitor's ongoing visit is given a
     * departure time.
     * @param id - The ID of the visitor in the collection
     * @param time - The time the visit ends
     */
    public String endVisit(String id, LocalTime time){
        //Check if the visitor is already visiting
        boolean isVisiting = false;
        for (Visit v : visits) {
            if (v.getVisitorID().equals(id) && v.isOngoing()) {
                isVisiting = true;
            }
        }
        if (!isVisiting) {
            return "invalid id";
        }

        for (Visit v : visits) {
            if (v.getVisitorID().equals(id) && v.isOngoing()) {
                v.setDepartureTime(time);
            }
        }

        return "success";
    }

    /**
     * The specified visitor borrows a book from the library.
     * A new Borrow object is created with an initial state of Ongoing.
     * @param id - The ID of the visitor
     * @param borrow - The transaction to be added to the visitor's transactions
     */
    public String borrowBook(String id, Borrow borrow){
        //Check if the visitor is in the registry, if the book checkout limit has not been exceeded, and if the visitor has no outstanding fines
        boolean bookLimitExceeded = false;
        boolean hasOutstandingFine = false;

        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                if (v.getBorrowing().size() >= 5) {
                    bookLimitExceeded = true;
                }
                for (Borrow b : v.getBorrowing()) {
                    if (b.getOwesMoney()) {
                        hasOutstandingFine = true;
                    }
                }
                if (!bookLimitExceeded && !hasOutstandingFine) {
                    v.removeBorrow(borrow);
                }
            }
        }

        if (!isInRegistry(id)) { return "invalid id"; }
        if (bookLimitExceeded) { return "book limit exceeded"; }
        if (hasOutstandingFine) { return "has outstanding fine"; }

        return "success";
    }

    /**
     * The specified visitor returns a checked out book to the library.
     * The state of the Borrow transaction becomes Complete
     * @param id - Visitor id
     * @param borrow The borrow transaction
     */
    public String returnBook(String id, Borrow borrow){
        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                borrow.returnBook();
                v.removeBorrow(borrow);
                v.changeAmountOwed(borrow.getFee());
            }
        }

        if(!isInRegistry(id)) {
            return "invalid id";
        }

        return "success";
    }

    /**
     * @param id - Visitor id
     * @param amount - The amount paid towards the fine
     */
    public String payFine(String id, int amount) {
        //Check if the visitor is in the registry
        if (!isInRegistry(id)) {
            return "invalid id";
        }

        //Check if amount paid is negative or if amount exceeds the fine
        if (amount < 0)
            return "invalid amount";

        for (Visitor v : visitors) {
            if (v.getVisitorID().equals(id)) {
                //Check if amount paid exceeds amount owed
                if (amount > v.getAmountOwed())
                    return "invalid amount";
                v.changeAmountOwed(amount * -1);
            }
        }

        return "success";
    }

    /**
     * Returns an ArrayList of the visitor's ongoing Borrow transactions.
     * @param id Visitor ID
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
        //Check if visitor is already in registry
        boolean isInRegistry = false;
        for (Visitor v : visitors) {
            if (v.getFirstName().equals(firstName) &&
                    v.getLastName().equals(lastName) &&
                    v.getAddress().equals(address) &&
                    v.getPhone().equals(phoneNumber)) {
                isInRegistry = true;
            }
        }
        if (isInRegistry) {
            return "duplicate";
        }

        //Create new visitor and add it to the registry
        Visitor newVisitor = new Visitor(firstName, lastName, address, phoneNumber);
        visitors.add(newVisitor);
        return newVisitor.getVisitorID();
    }

    public boolean isInRegistry(String id){
        boolean inRegistry = false;
        for(Visitor v : visitors){
            if(v.getVisitorID().equals(id)){
                inRegistry = true;
            }
        }

        return inRegistry;
    }
}
