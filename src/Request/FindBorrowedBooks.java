package Request;

import java.util.ArrayList;
import java.util.List;

import BookRegistry.Book;
import LBMS.LBMS;
import Transaction.Borrow;

/**
 * returns a list of books borrowed by a given visitor
 */
public class FindBorrowedBooks implements Request{
    private String clientID;
    private String visitorID;

    public FindBorrowedBooks(String _clientID, String _visitorID){
        clientID = _clientID;
        visitorID = _visitorID;
    }

    public List<Object> executeCommand(){

        List<Object> output = new ArrayList<>();

        if(!LBMS.vr.isInRegistry(visitorID)){
            output.add(new Problem("invalid-visitor-id",""));
            return output;
        }

        List<Borrow> borrows = LBMS.vr.findBorrowedBooks(visitorID);

        for(Borrow b : borrows) {
            output.add(b);
        }

        List<Long> books = new ArrayList<>();
        for(Object o : output){
            books.add(((Borrow)o).getBook().getISBN());
        }

        LBMS.ur.setBorrowedSelection(clientID, books);
        return output;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }
}
