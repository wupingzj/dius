package au.com.dius.shopping;

public interface Checkout {
	void read(String sku);

    Price total();
}
