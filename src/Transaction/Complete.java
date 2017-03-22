package Transaction;

import Transaction.BorrowState;

/**
 * represents the state and behavior of a Borrow that is paid completely.
 */
public class Complete implements BorrowState {

    public void increaseFine(Fine fine, int daysLate) {
        //doNothing
    }

    public boolean returnBook(){
        return false;
    }

    public boolean owesMoney() {
        return false;
    }
}
