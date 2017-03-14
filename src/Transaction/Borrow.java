package Transaction;

import BookRegistry.Book;
import VisitorRegistry.Visitor;

import java.time.LocalDateTime;

/**
 * Stores information on a single borrow transaction.
 */
public class Borrow {
    public Book book;
    public Visitor visitor;
    public LocalDateTime checkedOut;
    public LocalDateTime dueDate;
    public LocalDateTime returned;

    public String toString(){
        return super.toString();
    }
}
