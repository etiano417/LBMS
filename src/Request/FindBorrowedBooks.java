package Request;

import java.util.ArrayList;
import java.util.List;
import LBMS.LBMS;
import Transaction.Borrow;

/**
 * returns a list of books borrowed by a given visitor
 */
public class FindBorrowedBooks implements Request{
    private String visitorID;

    public FindBorrowedBooks(String _visitorID){
        visitorID = _visitorID;
    }

    public List<Object> executeCommand(){

        List<Object> output = new ArrayList<>();

        List<Borrow> borrows = LBMS.vr.findBorrowedBooks(visitorID);

        for(Borrow b : borrows){
            output.add(b);
        }

        return output;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }
}
