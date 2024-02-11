package com.gleb.ratingmovies.util;

import com.gleb.ratingmovies.controller.command.util.Parameter;
import com.gleb.ratingmovies.controller.command.request.RequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParameterTaker {

    private static final Logger logger = LogManager.getLogger();


    public static String takeString(String parameterName, RequestContext requestContext) {
        return requestContext.getRequestParameter(parameterName);
    }

    public static long takeId(RequestContext requestContext) {
        String idStr = requestContext.getRequestParameter(Parameter.ID);
        return Long.parseLong(idStr);

    }

    public static long takeIdNow(RequestContext requestContext) {
        String idStr = requestContext.getRequestParameter("movieId");
        return Long.parseLong(idStr);

    }

    public static int takeNumber(String parameterName, RequestContext requestContext) {
        String numberStr = requestContext.getRequestParameter(parameterName);
        int number = -1;
        try {
            number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            logger.warn(e);
        }
        return number;
    }
    public static double takeBinaryNumber(String parameterName, RequestContext requestContext) {
        String str = requestContext.getRequestParameter(parameterName);
        Double number = 5.0;
        try {
            // Парсим строку в целое число с указанием основания 2
            number = Double.valueOf(str);;
        } catch (NumberFormatException e) {
            logger.warn(e);
        }
        return number;
    }


}
