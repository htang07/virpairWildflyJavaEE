import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConsumMessageTest1 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConsumeTextMessage() {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context","true");
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Connection connection = null;
		
		try {
			Context ctx = new InitialContext(jndiProperties);
			Queue queue = (Queue)ctx.lookup("jms/BookStoreQueue");
			ConnectionFactory cf = (ConnectionFactory)ctx.lookup("jms/RemoteConnectionFactory");
			connection = cf.createConnection();
			
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(new TextMessageListener());
			//consumer.setMessageListener(new MapMessageListener());
			
			connection.start();
			
			boolean finished = false;
			while (!finished) {
				finished = lookForUserPressingX();
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (JMSException e) {
				
			}
		}

	}
	
	@Test
	public void consumeMapMessage() {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context","true");
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Connection connection = null;
		
		try {
			Context ctx = new InitialContext(jndiProperties);
			Queue queue = (Queue)ctx.lookup("jms/BookStoreQueue");
			ConnectionFactory cf = (ConnectionFactory)ctx.lookup("jms/RemoteConnectionFactory");
			connection = cf.createConnection();
			
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			
			MessageConsumer consumer = session.createConsumer(queue);
			consumer.setMessageListener(new MapMessageListener());
			
			connection.start();
			
			boolean finished = false;
			while (!finished) {
				finished = lookForUserPressingX();
			}
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (JMSException e) {
				
			}
		}
	}
	
	@Test
	public void testReceiveMessageAlternative() {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context","true");
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		
		Connection connection = null;
		
		try {
			Context ctx = new InitialContext(jndiProperties);
			Queue queue = (Queue)ctx.lookup("jms/BookStoreQueue");
			ConnectionFactory cf = (ConnectionFactory)ctx.lookup("jms/RemoteConnectionFactory");
			connection = cf.createConnection();
			
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			connection.start();
			
			//MapMessage mapMessage = (MapMessage)consumer.receiveNoWait(); not to wait, not reliable for certain JMS implementation
			MapMessage mapMessage = (MapMessage)consumer.receive(); //will terminate after process 'single' message received
			if(mapMessage == null) {
				System.out.println("There were no messages");
				return;
			}
			String title = mapMessage.getString("title");
			int sku = mapMessage.getInt("sku");
			double price = mapMessage.getDouble("price");
			long longDate = mapMessage.getLong("date");
			Date date = new Date(longDate);
			
			System.out.println("Sale of " + title + " (" + sku + ") at $" + price + " on " + date);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (JMSException e) {
				
			}
		}
	}
	
	private static boolean lookForUserPressingX() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine().toLowerCase().equals("x");
	}

}
