import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import java.util.Properties;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestClient1 {

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
	public void testShoppingCart() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context",true);
		
		Context jndi = new InitialContext(jndiProperties);
		
		ShoppingCartSession cart = (ShoppingCartSession) jndi.lookup("ShoppingCartServer/ShoppingCartSessionImplementation!ShoppingCartSession");
		
		cart.addItem("Bath towels");
		cart.addItem("soap");
		List<String> items = cart.getCartContents();
		for (String item : items) {
			System.out.println(item);
		}
		
		cart.checkout();
	}

}
