package au.com.dius.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 * The product catalogue that holds all mapping of SKU code to product.
 */
public class Catalogue {
	public Map<SKU, Product> catalogue = new HashMap<SKU, Product>();

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
	 * @param sku SKU
	 */
	public Product get(SKU sku) {
		return catalogue.get(sku);
	}

	public Map<SKU, Product> getAll() {
		return this.catalogue;
	}
}
