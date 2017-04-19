package UserInterface;

import Request.FindBorrowedBooks;
import Request.Problem;
import Transaction.Borrow;
import LBMS.LBMS;

import java.util.List;

/**
 * Creates a FindBorrowedBooks command
 */
public class Borrowed implements UICommand {

    public String perform(List<String> params) {

        String clientId = params.remove(0);

        String visitorId;

        if(params.isEmpty()){
            visitorId = LBMS.ur.getVisitor(clientId);
        } else {
            visitorId = params.get(0);
        }

        List<Object> results = new FindBorrowedBooks(clientId,visitorId).executeCommand();

        //List<Borrowed> borrows = new ArrayList<>();

        if((!results.isEmpty())&& results.get(0) instanceof Problem){
            return String.format("%s,borrowed,%s",clientId,results.get(0));
        }

        String output = String.format("%s,borrowed,%d",clientId,results.size());

        for(int i = 0; i < results.size(); i++){
            Borrow b = (Borrow) results.get(i);
            output = output + String.format("\n%d,%s,%s,%d-%02d-%02d",i + 1,b.getBook().getTitle(),
                    b.getBook().getIsbn(),b.getCheckedOut().getYear(),b.getCheckedOut().getMonthValue(),
                    b.getCheckedOut().getDayOfMonth());
        }

        output = output + ";";

        return output;
    }
}