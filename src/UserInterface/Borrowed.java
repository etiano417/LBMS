package UserInterface;

import Request.FindBorrowedBooks;
import Transaction.Borrow;

import java.util.List;

/**
 * Creates a FindBorrowedBooks command
 */
public class Borrowed implements UICommand {

    public String perform(List<String> params) {

        List<Object> results = new FindBorrowedBooks(params.get(0)).executeCommand();

        //List<Borrowed> borrows = new ArrayList<>();

        String output = String.format("borrowed,%d",results.size());

        for(int i = 0; i < results.size(); i++){
            Borrow b = (Borrow) results.get(i);
            output = output + String.format("\n%d,%s,%s,d/%02d/%02d",i,b.getBook().getTitle(),
                    b.getBook().getIsbn(),b.getCheckedOut().getYear(),b.getCheckedOut().getMonthValue(),
                    b.getCheckedOut().getDayOfYear());
        }

        output = output + ";";

        return output;
    }
}
