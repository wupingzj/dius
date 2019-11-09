package au.com.dius.shopping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.shopping.ruleengine.PricingRule;
import au.com.dius.shopping.ruleengine.PricingRuleEngine;

public class CheckoutTests {
	private ProductStore ps;
	private Checkout co;

	private Product ipad = new Product(SKU.IPAD, "Super iPad", new Price("549.99"));
	private Product macbook = new Product(SKU.MACBOOK, "MacBook Pro", new Price("1399.99"));
	private Product appleTV = new Product(SKU.APPLETV, "Apple TV", new Price("109.50"));
	private Product vgaAdaptor = new Product(SKU.VGAADAPTOR, "VGA adapter", new Price("30"));

	@Before
	public void setUp() throws Exception {
		// initialize product store
		ps = new ProductStoreImpl();
		ps.addToCatalogue(ipad);
		ps.addToCatalogue(macbook);
		ps.addToCatalogue(appleTV);
		ps.addToCatalogue(vgaAdaptor);
		
		// init pricing rule
		PricingRule pr = PricingRuleEngine.getInstance().getPricingRule();
		co = new CheckoutImpl(pr, ps); // init with rules, products
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Scenario: SKUs scan: appletv, appletv, appletv Expected: charge for two
	 * tv only. Total $219
	 */
	@Test
	public void ThreeAppleTV_chargeTwoOnly() {
		co.scan(SKU.APPLETV);
		co.scan(SKU.APPLETV);
		co.scan(SKU.APPLETV);

		Price total = co.total();
		assertEquals("The price of 3 apple tv for 2 should be $219", new BigDecimal("219.00"), total.getNativePrice());
	}

	/**
	 * Scenario: SKUs scan: appletv, appletv, appletv, vga Expected: charge for two
	 * tv only. Total $249
	 */
	@Test
	public void ThreeAppleTV_OneVGA_chargeTwoOnly() {
		co.scan(SKU.APPLETV);
		co.scan(SKU.APPLETV);
		co.scan(SKU.APPLETV);
		co.scan(SKU.VGAADAPTOR);

		Price total = co.total();
		assertEquals("The price of 3 apple tv for 2 and one vga should be $249", new BigDecimal("249.00"), total.getNativePrice());
	}

	/**
	 * Rule: SKUs scan: ipad buld discount buy 4 or more ipad, price drop to $499.99
	 * 
	 * Scenario: 2 apple tvs, 5 ipads, should charge
	 */
	@Test
	public void fourIpads_discountedPrice() {
		co.scan(SKU.APPLETV);
		co.scan(SKU.IPAD);
		co.scan(SKU.IPAD);
		co.scan(SKU.APPLETV);
		co.scan(SKU.IPAD);
		co.scan(SKU.IPAD);
		co.scan(SKU.IPAD);

		Price total = co.total();

		BigDecimal expected = new BigDecimal("2718.95");
		String msg = String.format("The price of 2 apple tvs and 5 ipads should be %s", new BigDecimal("2718.95"));
		assertEquals(msg, expected, total.getNativePrice());
	}

	/**
	 * Scenario: bundle a free VGA adaptor free of charge with every Macbook Pro
	 * sold
	 * Scenario: 1 macbook, 1 vga adaptor and 1 ipad, should charge $1949.98
	 */
	@Test
	public void buyMac_GetFreeVGA() {
		co.scan(SKU.MACBOOK);
		co.scan(SKU.VGAADAPTOR);
		co.scan(SKU.IPAD);

		Price total = co.total();
		assertEquals(new BigDecimal("1949.98"), total.getNativePrice());
	}
}
