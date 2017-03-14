package BookRegistry;

import BookRegistry.Book;

import java.util.List;

/**
 * Represents the book store, and handles purchases from the book store
 */
public interface BookStore {
    Book search(String name);
    Book search(String name, List<String> Authors);
    Book search(String name, List<String> Authors, int isbn);
}
