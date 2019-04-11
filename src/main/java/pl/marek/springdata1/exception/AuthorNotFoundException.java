package pl.marek.springdata1.exception;

public class AuthorNotFoundException extends RuntimeException {
   public AuthorNotFoundException(String message) {
        super(message);
    }
}
