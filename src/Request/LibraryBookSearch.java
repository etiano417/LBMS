package Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import BookRegistry.Book;
import LBMS.LBMS;

/**
 * Searches for books owned by the library and available for borrowing by visitors.
 */
public class LibraryBookSearch extends Search {
    public LibraryBookSearch(String _title, List<Object> params){
        super(_title,params);
    }
    public List<Object> executeCommand(){
        /**
        if(!LBMS.ur.isEmployee(super.clientId)){
            List<Object> output = new ArrayList<>();
            output.add(new Problem("not-authorized",""));
            return output;
        }
        */
        return super.executeCommand();
    }

    public List<Book> bookList(List<Object> params){
        return LBMS.br.searchBooks(title,params);
    }
    public void setUserSelection(List<Long> books){
        LBMS.ur.setLibrarySelection(super.clientId,books);
    }
}
