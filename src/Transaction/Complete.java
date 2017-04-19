package Transaction;

import Transaction.BorrowState;

import java.io.Serializable;

/**
 * represents the state and behavior of a Borrowed that is paid completely.
 */
public class Complete implements BorrowState, Serializable
{
    public void increaseFine(Fine fine, int daysLate)
    {
        //doNothing
    }

    public boolean returnBook(){
        return false;
    }

    public boolean owesMoney() {
        return false;
    }
}
