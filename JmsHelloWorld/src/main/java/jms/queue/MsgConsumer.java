package jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MsgConsumer implements MessageListener{

	private Connection connection;
	private Session session;
	
	public void startListener() throws JMSException{
		System.out.println("Listener starting ...");
		ConnectionFactory connectionFactory = JmsProvider.getConnectionFactory();
		this.connection = connectionFactory.createConnection();
		connection.start();
		
		this.session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("example.queue");
		MessageConsumer consumer = session.createConsumer(queue);
		consumer.setMessageListener(this);
	}
	
	@Override
	public void onMessage(Message msg) {

		if(msg instanceof TextMessage){
			
			TextMessage txtMsg = (TextMessage) msg;
			
			try {
				
				System.out.printf("message received : %s, Thread : %s%n", txtMsg.getText(), Thread.currentThread().getName());
				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void destroy() throws JMSException{
		connection.close();
	}
	
}
