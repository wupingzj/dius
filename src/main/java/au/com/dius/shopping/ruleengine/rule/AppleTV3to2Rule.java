package au.com.dius.shopping.ruleengine.rule;

import java.math.BigDecimal;
import java.util.Map;

import au.com.dius.shopping.Catalogue;
import au.com.dius.shopping.Price;
import au.com.dius.shopping.Product;
import au.com.dius.shopping.SKU;
import au.com.dius.shopping.ShoppingCart;

public class AppleTV3to2Rule implements Rule {

	@Override
	public Price apply(Catalogue catalogue, ShoppingCart cart, Price total) {
		Map<Product, Integer> productCounts = cart.countProducts();
		Product appleTV = catalogue.get(SKU.ATV);

		Integer appleTVcount = productCounts.get(appleTV);
		if (appleTVcount == null) {
			return total;
		}

		if (appleTVcount >= 2) {
			// for every 3 purchases of appleTV, reduce price of one
			int promoteCount = appleTVcount / 3;

			BigDecimal changeAmount = appleTV.getPrice().getNativePrice().multiply(new BigDecimal(promoteCount))
					.negate();

			return total.change(changeAmount);
		} else {
			return total;
		}
	}
}
