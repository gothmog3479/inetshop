package ru.gothmog.dao.settings;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
