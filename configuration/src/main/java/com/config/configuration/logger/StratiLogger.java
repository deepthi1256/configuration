package com.config.configuration.logger;

import com.config.configuration.logger.impl.ILogger;
import io.strati.StratiServiceProvider;


public class StratiLogger implements ILogger {
    private final org.slf4j.Logger log;

    public StratiLogger(String className) {
        log = StratiServiceProvider.getLogger(className);
    }

    public void info(final String format) {
        log.info( format);
    }

    public void info(final String format, final Object o) {
        log.info( format, o);
    }

    public void info(String format, Object arg1, Object arg2) {
        log.info(format, arg1, arg2);
    }

    public void info(final String format, final Object... args) {
        log.info( format, args);
    }

    public void debug(String format, Object... arguments) { log.debug( format, arguments); }

    public void warn(final String format) {
        log.warn( format);
    }

    public void warn(String format, Object arg) { log.warn( format, arg); }

    public void warn(String format, Object... arguments) { log.warn(format, arguments); }

    public void error(final String format) {
        log.error( format);
    }

    public void error(final String format, final Object o) {
        log.error( format, o);
    }

    public void error(final String format, final Object arg1, final Object arg2) {
        log.error(format, arg1, arg2);
    }

    public void error(String format, Object... arguments) {
        log.error( format, arguments);
    }
}
