package com.ariba.procurment.mgmt.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitMQConfig 
{
	@Value("${mq.host.name}")
	private String host;

	@Value("${mq.host.port}")
	private int port;

	@Value("${mq.credentials.username}")
	private String username;

	@Value("${mq.credentials.password}")
	private String password;

	@Value("${mq.retry.initialInterval}")
	private int initialInterval;

	@Value("${mq.retry.maxInterval}")
	private int maxInterval;

	@Value("${mq.retry.backOffMultiplier}")
	private double backOffMultiplier;

	@Value("${mq.retry.maxAttempts}")
	private int maxAttempts;

	@Bean
	public ConnectionFactory getConnectionFactory() 
	{
		log.debug("getConnectionFactory");

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

    @Bean
    public AmqpAdmin getRabbitAdmin(ConnectionFactory connectionFactory)
    {
    	log.debug("getRabbitAdmin");
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RetryTemplate getRetryTemplate() 
    {
		RetryTemplate retryTemplate = new RetryTemplate();

		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(initialInterval);
		backOffPolicy.setMaxInterval(maxInterval);
		backOffPolicy.setMultiplier(backOffMultiplier);
		retryTemplate.setBackOffPolicy(backOffPolicy);

		// set any specific exceptions to retry on or fail on with the constructor
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(maxAttempts);
		retryTemplate.setRetryPolicy(retryPolicy);

		return retryTemplate;
    }

	@Bean
	public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory,RetryTemplate retryTemplate, MessageConverter jackson2JsonMessageConverter) 
	{
		log.debug("getRabbitTemplate");

		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setRetryTemplate(retryTemplate);
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
		return rabbitTemplate;
	}
	
	@Bean
	public MessageConverter jackson2JsonMessageConverter()  
	{
		return new Jackson2JsonMessageConverter();
	}
}
