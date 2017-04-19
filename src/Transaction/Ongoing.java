package Transaction;

import Transaction.BorrowState;

import java.io.Serializable;

/**
 * represents the state and behavior of a Borrowed that is ongoing.
 */
public class Ongoing implements BorrowState, Serializable
{
    public void increaseFine(Fine fine, int daysLate)
    {
        //doNothing
    }

    public boolean returnBook(){
        return true;
    }

    public boolean owesMoney() {
        return false;
    }
}
