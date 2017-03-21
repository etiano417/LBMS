package Request;

import BookRegistry.Book;
import LBMS.LBMS;

import java.util.*;

/**
 * Searches for books owned by the library and available for borrowing by visitors.
 */
public class BookStoreSearch extends Search {
    public BookStoreSearch(String _title, List<Object> params){
        super(_title,params);
    }
    public List<Book> bookList(List<Object> params){
        return LBMS.br.searchBookStore(title,params);
    }
}
