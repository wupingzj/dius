package au.com.dius.shopping.ruleengine;

import java.util.LinkedList;
import java.util.List;

import au.com.dius.shopping.Catalogue;
import au.com.dius.shopping.Price;
import au.com.dius.shopping.ShoppingCart;
import au.com.dius.shopping.ruleengine.rule.AppleTV3to2Rule;
import au.com.dius.shopping.ruleengine.rule.Rule;
import au.com.dius.shopping.ruleengine.rule.SuperIPadRule;
import au.com.dius.shopping.ruleengine.rule.VgaAdaptorBundleMacbookRule;

public class PricingRuleImpl implements PricingRule {	
	List<Rule> rules = new LinkedList<Rule>();

	public PricingRuleImpl() {
		Rule rule1 = new AppleTV3to2Rule();
		rules.add(rule1);

		Rule rule2 = new SuperIPadRule();
		rules.add(rule2);

		Rule rule3 = new VgaAdaptorBundleMacbookRule();
		rules.add(rule3);
	}

	public Price apply(final Catalogue catalogue, ShoppingCart cart) {
		final Price total = new Price("0.00");
		
		// calculate total
		cart.getProducts().forEach( p-> {
			total.change(p.getPrice().getNativePrice());
		});
		
		// apply rules
		this.rules.forEach( rule -> {
			rule.apply(catalogue, cart, total);			
		});

		return total;
	}

}
