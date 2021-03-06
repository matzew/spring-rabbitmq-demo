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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 * 
 * Launches a Spring-AMQP based publisher service
 */
public class Main 
{
    public static void main( String[] args )
    {
      // get access to the Java based config
      ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfiguration.class);

      // get our service class
      // could use the @Named "rabbitPublishService" value as well, but this would require a type-cast.......
      RabbitPublishService rps = context.getBean(RabbitPublishService.class);

      // launch it!
      rps.run();

      // System.exit(0);
    }
}