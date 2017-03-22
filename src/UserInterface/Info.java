package UserInterface;

import BookRegistry.Book;
import Request.LibraryBookSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Performs a Library Book Search.
 */
public class Info implements UICommand {

    public String perform(List<String> params) {
        List<Object> input = new ArrayList<>();

        ArrayList<String> par = new ArrayList<>();

        for(String s : params){
            par.add(s);
        }

        String title = par.get(0);
        par.remove(0);

        for(String s : par){
            try{
                int x = Integer.parseInt(s);
                input.add(x);
            } catch(NumberFormatException e){
                input.add(s);
            }
        }

        List<Object> results = new LibraryBookSearch(title,input).executeCommand();

        List<Book> books = new ArrayList<>();

        for(Object o : results){
            books.add((Book) o);
        }

        UserInterface.librarySearch = books;

        String output = String.format("info,%d",books.size());
        for(Book b : books){
            output = output + String.format("\n%d,%s,%s;",b.getNumAvailable(),b.getIsbn(),b.toString());
        }
        output = output + ";";

        return null;
    }
}
