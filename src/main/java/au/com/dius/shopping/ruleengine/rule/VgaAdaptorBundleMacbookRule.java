package au.com.dius.shopping.ruleengine.rule;

import java.math.BigDecimal;
import java.util.Map;

import au.com.dius.shopping.Catalogue;
import au.com.dius.shopping.Price;
import au.com.dius.shopping.Product;
import au.com.dius.shopping.SKU;
import au.com.dius.shopping.ShoppingCart;

public class VgaAdaptorBundleMacbookRule implements Rule {

	@Override
	public Price apply(Catalogue catalogue, ShoppingCart cart, Price total) {
		Map<Product, Integer> productCounts = cart.countProducts();

		Product macbook = catalogue.get(SKU.MBP);
		Product vga = catalogue.get(SKU.VGA);

		Integer macbookCount = productCounts.get(macbook);
		Integer vgaCount = productCounts.get(vga);
		if (macbookCount == null || vgaCount == null) {
			return total;
		}
		
		if (macbookCount == 0 || vgaCount == 0) {
			return total;
		}

		// apply rule
		BigDecimal vgaPrice = vga.getPrice().getNativePrice();
		BigDecimal freeAmount;
		if (macbookCount > vgaCount) {
			freeAmount = vgaPrice.multiply(new BigDecimal(vgaCount)).negate();
			
			// bundle free vga adaptors
			for (int i=0; i<(macbookCount - vgaCount); i++) {
				cart.addProduct(vga);	
			}
		} else {
			freeAmount = vgaPrice.multiply(new BigDecimal(macbookCount)).negate();
		}
		
		return total.change(freeAmount);
	}
}
