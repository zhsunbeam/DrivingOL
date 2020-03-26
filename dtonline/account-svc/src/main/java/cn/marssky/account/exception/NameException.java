package cn.marssky.account.exception;

public class NameException extends Exception {

    public NameException(String message) {
        super(message);
        System.out.println(getLocalizedMessage());
    }
}
