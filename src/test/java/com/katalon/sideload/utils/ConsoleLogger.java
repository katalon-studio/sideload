package com.katalon.sideload.utils;

import com.katalon.utils.Logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.SimpleFormatter;

public class ConsoleLogger implements Logger {

    public java.util.logging.Logger logger;

    public ConsoleLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        logger.info(message);
        System.out.println(message);
    }
}
