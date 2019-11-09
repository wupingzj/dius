package au.com.dius.shopping.ruleengine.rule;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import au.com.dius.shopping.Price;
import au.com.dius.shopping.Product;
import au.com.dius.shopping.SKU;
import au.com.dius.shopping.ShoppingCart;
import au.com.dius.shopping.ShoppingCartImpl;

public class SuperIPadRuleTest extends RuleTest {
	private Rule rule = new SuperIPadRule();

	// Test basic flow. The rule should not impact total price.
	// Original 549.99 is charged for a single iPad being purchased.
	@Test
	public void testBasicFlow() {
		Price total = new Price("549.99");

		ShoppingCart cart = new ShoppingCartImpl();

		Product p1 = new Product(SKU.IPAD, "Super iPad", new Price("549.99"));
		cart.addProduct(p1);
		rule.apply(catalogue, cart, total);

		assertEquals(p1.getPrice().getNativePrice(), total.getNativePrice());
	}
	
	// Price should drop to $499.99 if 5 or more iPads are purchased
	// 6 iPads will at $499.99 each with total of $2,999.94
	@Test
	public void testBulkDiscount() {
		ShoppingCart cart = new ShoppingCartImpl();

		int numberOfIpads = 6;
		
		// setup test data
		for (int i = 0; i < numberOfIpads; i++) {
			cart.addProduct(ipad);
		}
		Price total = cart.getTotal();

		// apply rule
		Price newTotal = rule.apply(catalogue, cart, total);

		BigDecimal expected = new BigDecimal("499.99").multiply(new BigDecimal(numberOfIpads));
		assertEquals(expected, newTotal.getNativePrice());
	}
}
