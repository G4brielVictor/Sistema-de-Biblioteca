package exceptions;

public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException(String message) {
        super(message);
    }
}
