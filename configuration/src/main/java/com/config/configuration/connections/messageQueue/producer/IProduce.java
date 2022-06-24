package com.config.configuration.connections.messageQueue.producer;


import com.config.configuration.connections.messageQueue.pojo.Message;

import java.util.Map;

public interface IProduce {

    void init(Map<String,Object> properties, IProduceCallBack callBack);
    void process(Message message);
    void close();
}
