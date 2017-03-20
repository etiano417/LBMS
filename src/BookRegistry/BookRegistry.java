package BookRegistry;

import java.util.Collection;
import java.util.List;

/**
 * Responsible for holding and retrieving books from the library's collection and from the book store.
 */
public class BookRegistry {
    private Collection<Book> books;

    public List<Book> searchBooks(String name, List<Object> params){
        return null;
    }

    //public List<Book> searchBooks(String name, List<String> authors){return null;}

    //public List<Book> searchBooks(String name, List<String> authors, int isbn){return null;}

    public List<Book> searchBookStore(String name, List<Object> params){
        return null;
    }

    //public List<Book> searchBookStore(String name, List<String> authors){return null}

    //public List<Book> searchBookStore(String name, List<String> authors, int isbn){return null;}

    public Book lendBook(int isbn){
        return null;
    }

    public void returnBook(int isbn){

    }

    public void purchaseBook(Book book, int num){

    }

    public void purchaseBook(int book, int num){

    }
}
