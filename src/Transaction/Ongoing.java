package Transaction;

import Transaction.BorrowState;

/**
 * represents the state and behavior of a Borrow that is ongoing.
 */
public class Ongoing implements BorrowState {

    public void increaseFine(Fine fine, int daysLate) {
        //doNothing
    }

    public boolean returnBook(){
        return true;
    }
}
