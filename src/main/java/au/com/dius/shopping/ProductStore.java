package au.com.dius.shopping;

public interface ProductStore {
	/**
	 * Gets product with given <code>productSku</code> from catalogue
	 * 
	 * @param productSku
	 * @return
	 */
	public Product getProduct(SKU productSku);

	/**
	 * Adds given <code>product</code> to catalogue
	 * 
	 * @param product
	 */
	public void addToCatalogue(Product product);

	/**
	 * Gets product catalogue
	 */
	public Catalogue getCatalogue();
}
