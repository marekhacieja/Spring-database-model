package pl.marek.springdata1.app;

enum Options {
    EXIT(0, "Exit"),
    ADD_BOOK(1, "Add book"),
    ADD_AUTHOR(2, "Add author"),
    ADD_CUSTOMER(3, "Add customer"),
    SHOW_BOOKS(4, "Show all books"),
    SHOW_CUSTOMERS(5, "Show all customers"),
    SHOW_AUTHORS(6, "Show all authors"),
    RENT(7, "Rent book"),
    REMOVE_DEVICE(8, "Remove book"),
    REMOVE_CATEGORY(9, "Remove author"),
    REMOVE_CUSTOMER(10, "Remove curstomer");


    private int number;
    private String name;

    Options(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return number + ". " + name;
    }

    static Options chooseOption(int number) {
        if(number < 0 || number > values().length)
            throw new InvalidOptionException();
        return values()[number];
    }
}
