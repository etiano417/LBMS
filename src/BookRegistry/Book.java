package BookRegistry;

import java.time.LocalDate;
import java.util.List;

/**
 * Stores data on a single ISBN number.
 */
public class Book
{
    private long isbn;
    private String title;
    private List<String> authors;
    private String publisher;
    private LocalDate publishDate;
    private int pageCount;
    private int numCopies;
    private int numAvailable;

    public Book(long isbn_, String title_, List<String> authors_, String publisher_, LocalDate publishDate_,
                int pageCount_)
    {
        isbn = isbn_;
        title = title_;
        authors = authors_;
        publisher = publisher_;
        publishDate = publishDate_;
        pageCount = pageCount_;
        numCopies = 1;
        numAvailable = 1;
    }

    public boolean checkoutBook()
    {
        if(numAvailable > 0)
        {
            numAvailable--;
            return true;
        }
        else
            return false;
    }

    public boolean returnBook()
    {
        if(numAvailable<numCopies)
        {
            numAvailable++;
            return true;
        }
        else
            return false;
    }

    public void purchaseBook()
    {
        numCopies++;
        numAvailable++;
    }

    public String getTitle()
    {
        return title;
    }

    public long getIsbn(){
        return isbn;
    }

    public List<String> getAuthors()
    {
        return authors;
    }

    public Long getISBN()
    {
        return isbn;
    }

    public LocalDate getPublishDate(){
        return publishDate;
    }

    public int getNumAvailable(){
        return numAvailable;
    }

    @Override
    public String toString()
    {
        String toReturn = "" + isbn + ", " + title + ", ";
        for(int x=0;x<authors.size();x++)
        {
            toReturn += "{" + authors.get(x) + "}";
        }
        toReturn += ", " + publisher + ", " + publishDate + ", " + pageCount
                + " (" + numAvailable + "/" + numCopies + ")";

        return toReturn;
    }
}
