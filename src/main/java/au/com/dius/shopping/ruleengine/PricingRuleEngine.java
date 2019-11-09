package au.com.dius.shopping.ruleengine;

/**
 * The rule engine provisions pricing rules. This design aims to provide
 * flexibility in case that pricing rules changes or evolves.
 * 
 * This engine is singleton. In spring framework environment, the bean is
 * singleton by default and the pricing rule can be configured/changed easily
 * with more sophisticated implementation as per business requirements and
 * initiatives.
 * 
 * @author ping
 */
public class PricingRuleEngine {
	private static PricingRuleEngine instance = new PricingRuleEngine();

	/**
	 * The first version of PricingRule.
	 */
	private static PricingRule rules = new PricingRuleImpl();

	private PricingRuleEngine() {
	}

	public static PricingRuleEngine getInstance() {
		return instance;
	}

	public static PricingRule getPricingRule() {
		return rules;
	}
}
