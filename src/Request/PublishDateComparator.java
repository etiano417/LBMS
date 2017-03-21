package Request;

import BookRegistry.Book;

import java.util.Comparator;

/**
 * Created by Eric Tiano on 3/21/2017.
 */
public class PublishDateComparator implements Comparator<Book> {
    public int compare(Book a, Book b){
        if (a.getPublishDate().compareTo(b.getPublishDate()) == 0){
            return new Integer(a.getIsbn()).compareTo(new Integer(b.getIsbn()));
        }

        return a.getPublishDate().compareTo(b.getPublishDate());
    }
}
