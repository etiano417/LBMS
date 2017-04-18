package BookRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to test the book registry
 */
public class BookRegistryTest {

    public static void main(String[] args) {
        BookRegistry br = new BookRegistry(new BookStoreTextDatabase(new File("books.txt")));

        //test search books
        List<Object> params = new ArrayList<>();
        System.out.println("Books title 'Compilers' in store");
        assert br != null;
        System.out.println(
                bookList(
                        br.searchBookStore("Compilers", params)));
    }

    private static String bookList(List<Book> books){

        StringBuilder sb = new StringBuilder();

        for(Book book : books){
            sb.append(book);
            sb.append("\n");
        }

        assert(sb.toString() != null);

        return sb.toString();
    }
}
