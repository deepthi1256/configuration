package com.config.configuration.metrics;

import com.config.configuration.logger.impl.ILogger;
import com.config.configuration.logger.impl.LogFactory;
import com.config.configuration.metrics.impl.IMetrics;
import io.strati.StratiServiceProvider;
import io.strati.metrics.InvalidArgumentException;
import io.strati.metrics.MetricsService;
import io.strati.metrics.instrument.Counter;
import io.strati.metrics.instrument.Gauge;
import io.strati.metrics.instrument.Histogram;
import io.strati.metrics.instrument.Instrument;


public class StratiMetrics implements IMetrics {

    private static ILogger log = LogFactory.getLogger();

    private static MetricsService metricsService;

    private static StratiMetrics instance;
    private StratiMetrics() {
        createMetrics();
    }

    public static IMetrics getInstance() {
        if(instance == null) {
            synchronized (StratiMetrics.class) {
                instance = new StratiMetrics();
                instance.createMetrics();
            }
        }
        return instance;
    }
    public void createMetrics() {
        metricsService = StratiServiceProvider.getInstance().getMetricsService().get();
    }


    @Override
    public void createCounter(String name){
        try {
            metricsService.getMetricRegistry().createCounter(name);
        } catch (InvalidArgumentException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void updateCounter(String name,int value) {
        try {
            Object obj = metricsService.getMetricRegistry().findEnabledMetrics(name);
            if(obj != null) {
                Counter counter = (Counter) obj;
                counter.inc(value);
            }
        } catch (InvalidArgumentException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void createGauges(String name,String unitType){
        try {
            if(unitType == null) {
                unitType = "NotSet";
            }
            metricsService.getMetricRegistry().createGauge(name,Instrument.MeasurementUnit.valueOf(unitType));
        } catch (InvalidArgumentException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void updateGauges(String name,double value){
        try {
            Object obj = metricsService.getMetricRegistry().findEnabledMetrics(name);
            if(obj != null) {
                Gauge gauge = (Gauge) obj;
                gauge.record(value);
            }
        } catch (InvalidArgumentException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void createHistograms(String name,String unitType,double[] supportedPercentail){
        try {
            if(unitType == null) {
                unitType = "NotSet";
            }
            metricsService.getMetricRegistry().createHistogram(name,Instrument.MeasurementUnit.valueOf(unitType),supportedPercentail);
        } catch (InvalidArgumentException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void updateHistograms(String name,double value){
        try {
            Object obj = metricsService.getMetricRegistry().findEnabledMetrics(name);
            if(obj != null) {
                Histogram histogram = (Histogram) obj;
                histogram.record(value);
            }
        } catch (InvalidArgumentException e) {
            log.error(e.getMessage());
        }
    }
}
