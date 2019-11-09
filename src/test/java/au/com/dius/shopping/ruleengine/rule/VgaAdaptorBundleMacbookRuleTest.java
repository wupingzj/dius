package au.com.dius.shopping.ruleengine.rule;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;

import au.com.dius.shopping.Price;
import au.com.dius.shopping.Product;
import au.com.dius.shopping.SKU;
import au.com.dius.shopping.ShoppingCart;
import au.com.dius.shopping.ShoppingCartImpl;

public class VgaAdaptorBundleMacbookRuleTest extends RuleTest {

	private Rule rule = new VgaAdaptorBundleMacbookRule();

	// Test basic flow. The rule should not impact total price.
	@Test
	public void testBasicFlow() {
		Price total = new Price("30.00");

		ShoppingCart cart = new ShoppingCartImpl();

		Product p1 = new Product(SKU.VGA, "VGA adapter", new Price("30.00"));
		cart.addProduct(p1);
		rule.apply(catalogue, cart, total);

		assertEquals(new BigDecimal("30.00"), total.getNativePrice());
	}

	/**
	 * 3 Macbook and 5 VGA are purchased, only 2 VGA will be charged.
	 */
	@Test
	public void test3Mac5VGA() {
		ShoppingCart cart = new ShoppingCartImpl();

		// setup test data
		for (int i = 0; i < 3; i++) {
			cart.addProduct(macbook);
		}

		for (int i = 0; i < 5; i++) {
			cart.addProduct(vga);
		}

		Price total = cart.getTotal();
		BigDecimal totalAmount = total.getNativePrice();
		BigDecimal VGAPriceAmount = this.vga.getPrice().getNativePrice();

		// apply rule
		Price newTotal = rule.apply(catalogue, cart, total);

		// 3 VGA should be free
		BigDecimal freeAmount = VGAPriceAmount.multiply(new BigDecimal(3));
		BigDecimal expected = totalAmount.subtract(freeAmount);
		assertEquals(expected, newTotal.getNativePrice());
	}

	/**
	 * 5 Macbook and 2 VGA are purchased, no VGA will be charged and also, two VGA
	 * adaptor will be added to cart.
	 */
	@Test
	public void test5Mac2VGA() {
		ShoppingCart cart = new ShoppingCartImpl();

		// setup test data
		for (int i = 0; i < 5; i++) {
			cart.addProduct(macbook);
		}

		for (int i = 0; i < 2; i++) {
			cart.addProduct(vga);
		}

		Price total = cart.getTotal();
		BigDecimal totalAmount = total.getNativePrice();
		BigDecimal VGAPriceAmount = this.vga.getPrice().getNativePrice();

		// apply rule
		Price newTotal = rule.apply(catalogue, cart, total);

		// 2 VGA should be free
		BigDecimal freeAmount = VGAPriceAmount.multiply(new BigDecimal(2));
		BigDecimal expected = totalAmount.subtract(freeAmount);
		assertEquals(expected, newTotal.getNativePrice());

		// assert the 5 VGA adaptors in cart
		Map<Product, Integer> productCounts = cart.countProducts();
		Integer macbookCount = productCounts.get(macbook);
		Integer VGACount = productCounts.get(vga);
		assertEquals(5, macbookCount.intValue());
		assertEquals("There shuld be 5 VGA adaptors as two are bundled in for free", 5, VGACount.intValue());

	}
}
