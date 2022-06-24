package com.config.configuration.logger.impl;

public interface ILogger {
    void info(final String format);

    void info(final String format, final Object o);

    void info(String format, Object arg1, Object arg2);

    public void info(final String format, final Object... args);

    public void debug(String format, Object... arguments);

    public void warn(final String format);

    public void warn(String format, Object arg) ;

    public void warn(String format, Object... arguments) ;

    public void error(final String format);

    public void error(final String format, final Object o);

    public void error(final String format, final Object arg1, final Object arg2);

    public void error(String format, Object... arguments);
}
