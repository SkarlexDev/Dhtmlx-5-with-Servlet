package com.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;

public class RequestHelper {

    /*
     * This method will display a pair of key value from HttpServletRequest
     * Mainly used for debug and test
     */
    public static void displayRequest(HttpServletRequest req) {
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println(paramName + " : " + req.getParameter(paramName));
        }
    }
}
