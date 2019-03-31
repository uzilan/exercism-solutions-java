public class BankAccount {

    private static final String WITHDRAWING_TOO_MUCH = "Cannot withdraw more money than is currently in the account";
    private static final String EMPTY_ACCOUNT = "Cannot withdraw money from an empty account";
    private static final String NEGATIVE_AMOUNT = "Cannot deposit or withdraw negative amount";
    private static final String ACCOUNT_CLOSED = "Account closed";
    private int account;
    private boolean open;

    public void open() {
        synchronized (this) {
            this.open = true;
        }
    }

    public void close() {
        synchronized (this) {
            this.open = false;
        }
    }

    public int getBalance() throws BankAccountActionInvalidException {
        synchronized (this) {
            checkIfAccountIsClosed(open);
            return this.account;
        }
    }

    public void deposit(int amount) throws BankAccountActionInvalidException {
        synchronized (this) {
            checkIfAccountIsClosed(open);
            checkForNegativeAmount(amount);
            this.account += amount;
        }
    }

    public void withdraw(int amount) throws BankAccountActionInvalidException {
        synchronized (this) {
            checkIfAccountIsClosed(open);
            checkForNegativeAmount(amount);
            checkIfAccountIsZero(account);
            checkIfWithdrawingTooMuch(account, amount);
            this.account -= amount;
        }
    }

    private static void checkIfWithdrawingTooMuch(int account, int amount)
            throws BankAccountActionInvalidException {
        if (account - amount < 0) {
            throw new BankAccountActionInvalidException(WITHDRAWING_TOO_MUCH);
        }
    }

    private static void checkIfAccountIsZero(int account)
            throws BankAccountActionInvalidException {
        if (account == 0) {
            throw new BankAccountActionInvalidException(EMPTY_ACCOUNT);
        }
    }

    private static void checkForNegativeAmount(int amount)
            throws BankAccountActionInvalidException {
        if (amount < 0) {
            throw new BankAccountActionInvalidException(NEGATIVE_AMOUNT);
        }
    }

    private static void checkIfAccountIsClosed(boolean open)
            throws BankAccountActionInvalidException {
        if (!open) {
            throw new BankAccountActionInvalidException(ACCOUNT_CLOSED);
        }
    }
}
