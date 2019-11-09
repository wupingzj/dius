package au.com.dius.shopping.ruleengine.rule;

import java.math.BigDecimal;
import java.util.Map;

import au.com.dius.shopping.Catalogue;
import au.com.dius.shopping.Price;
import au.com.dius.shopping.Product;
import au.com.dius.shopping.SKU;
import au.com.dius.shopping.ShoppingCart;

/**
 * Rule: The brand new Super iPad will have a bulk discounted applied, where the
 * price will drop to $499.99 each, if someone buys more than 4
 */
public class SuperIPadRule implements Rule {

	@Override
	public Price apply(Catalogue catalogue, ShoppingCart cart, Price total) {
		Map<Product, Integer> productCounts = cart.countProducts();

		Product ipad = catalogue.get(SKU.IPAD);

		Integer ipadCount = productCounts.get(ipad);
		if (ipadCount == null) {
			return total;
		}

		if (ipadCount > 4) {
			BigDecimal original = ipad.getPrice().getNativePrice();
			BigDecimal newPrice = new BigDecimal("499.99");
			BigDecimal diff = original.subtract(newPrice);
			BigDecimal changeAmount = diff.multiply(new BigDecimal(ipadCount)).negate();

			return total.change(changeAmount);
		} else {
			return total;
		}
	}
}
