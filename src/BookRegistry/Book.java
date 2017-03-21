package BookRegistry;

import java.time.*;
import java.util.List;

/**
 * Stores data on a single ISBN number.
 */
public class Book {
    private int isbn;
    private String title;
    private List<String> authors;
    private String publisher;
    private LocalDate publishDate;
    private int pageCount;
    private int numCopies;
    private int numAvailable;

    public String getTitle(){
        return title;
    }

    public int getIsbn(){
        return isbn;
    }

    public LocalDate getPublishDate(){
        return publishDate;
    }

    public int getNumCopies(){
        return numCopies;
    }
}
