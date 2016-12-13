package ru.gothmog.service;

import javax.servlet.http.HttpServletRequest;

public interface IService {

    boolean doService(HttpServletRequest request);
}
