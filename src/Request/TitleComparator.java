package Request;

import BookRegistry.Book;

import java.util.Comparator;

/**
 * Created by Eric Tiano on 3/21/2017.
 */
public class TitleComparator implements Comparator<Book> {
    public int compare(Book a, Book b){
        if (a.getTitle().compareTo(b.getTitle()) == 0){
            return new Long(a.getIsbn()).compareTo(new Long(b.getIsbn()));
        }

        return a.getTitle().compareTo(b.getTitle());
    }
}
