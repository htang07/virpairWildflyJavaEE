import java.util.List;

import javax.ejb.Remote;

public interface ShoppingCartSession {
	public void addItem(String newItem);
	public List<String> getCartContents();
	public void checkout();
}
