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
    private int daysPast = 0;

    private Ongoing ongoing = new Ongoing();
    private Overdue overdue = new Overdue();
    private Complete complete = new Complete();
    private BorrowState state = ongoing;

    private Fine fine = null;

    public Borrow(Book _book, LocalDate _checkedOut){
        book = _book;
        checkedOut = _checkedOut;
        dueDate = _checkedOut.plusDays(7);
        returned = null;
    }

    public String toString(){
        return super.toString();
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public LocalDate getCheckedOut(){
        return checkedOut;
    }

    public Book getBook(){
        return book;
    }

    public void advanceDay(){
        daysPast++;
        //check if its due date or not
        if(state.equals(ongoing)) {
            if(daysPast>7)
                state = overdue;
        }
        state.increaseFine(fine, (daysPast-7));
    }

    public boolean getOwesMoney() {
        return state.owesMoney();
    }

    public void returnBook(){
        if(state.returnBook())
            state=complete;
    }

}
