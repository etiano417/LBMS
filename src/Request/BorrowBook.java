package Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import BookRegistry.Book;
import LBMS.LBMS;
import Transaction.Borrow;

public class BorrowBook implements Request {

    private String clientId;
    private List<Integer> bookIds;
    private String visitorId;

    public BorrowBook(String _clientId, List<Integer> _bookIds){
        clientId = _clientId;
        bookIds = _bookIds;
        visitorId = LBMS.ur.getVisitor(clientId);
    }

    public BorrowBook(String _clientId, List<Integer> _bookIds, String _visitorId){
        clientId = _clientId;
        bookIds = _bookIds;
        visitorId = _visitorId;
    }

    public List<Object> executeCommand(){

        List<Object> output = new ArrayList<>();

        if (!LBMS.library.isOpen()){
            output.add(new Problem("library-closed", ""));
            return output;
        }

        List<Long> isbnList = new ArrayList<>();
        for(int bookId : bookIds) {
            Long isbn = LBMS.ur.selectStoreBook(clientId, bookId);
            if (isbn == -1) {
                output.add(new Problem("invalid-book-id", ""));
                return output;
            }
            isbnList.add(isbn);
        }

        LocalDate dueDate;
        for(Long isbn: isbnList) {
            Book b = LBMS.br.checkoutBook(isbn);
            //temporary
            LocalDateTime d = LBMS.clock.getDateTime();
            Borrow t = new Borrow(b, d.toLocalDate());

            String borrowResult = LBMS.vr.borrowBook(visitorId, t);


            if (borrowResult.equals("invalid id")) {
                output.add(new Problem("invalid-visitor-id", ""));
                return output;
            } else if (borrowResult.equals("book limit exceeded")) {
                output.add(new Problem("book-limit-exceeded", ""));
                return output;
            } else if (borrowResult.equals("has outstanding fine")) {
                output.add(new Problem("outstanding-fine", ""));
                return output;
            }
            output.add(t.getDueDate());
        }
        return output;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        return output;
    }
}
