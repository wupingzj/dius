package au.com.dius.shopping;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PriceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculation() {
		Price price = new Price(BigDecimal.TEN);
		
		price.change(BigDecimal.ONE);
		assertEquals(new BigDecimal("11.00"),  price.getNativePrice());
		
		Price price2 = new Price(BigDecimal.TEN);
		price2.change(new BigDecimal("-2"));
		assertEquals(new BigDecimal("8.00"),  price2.getNativePrice());
	}
}
