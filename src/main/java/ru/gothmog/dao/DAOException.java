package ru.gothmog.dao;

import ru.gothmog.exception.ProjectException;

/**
 * @author Dmitriy Grushetskiy on 10.12.2016.
 */
public class DAOException extends ProjectException {

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String msg, Exception e) {
        super(msg, e);
    }
}
