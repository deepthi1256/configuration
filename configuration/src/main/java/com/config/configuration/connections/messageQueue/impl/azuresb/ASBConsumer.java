package com.config.configuration.connections.messageQueue.impl.azuresb;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceiverAsyncClient;
import com.config.configuration.config.pojo.AzureBus;
import com.config.configuration.connections.messageQueue.consumer.IConsumeCallBack;
import com.config.configuration.connections.messageQueue.consumer.IConsume;
import com.config.configuration.connections.messageQueue.pojo.Message;
import io.strati.configuration.annotation.ManagedConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;

import java.util.Map;


@Component
@Qualifier("asmConsumer")
public class ASBConsumer implements IConsume {

    ServiceBusReceiverAsyncClient receiver = null;
    Disposable subscription = null;
    private IConsumeCallBack callBack;

    @ManagedConfiguration
    AzureBus configurationReader;

    public void init(Map<String,Object> properties, IConsumeCallBack callBack) {
        receiver = new ServiceBusClientBuilder()
                .connectionString(configurationReader.getConnectionStr())
                .receiver()
                .topicName(configurationReader.getConsumerTopicName())
                .subscriptionName(configurationReader.getConsumerSubscriptionName())
                .buildAsyncClient();
        this.callBack = callBack;
    }




    @Override
    public void start() {
        subscription = receiver.receiveMessages()
                .subscribe(message -> this.callBack.process(new Message(message.getBody().toString(),message.getSequenceNumber())),
                        error -> error(error),
                        () -> complete());
    }

    public void error(Throwable error) {
        System.out.println(error.getMessage());
    }

    public void complete() {
        System.out.println("Completed the record Processing");
    }

    @Override
    public void close() {
        // When program ends, or you're done receiving all messages.
        if(subscription != null) {
            subscription.dispose();
        }
        if(receiver != null) {
            receiver.close();
        }
    }
}
