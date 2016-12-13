package ru.gothmog.service.impl;

import org.apache.log4j.Logger;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class ShowBlacklistService implements IService {

    private static final Logger logger = Logger.getLogger(ShowBlacklistService.class);

    private static final ShowBlacklistService instance = new ShowBlacklistService();

    public static ShowBlacklistService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        return false;
    }
}
