package au.com.dius.shopping;

public class Product {
	private final String sku;
	private final String name;
	private final Price price;

	public Product(String sku, String name, Price price) {
		this.sku = sku;
		this.name = name;
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	/**
	 * Gets price with currency
	 * 
	 * @return
	 */
	public Price getPrice() {
		return price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [sku=");
		builder.append(sku);
		builder.append(", name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Product other = (Product) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
}
