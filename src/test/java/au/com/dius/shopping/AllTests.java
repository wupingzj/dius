package au.com.dius.shopping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import au.com.dius.shopping.ruleengine.rule.AppleTV3to2RuleTest;
import au.com.dius.shopping.ruleengine.rule.SuperIPadRuleTest;
import au.com.dius.shopping.ruleengine.rule.VgaAdaptorBundleMacbookRuleTest;

@RunWith(Suite.class)
@SuiteClasses({ CheckoutTests.class, PriceTest.class, AppleTV3to2RuleTest.class, SuperIPadRuleTest.class,
		VgaAdaptorBundleMacbookRuleTest.class })
public class AllTests {

}
