package au.com.dius.shopping.ruleengine.rule;

import org.junit.Before;

import au.com.dius.shopping.Catalogue;
import au.com.dius.shopping.Price;
import au.com.dius.shopping.Product;
import au.com.dius.shopping.SKU;

public abstract class RuleTest {
	Catalogue catalogue = new Catalogue();
	Product appleTV = new Product(SKU.APPLETV, "Apple TV", new Price("109.50"));
	Product macbook = new Product(SKU.MACBOOK, "MacBook Pro", new Price("1399.99"));
	Product ipad = new Product(SKU.IPAD, "Super iPad", new Price("549.99"));
	Product vga = new Product(SKU.VGAADAPTOR, "VGA adapter", new Price("30.00"));

	@Before
	public void setUp() throws Exception {
		// init catalogue
		catalogue.add(ipad);
		catalogue.add(macbook);
		catalogue.add(appleTV);
		catalogue.add(vga);
	}

}
