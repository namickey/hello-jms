package com.sample;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class JmsClient {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        //"failover://tcp://172.17.0.2:61616"
        //ActiveMQConnectionFactory.DEFAULT_BROKER_URL
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://tcp://172.17.0.2:61616");
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        System.out.println("aaaaaaaaaaaaaaaaa");
        ProducerTemplate template = context.createProducerTemplate();
        System.out.println("aaaaaaaaaaaaaaaaa");
        context.start();
        System.out.println("aaaaaaaaaaaaaaaaa");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            template.sendBody("jms:queue:foo", "Test Message: " + i);
        }
        context.stop();
    }
}
