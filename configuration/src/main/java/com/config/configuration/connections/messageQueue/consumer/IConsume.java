package com.config.configuration.connections.messageQueue.consumer;


import com.config.configuration.connections.messageQueue.producer.IProduceCallBack;

import java.util.Map;


public interface IConsume {


    void init(Map<String,Object> properties, IConsumeCallBack callBack);

    void start();

    void close();
}
