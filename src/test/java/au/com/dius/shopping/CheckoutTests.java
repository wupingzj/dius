package au.com.dius.shopping;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckoutTests {
	private ProductStore ps;
	private Checkout co;

	private Product ipad = new Product(SKU.IPD, "Super iPad", new Price("549.99"));
	private Product macbook = new Product(SKU.MBP, "MacBook Pro", new Price("1399.99"));
	private Product appleTV = new Product(SKU.ATV, "Apple TV", new Price("109.50"));
	private Product vgaAdaptor = new Product(SKU.VGA, "VGA adapter", new Price("30"));

	@Before
	public void setUp() throws Exception {
		// initialize product store
		ps = new ProductStoreImpl();
		ps.addToCatalogue(ipad);
		ps.addToCatalogue(macbook);
		ps.addToCatalogue(appleTV);
		ps.addToCatalogue(vgaAdaptor);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Scenario: SKUs scan: appletv, appletv, appletv, vga Expected: charge for two
	 * tv only. Total $249
	 */
	@Test
	public void ThreeAppleTV_chargeTwoOnly() {
		co.scan(SKU.ATV);
		co.scan(SKU.ATV);
		co.scan(SKU.ATV);

		Price total = co.total();
		assertEquals("The price of 3 apple tv for 2 should be $219", new BigDecimal("219"), total.getNativePrice());

		BigDecimal expected = appleTV.getPrice().getNativePrice().multiply(new BigDecimal("2"));
		expected = expected.add(vgaAdaptor.getPrice().getNativePrice());
		String msg = String.format("The price of 3 apple tv and 1 vga should be %d", expected.toString());

		assertEquals(msg, expected, total.getNativePrice());
		msg = String.format("The price of 3 apple tv and 1 vga should be %d", new BigDecimal("249.00"));
		assertEquals(msg, expected, total.getNativePrice());
	}

	/**
	 * Rule: SKUs scan: ipad buld discount buy 4 or more ipad, price drop to $499.99
	 * 
	 * Scenario: 2 apple tvs, 5 ipads, should charge
	 */
	@Test
	public void fourIpads_discountedPrice() {
		co.scan(SKU.ATV);
		co.scan(SKU.IPD);
		co.scan(SKU.IPD);
		co.scan(SKU.ATV);
		co.scan(SKU.IPD);
		co.scan(SKU.IPD);
		co.scan(SKU.IPD);

		Price total = co.total();

		BigDecimal tvCost = appleTV.getPrice().getNativePrice().multiply(new BigDecimal("2"));
		BigDecimal ipadCost = new BigDecimal(499.99).multiply(new BigDecimal("5"));
		BigDecimal expected = tvCost.add(ipadCost);
		String msg = String.format("The price of 2 apple tvs and 5 ipads should be %d", expected.toString());
		assertEquals(msg, expected, total.getNativePrice());

		expected = new BigDecimal("2718.95");
		msg = String.format("The price of 2 apple tvs and 5 ipads should be %d", new BigDecimal("2718.95"));
		assertEquals(msg, expected, total.getNativePrice());
	}

	/**
	 * Scenario: bundle a free VGA adaptor free of charge with every Macbook Pro
	 * sold
	 */
	@Test
	public void buyMac_GetFreeHDMI() {
		co.scan(SKU.MBP);
		co.scan(SKU.VGA);
		co.scan(SKU.IPD);

		Price total = co.total();
		assertEquals(new BigDecimal("1949.98"), total.getNativePrice());
	}
}
