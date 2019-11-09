package au.com.dius.shopping.ruleengine.rule;

import au.com.dius.shopping.Catalogue;
import au.com.dius.shopping.Price;
import au.com.dius.shopping.ShoppingCart;

public interface Rule {
	public Price apply(Catalogue catalogue, ShoppingCart cart, Price total);
}
