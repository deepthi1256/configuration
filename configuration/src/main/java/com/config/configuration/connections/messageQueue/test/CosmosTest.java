package com.config.configuration.connections.messageQueue.test;

import com.config.configuration.config.pojo.AzureBus;
import com.config.configuration.connections.messageQueue.consumer.IConsumeCallBack;
import com.config.configuration.connections.messageQueue.consumer.IConsume;
import com.config.configuration.connections.messageQueue.pojo.Message;
import com.config.configuration.connections.messageQueue.producer.IProduce;
import com.config.configuration.connections.messageQueue.producer.IProduceCallBack;
import com.config.configuration.connections.messageQueue.producer.impl.ASBProducer;
import com.config.configuration.logger.impl.ILogger;
import com.config.configuration.logger.impl.LogFactory;
import io.strati.configuration.annotation.ManagedConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CosmosTest {

    private static final ILogger log = LogFactory.getLogger();

    @ManagedConfiguration
    AzureBus azureBus;

    private final IConsume consume;

    private final IProduce produce;

    public CosmosTest(@Qualifier("asmConsumer") IConsume consume,@Qualifier("asmProducer") IProduce produce) {
        this.consume = consume;
        this.produce = produce;
        this.consume.init(new HashMap<>(),new AsbConsume());
        this.consume.start();
        Map<String,Object> pConfig = new HashMap<>();
        pConfig.put("SERVICE_KEY",ASBProducer.SERVICE_TYPE.NON_SCHEDULE);
        this.produce.init(pConfig, new AsbProducer());
        Message message = new Message("dummyMessage");
        this.produce.process(message);
        //this.consume.close();
        //this.produce.close();
    }


    public class AsbProducer implements IProduceCallBack {

    }

    public class AsbConsume implements IConsumeCallBack {

        @Override
        public void process(Message message) {
            log.info("Consumption is Succesfull:: "+ message.getBody());
        }
    }
}
