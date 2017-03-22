package Transaction;

/**
 * Represents the state of a Borrow object, and it's behavior based on that state.
 */
public interface BorrowState {
    void increaseFine(Fine fine, int daysLate);
    boolean returnBook();
    boolean owesMoney();
}
