package au.com.dius.shopping;

import java.util.List;
import java.util.Map;

/**
 * Cutomer's shopping cart.
 * 
 * For this test, we assume customer would like checkout all products in the
 * shopping cart.
 * 
 * @author ping
 *
 */
public interface ShoppingCart {
	public void addProduct(Product product);

	public List<Product> getProducts();

	// calculates the product occurrences
	public Map<Product, Integer> countProducts();

	/**
	 * Gets the total price without applying rules
	 * 
	 * @return
	 */
	public Price getTotal();
}
