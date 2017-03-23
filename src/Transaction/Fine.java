package Transaction;

import Transaction.Borrow;

/**
 * Represents a single fine owed by a visitor.
 */
public class Fine {
    public Borrow transaction;
    public int fee;
    public boolean paid;

    public Fine(Borrow t){
        transaction = t;
        fee = 10;
        paid = false;
    }

    public String toString(){
        return super.toString();
    }
}

