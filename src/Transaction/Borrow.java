package Transaction;

import BookRegistry.Book;
import VisitorRegistry.Visitor;

import java.time.LocalDate;

/**
 * Stores information on a single borrow transaction.
 */
public class Borrow {
    private Book book;
    //public Visitor visitor;
    private LocalDate checkedOut;
    private LocalDate dueDate;
    private LocalDate returned;
    private BorrowState state;
    private Fine fine = null;

    private Ongoing ongoing = new Ongoing();
    private Overdue overdue = new Overdue();
    private Complete complete = new Complete();

    public Borrow(Book _book, LocalDate _checkedOut){
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
        overdue.increaseFine(fine);
    }

    public int getDaysPast(){
        LocalDate ticker = LocalDate.of(checkedOut.getYear(), checkedOut.getMonth(), checkedOut.getDayOfMonth());
        int count = 0;
        while(!ticker.equals(dueDate)) {
            ticker.plusDays(1);
            count++;
        }
        return count;
    }
}
