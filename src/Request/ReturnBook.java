package Request;

import BookRegistry.Book;
import Transaction.Borrow;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;

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
        List<Integer> locations = new ArrayList<>();

        for(int i = 0; i < Borrows.size() ; i++ ){
            if(Borrows.get(i) instanceof Borrow){
                Borrow b = (Borrow) Borrows.get(i);

                if(books.contains(b.getBook())){
                    locations.add(i);
                    books.remove(b.getBook());
                }
            }
        }

        List<Object> output = new ArrayList<>();

        for(Integer i : locations){
            output.add(Borrows.get(i));
            LBMS.vr.borrowBook(visitorID,(Borrow) Borrows.get(i));
            LBMS.br.returnBook(((Borrow) Borrows.get(i)).getBook().getIsbn());
        }

        return(output);
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        return output;
    }

}
