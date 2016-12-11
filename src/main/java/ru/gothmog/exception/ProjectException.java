package ru.gothmog.exception;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public class ProjectException extends Exception {

    private Exception hiddenException;

    public ProjectException(String msg) {
        super(msg);
    }

    public ProjectException(String msg, Exception e) {
        super(msg);
        hiddenException = e;
    }

    public Exception getHiddenException() {
        return hiddenException;
    }
}
