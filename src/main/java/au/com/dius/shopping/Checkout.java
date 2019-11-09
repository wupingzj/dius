package au.com.dius.shopping;

public interface Checkout {
	void scan(SKU sku);

    Price total();
}
