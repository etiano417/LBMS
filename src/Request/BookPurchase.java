package Request;
import LBMS.LBMS;

import java.util.ArrayList;
import java.util.List;

public class BookPurchase {
    public int quantity;
    public List<Integer> books;

    public BookPurchase(int _quantity, List<Integer> _books){
        quantity = _quantity;
        books = _books;
    }

    public List<Object> executeCommand(){
        return null;
    }
}
