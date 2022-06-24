package com.config.configuration.logger.impl;


import com.config.configuration.logger.StratiLogger;
import com.config.configuration.logger.constants.LoggerType;

public class LogFactory {

    enum loggerTypes{
        STRATI
    }
    public static String loggerType;

    public String getLoggerType() {
        return loggerType;
    }

    public void setLoggerType(String loggerType) {
        //Amar::Read it from the Configuration Manager.
        this.loggerType = loggerType;
    }

    public static ILogger getLogger() {
        if(loggerType == null) {
            return new StratiLogger(Thread.currentThread().getStackTrace()[2].getClassName());
        } else if(LoggerType.STRATI.name().equals(loggerType)) {
            return new StratiLogger(Thread.currentThread().getStackTrace()[2].getClassName());
        }
        return null;
    }
}
