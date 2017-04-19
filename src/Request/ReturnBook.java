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
    String clientId;
    String visitorId;
    List<Integer> bookIds;

    public ReturnBook(String _clientId, String _visitorId, List<Integer> _bookIds){
        clientId = _clientId;
        visitorId = _visitorId;
        bookIds = _bookIds;
    }

    public ReturnBook(String _clientId, List<Integer> _bookIds){
        clientId = _clientId;
        visitorId = LBMS.ur.getVisitor(_clientId);
        bookIds = _bookIds;
    }

    public List<Object> executeCommand(){

        List<Object> output = new ArrayList<>();

        List<Long> isbns = new ArrayList<>();
        List<Integer> badIds = new ArrayList<>();
        for(Integer id : bookIds){
            Long isbn = LBMS.ur.selectBorrowedBook(clientId,id);
            if(isbn == -1){
                badIds.add(id);
            }else{
                isbns.add(isbn);
            }
        }

        if(!badIds.isEmpty()){
            String invalidIds = "";
            for(int id : badIds){
                invalidIds += id + ",";
            }
            output.add(new Problem("invalid-book-id," + invalidIds,""));
            return output;
        }

        if(!LBMS.vr.isInRegistry(visitorId)){
            output.add(new Problem("invalid-visitor-id",""));
        }

        List<Long> overdueBooks = new ArrayList<>();
        double fine = 0;

        for(Long isbn : isbns){
            if(LBMS.vr.overdueFee(visitorId,isbn) > 0){
                overdueBooks.add(isbn);
                fine += LBMS.vr.overdueFee(visitorId,isbn);
            }
        }

        for(Long isbn : isbns) {
            String result = LBMS.vr.returnBook(visitorId,isbn);

            if(!result.equals("success")){
                output.add(new Problem(result,""));
            }

        }

        for(Long isbn : isbns) {
            LBMS.br.returnBook(isbn);
        }

        if(!overdueBooks.isEmpty()){
            output.add(fine);
            for(Long isbn : overdueBooks){
                output.add(isbns.indexOf(isbn));
            }

            return output;
        }
        return output;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        return output;
    }

}
