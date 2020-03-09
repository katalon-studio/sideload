package com.katalon.sideload.utils;

import com.katalon.utils.Logger;

public class LogUtils {

    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(LogUtils.class.getName());

    public static void info(Logger logger, String message) {
        if (logger == null) {
            logger = new ConsoleLogger(LOGGER);
        }
        logger.info(message);
    }
}
