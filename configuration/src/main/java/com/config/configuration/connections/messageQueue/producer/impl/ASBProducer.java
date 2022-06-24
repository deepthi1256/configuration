package com.config.configuration.connections.messageQueue.producer.impl;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import com.config.configuration.config.pojo.AzureBus;
import com.config.configuration.connections.messageQueue.consumer.IConsumeCallBack;
import com.config.configuration.connections.messageQueue.pojo.Message;
import com.config.configuration.connections.messageQueue.producer.IProduce;
import com.config.configuration.connections.messageQueue.producer.IProduceCallBack;
import com.config.configuration.logger.impl.ILogger;
import com.config.configuration.logger.impl.LogFactory;
import io.strati.configuration.annotation.ManagedConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;

import java.util.Map;

@Component
@Qualifier("asmProducer")
public class ASBProducer implements IProduce {

    public final static String SERVICE_KEY="SERVICE_KEY";

    private static final ILogger log = LogFactory.getLogger();

    @ManagedConfiguration
    AzureBus configurationReader;

    public  enum SERVICE_TYPE {
        SCHEDULE,NON_SCHEDULE
    };

    ServiceBusSenderAsyncClient sender = null;
    Disposable subscription = null;
    Map<String,Object> properties;
    private IProduceCallBack callBack;


    @Override
    public void init(Map<String,Object> properties, IProduceCallBack callBack) {
        sender = new ServiceBusClientBuilder()
                .connectionString(configurationReader.getConnectionStr())
                .sender()
                .topicName(configurationReader.getConsumerTopicName())
                .buildAsyncClient();
        this.properties = properties;
        this.callBack = callBack;
    }

    @Override
    public void process(Message message) {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage(message.getBody());
        if(ASBProducer.SERVICE_TYPE.SCHEDULE.equals(properties.get(SERVICE_KEY))) {
            sender.scheduleMessage(serviceBusMessage,message.getScheduleTimeStamp())
                    .doOnSuccess(unused -> complete())
                    .doOnError(e -> error(e))
                    .subscribe();
        } else {
            sender.sendMessage(serviceBusMessage)
                    .doOnSuccess(unused -> complete())
                    .doOnError(e -> error(e))
                    .doFinally(f -> complete())
                    .subscribe();
        }
    }

    public void complete() {
        log.info("Completed the Producing");
    }

    public void error(Throwable e) {
        log.info("Error while Producing the record"+e);
    }


    @Override
    public void close() {
        // When program ends, or you're done receiving all messages.
        if(subscription != null) {
            subscription.dispose();
        }
        if(sender != null) {
            sender.close();
        }
    }
}
