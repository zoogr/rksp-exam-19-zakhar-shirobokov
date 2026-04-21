package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EVENTS_RAW_QUEUE = "events.raw";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(EVENTS_RAW_QUEUE);
        container.setMessageListener(listenerAdapter);
        container.setConcurrentConsumers(3);
        container.setMaxConcurrentConsumers(10);
        container.setAcknowledgeMode(org.springframework.amqp.core.AcknowledgeMode.MANUAL);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RawMessageHandler handler) {
        return new MessageListenerAdapter(handler, "handleMessage");
    }
}