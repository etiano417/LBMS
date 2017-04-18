package UserInterface;

import BookRegistry.Book;
import Request.BookStoreSearch;
import Request.LibraryBookSearch;
import LBMS.LBMS;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs a Library Book Search.
 */
public class Search implements UICommand {

    public String perform(List<String> params) {

        String clientId = params.remove(0);

        if(!LBMS.ur.isEmployee(clientId)){
            return EmployeeChecking.message(clientId,"search");
        }

        List<Object> input = new ArrayList<>();

        if(params.size() < 1){
            return String.format("%s,search,missing-parameters,title;",clientId);
        }

        ArrayList<String> par = new ArrayList<>();

        for(String s : params){
            par.add(s);
        }

        String title = par.get(0);
        par.remove(0);

        for(String s : par){
            try{
                Long x = Long.parseLong(s);
                input.add(x);
            } catch(NumberFormatException e){
                input.add(s);
            }
        }

        input.add(0,clientId);
        List<Object> results = new BookStoreSearch(title,input).executeCommand();

        List<Book> books = new ArrayList<>();

        for(Object o : results){
            books.add((Book) o);
        }

        UserInterface.librarySearch = books;

        String output = String.format("%s,search,%d",clientId,books.size());
        for(Book b : books){
            output = output + String.format("\n%d,%s,%s;",b.getNumAvailable(),b.getIsbn(),b.toString());
        }

        return output;
    }
}
