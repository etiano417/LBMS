package Request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import BookRegistry.Book;
import LBMS.LBMS;
import Transaction.Borrow;

public class BorrowBook implements Request {

    private int visitorID;
    private int isbn;

    public BorrowBook(int _visitor, int _isbn){
        visitorID = _visitor;
        isbn = _isbn;
    }

    public List<Object> executeCommand(){

        Book b = LBMS.br.lendBook(isbn);
        LocalDateTime d = LBMS.clock.getDateTime();
        Borrow t = new Borrow(b,d.toLocalDate());

        List<Object> output = new ArrayList<>();

        LBMS.vr.borrowBook(Integer.toString(visitorID),t);

        output.add(t.getDueDate());

        return output;
    }
}
