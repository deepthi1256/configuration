package com.config.configuration.metrics;


import com.config.configuration.metrics.constants.MetricsType;
import com.config.configuration.metrics.impl.IMetrics;

public class MetricsFactory {

    public static String metricsType;

    public String getLoggerType() {
        return metricsType;
    }

    public void setLoggerType(String metricsType) {
        //Amar::Read it from the Configuration Manager.
        this.metricsType = metricsType;
    }

    public static IMetrics getMetrics() {
        if(metricsType == null) {
            return StratiMetrics.getInstance();
        } else if(MetricsType.STRATI.name().equals(metricsType)) {
            return StratiMetrics.getInstance();
        }
        return null;
    }
}
