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
		// TODO Auto-generated method stub
	}

	public Price total() {
		// TODO Auto-generated method stub
		throw new RuntimeException("To be impletemented");
	}
}
