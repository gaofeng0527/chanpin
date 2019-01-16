package com.eduedu.chanpin.utils;

/**
 * Created by Administrator on 2017/3/7.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ValidationUtil {
    private static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class.getName());

    public static String concatenateMessages(List<String> messages) {
        String concatenatedMessage = "";
        for (String message : messages) {
            concatenatedMessage += message + ";";
        }

        return concatenatedMessage;
    }
}
