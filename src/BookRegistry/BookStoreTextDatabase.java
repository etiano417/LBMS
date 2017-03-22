package BookRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

/**
 *  Handles interaction with text file book store.
 */
public class BookStoreTextDatabase implements BookStore
{
    private ArrayList<Book> database = new ArrayList<>();

    public BookStoreTextDatabase(File file)
    {
        Scanner scan;
        try{
            scan = new Scanner(file);
        }catch(FileNotFoundException fnfe){
            throw new Error(fnfe);
        }

        String line, isbn, title, author, publisher, publishedDate, pageCount;
        ArrayList<String> authors;
        LocalDate publishDate;
        while(scan.hasNext())
        {
            line = scan.nextLine();
            StringTokenizer st = new StringTokenizer(line);

            isbn = st.nextToken(",");
            st.nextToken("\"");
            title = st.nextToken();
            st.nextToken("{");
            author = st.nextToken("}");
            st.nextToken("\"");
            publisher = st.nextToken();
            st.nextToken(",");
            publishedDate = st.nextToken();
            pageCount = st.nextToken();

            author = author.substring(1);
            authors = new ArrayList<>(Arrays.asList(author.split("(, )")));

            if(publishedDate.length()==4)
                publishedDate += "-01-01";
            else if(publishedDate.length()==7)
                publishedDate += "-01";

            publishDate = LocalDate.parse(publishedDate);

            database.add(new Book(Long.parseLong(isbn), title, authors, publisher,
                    publishDate, Integer.parseInt(pageCount)));
        }

        scan.close();
    }

    public List<Book> search(String name)
    {
        ArrayList<Book> toReturn = new ArrayList<>();
        for(Book b:database)
        {
            if(b.getTitle().toLowerCase().contains(name.toLowerCase()))
                toReturn.add(b);
        }
        return toReturn;
    }

    public List<Book> search(String name, List<String> authors)
    {
        ArrayList<Book> toReturn = new ArrayList<>();
        for(Book b:database)
        {
            if(b.getTitle().toLowerCase().contains(name.toLowerCase())
                    && b.getAuthors().containsAll(authors))
                toReturn.add(b);
        }
        return toReturn;
    }

    public List<Book> search(String name, List<String> authors, long isbn)
    {
        ArrayList<Book> toReturn = new ArrayList<>();
        for(Book b:database)
        {
            if(b.getTitle().toLowerCase().contains(name.toLowerCase())
                    && b.getAuthors().containsAll(authors)
                    && b.getISBN() == isbn)
                toReturn.add(b);
        }
        return toReturn;
    }
}
