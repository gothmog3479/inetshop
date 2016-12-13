package ru.gothmog.service.impl;

import ru.gothmog.controller.RequestParameterName;
import ru.gothmog.service.IService;

import javax.servlet.http.HttpServletRequest;

public class GoAddProductService implements IService {

    private static final GoAddProductService instance = new GoAddProductService();

    public static GoAddProductService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        String idCategory = null;

        idCategory = request.getParameter(RequestParameterName.ID_CATEGORY);

        if (idCategory != null) {
            request.setAttribute(RequestParameterName.ID_CATEGORY, idCategory);
            result = true;
        }
        return result;
    }
}
