package BookRegistry;

import BookRegistry.Book;

import java.io.File;
import java.util.List;

/**
 *  Handles interaction with text file book store.
 */
public class BookStoreTextDatabase implements BookStore {
    private File database;

    public Book search(String name){
        return null;
    }

    public Book search(String name, List<String> Authors){
        return null;
    }

    public Book search(String name, List<String> Authors, int isbn){
        return null;
    }
}
