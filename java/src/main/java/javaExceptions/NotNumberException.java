package javaExceptions;

class NotNumberException extends Exception {

    private String convertedString;

    NotNumberException(String message, String convertedString) {
        super(message);
        this.convertedString = convertedString;
    }

    String getConvertedString () { return convertedString;}
}
