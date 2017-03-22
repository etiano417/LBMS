package BookRegistry;

import BookRegistry.Book;

import java.util.List;

/**
 * Represents the book store, and handles purchases from the book store
 */
public interface BookStore
{
    List<Book> search(String name);
    List<Book> search(String name, List<String> Authors);
    List<Book> search(String name, List<String> Authors, long isbn);
}
