package jms.queue;

import javax.jms.JMSException;

public class MainClass {

	public static void main(String[] args) throws JMSException, InterruptedException {
		
		final MsgProducer msgProducer = new MsgProducer();
		final MsgConsumer msgConsumer = new MsgConsumer();
		
		msgConsumer.startListener();
		
		for (int i = 0; i < 4; i++) {
			String msg = "Hello world" + i;
			msgProducer.sendMsg(msg);
			Thread.sleep(5000);
		}
		
		msgProducer.destroy();
		msgConsumer.destroy();
		
	}
}
