package au.com.dius.shopping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShoppingCartImpl implements ShoppingCart {
	private List<Product> products = new LinkedList<Product>();
	private Map<Product, Integer> counts = null;
	private boolean alreadyCounted = false;

	@Override
	public void addProduct(Product product) {
		this.products.add(product);
		this.alreadyCounted = false;
	}

	@Override
	public List<Product> getProducts() {
		return this.products;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cart:[ ");
		products.forEach(p -> {

			sb.append(p.toString());
		});
		sb.append("]");

		return sb.toString();
	}

	/**
	 * Counts products in the cart.
	 * 
	 * Note: this method is not thread-safe.
	 * 
	 * @param cart
	 * @return the product occurrence map
	 */
	public Map<Product, Integer> countProducts() {
		if (this.counts == null || !this.alreadyCounted) {
			// first count or re-count if cart changed
			counts = new HashMap<Product, Integer>();

			this.getProducts().forEach( p -> {
				Integer value = counts.get(p);
				if (value == null) {
					counts.put(p, 1);
				} else {
					counts.put(p, ++value);
				}
			});

		}

		this.alreadyCounted = true;
		
		return counts;
	}

	@Override
	public Price getTotal() {
		BigDecimal total = new BigDecimal("0.00");

		for (Product p : this.getProducts()) {
			total = total.add(p.getPrice().getNativePrice());
		}

		return new Price(total);
	}
}
