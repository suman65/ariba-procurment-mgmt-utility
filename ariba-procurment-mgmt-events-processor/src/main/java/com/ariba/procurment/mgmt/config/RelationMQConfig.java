package com.ariba.procurment.mgmt.config;


import static com.ariba.procurment.mgmt.util.MQConstants.*;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ariba.procurment.mgmt.listener.FileUploadListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RelationMQConfig 
{

  @Autowired
  private AmqpAdmin rabbitAdmin;
  
  @Value("${mq.job.workers}")
  private int jobWorkers;

  @Bean(name = "aribaDirectExchange")
  public DirectExchange getDirectExchange() 
  {
    return new DirectExchange(ARIBA_EXCHANGE_NAME, true, false);
  }
  
  @Bean(name = "fileUploadQueue")
  public Queue getFileUploadQueue() 
  {
    return new Queue(FILE_UPLOAD_QUEUE_NAME, true);
  }
  
  
  @Bean(name = "fileUploadBinding")
  Binding getFileUploadBinding(Queue fileUploadQueue, DirectExchange aribaDirectExchange) 
  {
    return BindingBuilder.bind(fileUploadQueue).to(aribaDirectExchange).with(FILE_UPLOAD_ROUTING_KEY);
  }

  @PostConstruct
  public void initializeMessageQueue() 
  {
    DirectExchange directExchange = getDirectExchange();
    rabbitAdmin.declareExchange(directExchange);
    
    rabbitAdmin.declareQueue(getFileUploadQueue());
    rabbitAdmin.declareBinding(getFileUploadBinding(getFileUploadQueue(), directExchange));
  }
  
  @Bean
  public SimpleMessageListenerContainer fileUploadListenerContainer(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter,MessageConverter jsonMessageConverter) 
  {
    log.debug("fileUploadListenerContainer");

    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(FILE_UPLOAD_QUEUE_NAME);
    container.setMaxConcurrentConsumers(jobWorkers);
    container.setMessageListener(listenerAdapter);
    listenerAdapter.setMessageConverter(jsonMessageConverter);
    return container;
  }
  
  @Bean
  MessageListenerAdapter listenerAdapter(FileUploadListener receiver) {
      return new MessageListenerAdapter(receiver, "processRequest");
  }
}