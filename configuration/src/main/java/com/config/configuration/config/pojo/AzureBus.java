package com.config.configuration.config.pojo;

import io.strati.configuration.annotation.Configuration;
import io.strati.configuration.annotation.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration(configName = "azure-bus")
public class AzureBus {

    @Property(propertyName = "connection.str")
    public String connectionStr;

    @Property(propertyName = "consumer.topic.name")
    public String consumerTopicName;

    @Property(propertyName = "consumer.subscription.name")
    public String consumerSubscriptionName;

}
