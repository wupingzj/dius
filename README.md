# DiUS coding testing

This project implements the computer store business requirement.

The functionality is implemented in TDD approach.

The following test cases are implemented:

# Table of contents:

- AllTests
	- the test suite that runs all test cases
- CheckoutTests
	- the test case for checkout
- PriceTest
	- the unit test case for Price POJO
- AppleTV3to2RuleTest
	- the unit test case for the rule of 3 for 2 deal on Apple TVs
- SuperIPadRuleTest
	- the unit test case for the rule of super ipad that price drops to $499..99 each when more than 4 ipads are purchased
- VgaAdaptorBundleMacbookRuleTest
	- the unit test case for the rule of the free bundled VGA adaptor with every Macbook Pro being purchased
		
		

# Run the test suite
- Run AllTests


# Design
The implementation is taking the flexibility into consideration. This is achieved by the introduction of the rule engine [PricingRuleImpl](https://github.com/wupingzj/dius/blob/master/src/main/java/au/com/dius/shopping/ruleengine/PricingRuleImpl.java)

To add a new rule, 
- simply create a rule class that implements the [Rule](https://github.com/wupingzj/dius/blob/master/src/main/java/au/com/dius/shopping/ruleengine/rule/Rule.java) interface with a corresponding test case.
For example,  [SuperIPadRule](https://github.com/wupingzj/dius/blob/master/src/main/java/au/com/dius/shopping/ruleengine/rule/SuperIPadRule.java). 

- register the new rule to PricingRuleImpl by something like
```
	public PricingRuleImpl() {
		Rule rule1 = new AppleTV3to2Rule();
		rules.add(rule1);

		Rule rule2 = new SuperIPadRule();
		rules.add(rule2);

		Rule rule3 = new VgaAdaptorBundleMacbookRule();
		rules.add(rule3);
	}
```


Thanks for reviewing the implementation
