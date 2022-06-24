package com.config.configuration.connections.messageQueue.consumer;

import com.config.configuration.connections.messageQueue.pojo.Message;

public interface IConsumeCallBack {
    void process(Message message);

}
