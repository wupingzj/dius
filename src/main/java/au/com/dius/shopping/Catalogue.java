package au.com.dius.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 * The product catalogue that holds all mapping of SKU code to product.
 */
public class Catalogue {
	public Map<String, Product> catalogue = new HashMap<String, Product>();

	/**
	 * Adds given product into catalogue
	 * 
	 * @param p
	 */
	public void add(Product p) {
		catalogue.put(p.getSku(), p);
	}

	/**
	 * Gets product with given <code>sku</code>from catalogue
	 * 
	 * @param sku SKU code
	 */
	public Product get(String sku) {
		return catalogue.get(sku);
	}

	public Map<String, Product> getAll() {
		return this.catalogue;
	}
}
