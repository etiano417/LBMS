package Transaction;

import Transaction.BorrowState;

/**
 * represents the state and behavior of a Borrow that is overdue.
 */
public class Overdue implements BorrowState {
    
    public void increaseFine(Fine fine) {
        //after the first day, fine increases by $10
        //increases by $2 each following week
    }

    public boolean returnBook(){
        return true;
    }
}
