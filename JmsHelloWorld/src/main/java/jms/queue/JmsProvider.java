package jms.queue;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsProvider {
	
	public static ConnectionFactory getConnectionFactory(){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		return connectionFactory;
	}

}
