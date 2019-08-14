import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class TextMessageListener implements MessageListener {

	public void onMessage(Message message) {
		
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			String text;
			try {
				text = textMessage.getText();
				System.out.println(text);
			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
			
		}
		else {
			System.out.println("Invalid message received.");
		}
	}

}
