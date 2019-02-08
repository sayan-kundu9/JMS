package jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MsgProducer {
	
	private  MessageProducer producer;
	private  Session session;
	private Connection connection;
	
	public MsgProducer() throws JMSException {
		ConnectionFactory connectionFactory = JmsProvider.getConnectionFactory();
		this.connection = connectionFactory.createConnection();
		System.out.println("connection created ...");
		connection.start();
		System.out.println("connection started ...");
		
		this.session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("example.queue");
		this.producer = session.createProducer(queue);
	}
	
	public void sendMsg(String msg) throws JMSException
	{
		System.out.printf("sending message : %s, Thread : %s%n", msg, Thread.currentThread().getName());
		TextMessage txtMsg = session.createTextMessage(msg);
		System.out.println("msg created ...");
		producer.send(txtMsg);
		System.out.println("msg sent.");
	}
	
	public void destroy() throws JMSException
	{
		connection.close();
		System.out.println("sender/producer destroid.");
	}

}
