package pl.marek.springdata1.app;

class InvalidOptionException extends RuntimeException {
    InvalidOptionException() {
        super("Wrong option");
    }
}