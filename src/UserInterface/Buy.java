package UserInterface;

import BookRegistry.Book;
import Request.BookPurchase;
import Request.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Tiano on 4/16/2017.
 */
public class Buy implements UICommand {
    public String perform(List<String> params){
        String clientId = params.remove(0);

        if(params.size() < 2){
            List<String> requiredParameters = new ArrayList<>();
            requiredParameters.add("quantity");
            requiredParameters.add("id");
            return MissingParameters.missingParameters(requiredParameters,params.size());
        }

        int quantity = Integer.parseInt(params.remove(0));

        List<Integer> ids = new ArrayList<>();

        for(String p : params){
            ids.add(Integer.parseInt(p));
        }

        List<Object> result = new BookPurchase(clientId,quantity,ids).executeCommand();

        if(!(result.isEmpty()) && result.get(0) instanceof Problem){
            return String.format("%s,buy,%s;",clientId,result.get(0));
        }

        List<Book> books = new ArrayList<>();
        for(Object b : result){
            Book book = (Book) b;
            books.add(book);
        }

        String bookOutput = String.format("success,%d\n",books.size());
        for(Book b: books){
            String authors = "";
            for(String author : b.getAuthors()){
                authors = authors + author + ",";
            }
            bookOutput = bookOutput + String.format("%d,%s,%s,%s,%d\n",b.getIsbn(),b.getTitle(),authors,b.getPublishDate(),
                    b.getNumAvailable());
        }
        return bookOutput;
    }
}
