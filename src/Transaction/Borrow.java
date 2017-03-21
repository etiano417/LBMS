package Transaction;

import BookRegistry.Book;
import VisitorRegistry.Visitor;

import java.time.LocalDateTime;

/**
 * Stores information on a single borrow transaction.
 */
public class Borrow {
    private Book book;
    //public Visitor visitor;
    private LocalDateTime checkedOut;
    private LocalDateTime dueDate;
    private LocalDateTime returned;
    private BorrowState state;

    public Borrow(Book _book, LocalDateTime _checkedOut){
        state = new Ongoing();
        book = _book;
        checkedOut = _checkedOut;
        dueDate = _checkedOut.plusDays(7);
        returned = null;
    }

    public String toString(){
        return super.toString();
    }

    public void advanceDay(){
        //check if its due date or not
    }
}
