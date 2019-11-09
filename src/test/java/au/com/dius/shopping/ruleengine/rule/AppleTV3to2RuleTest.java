package au.com.dius.shopping.ruleengine.rule;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import au.com.dius.shopping.Price;
import au.com.dius.shopping.Product;
import au.com.dius.shopping.SKU;
import au.com.dius.shopping.ShoppingCart;
import au.com.dius.shopping.ShoppingCartImpl;

public class AppleTV3to2RuleTest extends RuleTest {

	private Rule rule = new AppleTV3to2Rule();

	// Test basic flow. The rule should not impact total price.
	@Test
	public void testBasicFlow() {
		Price total = new Price("5678.21");

		ShoppingCart cart = new ShoppingCartImpl();

		Product p1 = new Product(SKU.IPD, "Super iPad", new Price("649.99"));
		cart.addProduct(p1);
		rule.apply(catalogue, cart, total);

		assertEquals(new BigDecimal("5678.21"), total.getNativePrice());
	}

	@Test
	public void test3to2promotion() {
		ShoppingCart cart = new ShoppingCartImpl();

		// setup test data
		for (int i = 0; i <= 2; i++) {
			// add 3 appleTVs
			cart.addProduct(appleTV);
		}
		Price total = cart.getTotal();
		Price appleTVprice = this.appleTV.getPrice();

		// apply rule
		Price newTotal = rule.apply(catalogue, cart, total);

		// total price should be 2* appleTV price
		BigDecimal expected = appleTVprice.getNativePrice().multiply(new BigDecimal(2));
		assertEquals(expected, newTotal);
	}
}
