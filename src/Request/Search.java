package Request;

import BookRegistry.Book;
import LBMS.LBMS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Searches for books owned by the library and available for borrowing by visitors.
 */
public abstract class Search implements Request {

    private List<String> authors;
    private Optional<Long> isbn;
    private Optional<String> publisher;
    private Optional<String> sortOrder;

    protected String title;
    protected String clientId;

    public Search(String _title, List<Object> params){
        title = _title;

        authors = new ArrayList<String>();

        clientId = (String) params.remove(0);

        if(!params.isEmpty() && !(params.get(0).equals("*"))) {

            while (!params.isEmpty() && params.get(0) instanceof String) {
                if (params.get(0).equals("*")) {
                    break;
                }
                authors.add((String) params.get(0));
                params.remove(0);
            }
        } else if (!params.isEmpty()) {
            params.remove(0);
        }
        if(!params.isEmpty() && params.get(0) instanceof Long){
            isbn = Optional.of((Long) params.get(0));
        } else if(!params.isEmpty()) {
            isbn = Optional.empty();
            params.remove(0);
        } else {
            isbn = Optional.empty();
        }

        if(!params.isEmpty() && params.get(0) instanceof String) {
            publisher = Optional.of((String) params.get(0));
        } else {
            publisher = Optional.empty();
        }

        if(!params.isEmpty() && params.get(0) instanceof String) {
            sortOrder = Optional.of((String) params.get(0));
        } else {
            sortOrder = Optional.empty();
        }
    }

    public List<Object> executeCommand(){

        List<Object> params = new ArrayList<Object>();
        List<Object> output = new ArrayList<Object>();

        //checks for proper sort-order
        Collection<String> sortOrders = new ArrayList<String>();
        sortOrders.add("title");
        sortOrders.add("publish-date");
        sortOrders.add("book-status");
        if(sortOrder.isPresent() && sortOrders.contains(sortOrder.get())){

            output.add(new Problem("invalid-sort-order","The specified sort order doesn't match one of the expected " +
                    "values."));
            return output;
        }

        params.add(authors);
        params.add(isbn);
        params.add(publisher);
        //params.add(sortOrder);

        List<Book> books = bookList(params);

        //sort the books
        if(sortOrder.isPresent() && sortOrder.get().equals("book-status")){
            for(Book b : books){

                //NOTE: may cause errors. Not entirely sure.
                if(b.getNumAvailable() == 0){
                    books.remove(b);
                }
            }

        } else if(sortOrder.isPresent() && sortOrder.get().equals("title")){
            books.sort(new TitleComparator());
        } else if(sortOrder.isPresent() && sortOrder.get().equals("publish-date")){
            books.sort(new PublishDateComparator());
        }

        //converts the book list to an object list for output, and creates a list of ISBNs

        List<Long> isbnSelection = new ArrayList<>();

        for(Book b : books){
            output.add(b);
            isbnSelection.add(b.getIsbn());
        }

        setUserSelection(isbnSelection);

        return output;
    }

    public List<Object> undoCommand(){
        List<Object> output = new ArrayList<>();
        output.add(new Problem("cannot-undo", "The most recently executed command cannot be undone."));
        return output;
    }

    public abstract List<Book> bookList(List<Object> params);
    public abstract void setUserSelection(List<Long> books);

}
