package Transaction;

import Transaction.BorrowState;

/**
 * represents the state and behavior of a Borrowed that is overdue.
 */
public class Overdue implements BorrowState {
    
    public void increaseFine(Fine fine, int daysLate) {
        fine.fee = 10;  //late fee after first week is $10
        for(int i=14; i<=daysLate; i+=7)
            fine.fee+=2;   //fee increases by $2 weekly until returned
    }

    public boolean returnBook(){
        return true;
    }
}
