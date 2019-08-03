import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class ShoppingCartSessionImplementation implements ShoppingCartSession {

	private List<String> items;
	
	public ShoppingCartSessionImplementation() {
		this.items = new ArrayList<String>();
	}

	public void addItem(String newItem) {
		this.items.add(newItem);
		
	}

	public List<String> getCartContents() {
		return this.items;
	}

	@Remove
	public void checkout() {
		// add the items to a database or something
		
	}
	
	
}
