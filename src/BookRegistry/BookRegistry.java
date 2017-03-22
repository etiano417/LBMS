package BookRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for holding and retrieving books from the library's collection and from the book store.
 */
public class BookRegistry
{
    private ArrayList<Book> books;
    private BookStoreTextDatabase bstd;

    public BookRegistry(BookStoreTextDatabase bstd_)
    {
        bstd = bstd_;
    }

    public List<Book> searchBooks(String name, List<Object> params)
    {
        List<Book> toReturn = new ArrayList<>();
        for(Book b:books)
        {
            if(b.getTitle().toLowerCase().contains(name.toLowerCase())
                    && b.getAuthors().containsAll((List<String>)params.get(0))
                    && b.getISBN() == (Long)params.get(1))
                toReturn.add(b);
        }
        return toReturn;
    }

    public List<Book> searchBookStore(String name, List<Object> params)
    {
        return bstd.search(name, (List<String>)params.get(0), (Long)params.get(1));
    }

    public Book checkoutBook(long isbn)
    {
        boolean inLibrary = false;
        for(Book b:books)
        {
            if(b.getISBN()==isbn)
            {
                if(b.checkoutBook())
                {
                    inLibrary = true;
                    System.out.println("Book checkout success");
                    return b;
                }
                else
                    System.out.println("Book checkout failure");
            }
        }
        if(!inLibrary)
            System.out.println("Book not found in library");

        return null;
    }

    public void returnBook(long isbn)
    {
        boolean inLibrary = false;
        for(Book b:books)
        {
            if(b.getISBN()==isbn)
            {
                inLibrary = true;
                if(b.returnBook())
                    System.out.println("Book return success");
                else
                    System.out.println("Book return failure");
            }
        }
        if(!inLibrary)
            System.out.println("Book not found in library");
    }

    public void purchaseBook(Book book, int num)
    {
        boolean inLibrary = false;
        for(Book b:books)
        {
            if(b.equals(book))
            {
                inLibrary = true;
                for(int x=0;x<num;x++)
                {
                    b.purchaseBook();
                }
            }
        }

        if(!inLibrary)
            books.add(book);
    }

    public void purchaseBook(long isbn, int num)
    {
        boolean inLibrary = false;
        for(Book b:books)
        {
            if(b.getISBN()==isbn)
            {
                inLibrary = true;
                for(int x=0;x<num;x++)
                {
                    b.purchaseBook();
                }
            }
        }

        if(!inLibrary)
        {
            List<Book> toAdd = new ArrayList<>();

            ArrayList<String> blankAuthors = new ArrayList<>();
            toAdd = bstd.search("",blankAuthors,isbn);

            if(toAdd.size()>0)
                books.add(toAdd.get(0));
            else
                System.out.println("Book not found in library or database");
        }
    }
}
