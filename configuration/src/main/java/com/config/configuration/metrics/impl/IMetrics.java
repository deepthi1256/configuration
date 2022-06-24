package com.config.configuration.metrics.impl;

import io.strati.metrics.InvalidArgumentException;

public interface IMetrics {

    /**
     *  The counter metric type is used for any value that increases, such as a request count or error count.
     *  Importantly, a counter should never be used for a value that can decrease
     * @param name
     */
    void createCounter(String name) throws InvalidArgumentException;

    /**
     *  Increment Counter to a certain Value
     * @param name
     * @param value
     */
    void updateCounter(String name,int value) throws InvalidArgumentException;


    /**
     * Update Gauge
     * @param name
     * @param value
     */
    void updateHistograms(String name,double value);

    /**
     *The histogram metric type measures the frequency of value observations that fall into specific predefined buckets.
     * you want to take many measurements of a value, to later calculate averages or percentiles
     * you’re not bothered about the exact values, but are happy with an approximation
     * you know what the range of values will be up front, so can use the default bucket definitions or define your own
     * @param name
     * @param unitType Default is NotSet
     * @param supportedPercentail Not mandatory Default is .005, .01, .025, .05, .075, .1, .25, .5, .75, 1, 2.5, 5, 7.5, 10.
     * Allowed UnitTypes are "NotSet","Calls","Reqs",XCEPTION_COUNT("Excptns"),FAILED_COUNT("Failures"),BYTE("Bytes"),KB,MB,GB;
     *
     */
    void createHistograms(String name,String unitType,double[] supportedPercentail);

    /**
     * Update Gauge
     * @param name
     * @param value
     */
    void updateGauges(String name,double value);

    /**
     *  The gauge metric type can be used for values that go down as well as up, such as current memory usage or the number of items in a queue.
     * @param name
     * @param unitType Default is NotSet
     * Allowed UnitTypes are "NotSet","Calls","Reqs",XCEPTION_COUNT("Excptns"),FAILED_COUNT("Failures"),BYTE("Bytes"),KB,MB,GB;
     */
    void createGauges(String name,String unitType);
}
