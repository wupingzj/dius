package au.com.dius.shopping;

public class ProductStoreImpl implements ProductStore {
	private Catalogue catalogue = new Catalogue();

	public void addToCatalogue(Product product) {
		catalogue.add(product);
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public Product getProduct(SKU productSku) {
		return catalogue.get(productSku);
	}
}