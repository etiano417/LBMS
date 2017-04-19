package Transaction;

import Transaction.Borrow;

import java.io.Serializable;

/**
 * Represents a single fine owed by a visitor.
 */
public class Fine implements Serializable
{
    public Borrow transaction;
    public int fee;
    public boolean paid;

    public Fine(Borrow t)
    {
        transaction = t;
        fee = 10;
        paid = false;
    }

    public String toString()
    {
        return super.toString();
    }
}

