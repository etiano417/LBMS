package Request;
import BookRegistry.Book;
import LBMS.LBMS;

import java.util.ArrayList;
import java.util.List;

public class BookPurchase {
    public String clientId;
    public int quantity;
    public List<Integer> books;

    public BookPurchase(String _clientId, int _quantity, List<Integer> _books){
        clientId = _clientId;
        quantity = _quantity;
        books = _books;
    }

    public List<Object> executeCommand(){

        List<Object> output = new ArrayList<Object>();
        if(!LBMS.ur.isEmployee(clientId)){
            output.add(new Problem("not-authorized",""));
            return output;
        }

        //gathers the selected books
        List<Long> isbnList = new ArrayList<>();
        for(int bookId : books){
            Long isbn = LBMS.ur.selectStoreBook(clientId, bookId);
            if (isbn == -1) {
                output.add(new Problem("invalid-book-id", ""));
                return output;
            }
            isbnList.add(isbn);
        }

        List<Book> bookList = new ArrayList<>();

        for(Long isbn : isbnList){
            LBMS.br.purchaseBook(isbn,quantity);
            bookList.add(LBMS.br.getBook(isbn));
        }

        List<Object> books = new ArrayList<>();
        for(Book b : bookList){
            books.add(b);
        }
        /**
        String books = String.format("success,%d\n",bookList.size());
        for(Book b: bookList){
            String authors = "";
            for(String author : b.getAuthors()){
                authors = authors + author + ",";
            }
            books = books + String.format("%d,%s,%s,%s,%d\n",b.getIsbn(),b.getTitle(),authors,b.getPublishDate(),
                    b.getNumAvailable());
        }
         **/
        return books;
    }
}
