package com.eduedu.chanpin.exception;

/**
 * Created by Administrator on 2016/6/23.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

public class UncaughtException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger( UncaughtException.class.getName());

    private String viewName;
    private ModelMap modelMap = new ModelMap();


    public  UncaughtException(String message, String viewName) {
        super(message);
        this.viewName = viewName;
    }

    public  UncaughtException(String message) {
        super(message);

    }

    public String getViewName() {
        return viewName;
    }

    public ModelMap getModelMap() {
        return modelMap;
    }

    public void  setAttribute(String attributeName, Object attributeValue) {
        this.modelMap.addAttribute(attributeName, attributeValue);
    }
}
