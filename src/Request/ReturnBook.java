package Request;

import BookRegistry.Book;
import Transaction.Borrow;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all the processes involved in returning a book.
 */
public class ReturnBook implements Request {
    String visitorID;
    List<Book> books;

    public ReturnBook(String _visitorID, List<Book> _books){
        visitorID = _visitorID;
        books = _books;
    }

    public List<Object> executeCommand(){
        FindBorrowedBooks fbb = new FindBorrowedBooks(visitorID);

        List<Book> candidates = new ArrayList<>();

        List<Object> Borrows = fbb.executeCommand();
        int location;

        for(int i = 0; i < Borrows.size() ; i++ ){
            if(Borrows.get(i) instanceof Borrow){
                Borrow b = (Borrow) Borrows.get(i);

                if(b.getBook().equals ()){

                }
            }
        }



        return();
    }

}
