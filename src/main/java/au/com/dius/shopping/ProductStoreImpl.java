package au.com.dius.shopping;

public class ProductStoreImpl implements ProductStore {
	private Catalogue catalogue = new Catalogue();

	public void addToCatalogue(Product product) {
		catalogue.add(product);
	}
}
