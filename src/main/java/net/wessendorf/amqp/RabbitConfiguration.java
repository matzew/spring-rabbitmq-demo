/*
 * Copyright 2012 Matthias Wessendorf.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.wessendorf.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Defines the Spring components...
 */
@Configuration
public class RabbitConfiguration {


    // ================== RabbitMQ config section ==================
    
    @Bean
    public ConnectionFactory connectionFactory() {

      CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
      
      // apply some config settings:
      connectionFactory.setUsername("guest");
      connectionFactory.setPassword("guest");
      connectionFactory.setVirtualHost("/");
      connectionFactory.setPort(5672);

      return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
      rabbitTemplate.setExchange("demo_exchange");
      //rabbitTemplate.setQueue("myqueue");
      return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
      return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue myQueue() {
      Queue queue = new Queue("myqueue");
      return queue;
    }
    
    // ================== custom service/code section ==================

    @Bean
    public RabbitPublishService rabbitPublishService()
    {
        return new RabbitPublishService();
    }
    
}