package javaExceptions;

class OutOfMaxValueException extends Exception {

    private double number;

    OutOfMaxValueException(String message, double number) {
        super(message);
        this.number = number;
    }

    double getNumber () {
        return number;
    }
}
