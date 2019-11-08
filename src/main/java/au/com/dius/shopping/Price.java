package au.com.dius.shopping;

import java.math.BigDecimal;

public class Price {
	private static int scale = 2;
	// Price can be in different currencies like AUD, USD etc and with currency
	// exchange rates.
	// This business requirement is ignored in this assessment test.
	private String currency;

	private BigDecimal price;

	public Price(BigDecimal price) {
		this.price = price.setScale(scale);
	}

	public Price(String price) {
		if (price == null) {
			// log it and throw appropriate exception
			throw new IllegalArgumentException("price cannot be null.");
		}
		this.price = new BigDecimal(price);
	}

	/**
	 * Gets price without consideration of currency
	 * 
	 * @return
	 */
	public BigDecimal getNativePrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Price change(BigDecimal change) {
		this.price = this.price.add(change).setScale(scale);
		return this;
	}

	public void multiply(int i) {
		setPrice(this.price.multiply(new BigDecimal(i)));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Price [price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
}
