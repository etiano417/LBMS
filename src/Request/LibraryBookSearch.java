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
public class LibraryBookSearch implements Request {
    String title;
    List<String> authors;
    Optional<Integer> isbn;
    Optional<String> publisher;
    Optional<String> sortOrder;

    public LibraryBookSearch(String _title,List<Object> params){
        title = _title;

        authors = new ArrayList<String>();

        while(!params.isEmpty() && params.get(0) instanceof String){
            if(params.get(0) == "*") {
                params.remove(0);
                break;
            }
            params.remove(0);
            authors.add((String)params.get(0));
        }
        if(!params.isEmpty() && params.get(0) instanceof Integer){
            isbn = Optional.of((Integer) params.get(0));
        } else if(!params.isEmpty()) {
            params.remove(0);
        }

        if(!params.isEmpty() && params.get(0) instanceof String) {
            publisher = Optional.of((String) params.get(0));
        }

        if(!params.isEmpty() && params.get(0) instanceof String) {
            sortOrder = Optional.of((String) params.get(0));
        }
    }

    public List<Object> executeCommand(){

        List<Object> params = new ArrayList<Object>();

        params.add(authors);
        params.add(isbn);
        params.add(publisher);
        params.add(sortOrder);

        List<Book> books = LBMS.br.searchBooks(title,params);

        //converts the book list to an object list for output
        List<Object> output = new ArrayList<Object>();
        for(Book b : books){
            output.add(b);
        }
        return output;
    }

}
