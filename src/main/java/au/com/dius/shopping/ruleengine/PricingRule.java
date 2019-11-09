package au.com.dius.shopping.ruleengine;

import au.com.dius.shopping.Catalogue;
import au.com.dius.shopping.Price;
import au.com.dius.shopping.ShoppingCart;

public interface PricingRule {
	/**
	 * Calculates the products in <code>cart</code>.
	 * 
	 * @param cart the shopping cart that customer would like to checkout
	 * @return the total price of all products in the cart with pricing rule applied
	 */
	public Price apply(Catalogue catalogue, ShoppingCart cart);

}
