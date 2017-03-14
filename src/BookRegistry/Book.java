package BookRegistry;

import java.time.*;

/**
 * Stores data on a single ISBN number.
 */
public class Book {
    private int isbn;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private int pageCount;
    private int numCopies;
    private int numAvailable;
}
