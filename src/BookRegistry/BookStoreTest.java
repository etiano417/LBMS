package BookRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookStoreTest
{
    public static void main(String[] args)
    {
        File file = new File("C:\\Users\\Shea-RF\\IdeaProjects\\BookRegistry\\src\\books.txt");
        BookStoreTextDatabase db = new BookStoreTextDatabase(file);

        ArrayList<String> a = new ArrayList<>();
        a.add("J. K. Rowling");
        ArrayList<String> z = new ArrayList<>();
        z.add("J.K. Rowling");
        ArrayList<String> q = new ArrayList<>();

        List<Book> searchOut = db.search("Harry Potter", a, Long.parseLong("9781408855713"));
        for(Book b:searchOut)
        {
            System.out.println(b.toString());
        }
        System.out.println("------------");

        searchOut = db.search("Harry Potter", z);
        for(Book b:searchOut)
        {
            System.out.println(b.toString());
        }
        System.out.println("--------------");

        searchOut = db.search("Harry Potter");
        for(Book b:searchOut)
        {
            System.out.println(b.toString());
        }
        System.out.println("--------------");

        searchOut = db.search("", q, Long.parseLong("9781408855713"));
        for(Book b:searchOut)
        {
            System.out.println(b.toString());
        }
        System.out.println("--------------");
    }
}
