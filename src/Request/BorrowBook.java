package Request;

import java.util.List;

import BookRegistry.Book;
import LBMS.LBMS;

public class BorrowBook implements Request {

    private int visitorID;
    private int searchID;

    public BorrowBook(int _visitor, int _search){
        visitorID = _visitor;
        searchID = _search;
    }

    public List<Object> executeCommand(){
        Book b = LBMS.br.borrowFromSearch(searchID);
        LBMS.vr.borrowBook(visitorID,b);
        //need access to due date. either borrowBook needs to either take a transaction or return one
        return null;
    }
}
