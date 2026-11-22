package ma.lafnoune.bankingapp.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String id) {
        super(String.format("Account %s not found", id));
    }
}
