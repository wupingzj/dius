package au.com.dius.shopping;

import au.com.dius.shopping.ruleengine.PricingRule;

public class CheckoutImpl implements Checkout {
	private PricingRule pr;
	private ProductStore ps;
	private ShoppingCart cart;

	/**
	 * When a customer is served, a new shopping cart is created for this customer.
	 * 
	 * @param pr pricing rule
	 * @param ps product store
	 */
	public CheckoutImpl(PricingRule pr, ProductStore ps) {
		this.pr = pr;
		this.ps = ps;
		this.cart = new ShoppingCartImpl();
	}

	public void scan(SKU sku) {
		if (sku == null) {
			throw new IllegalArgumentException("SKU cannot be null or empty");
		}
		
		Product product = ps.getProduct(sku);
		if (product == null) {
			throw new IllegalArgumentException(String.format("The product %s is not valid.", product));
		}

		this.cart.addProduct(product);

	}

	public Price total() {
		Price total = pr.apply(ps.getCatalogue(), this.cart);

		return total;
	}
}
