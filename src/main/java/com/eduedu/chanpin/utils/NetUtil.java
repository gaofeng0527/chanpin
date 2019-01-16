package com.eduedu.chanpin.utils;

/**
 * Created by Administrator on 2017/3/3.
 */

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NetUtil {
    private static final Logger logger = LoggerFactory.getLogger(NetUtil.class.getName());

    public static boolean isAjax(HttpServletRequest request) {
        String ajaxHeader = request.getHeader("X-Requested-With");

        if (ajaxHeader != null && "XMLHttpRequest".equalsIgnoreCase(ajaxHeader)) {
            return true;
        } else {
            return false;
        }
    }

    public static void responseAjaxJson (HttpServletResponse response, String jsonMessage) {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(jsonMessage);
            printWriter.flush();
        } catch (IOException ioe) {
            logger.info("Exception when writing ajax response");
            logger.info(ExceptionUtils.getStackTrace(ioe));
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    public static String getUserIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (null != ip && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("x-forwarded-for");
        if (null != ip && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (null != ip && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (null != ip && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("HTTP_CLIENT_IP");
        if (null != ip && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (null != ip && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        ip = request.getRemoteAddr();
        if (null != ip && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        } else {
            return "unknown";
        }
    }

}
