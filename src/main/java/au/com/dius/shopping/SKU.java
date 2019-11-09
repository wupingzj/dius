package au.com.dius.shopping;

public enum SKU {
	IPD("ipd"), MBP("mbp"), ATV("atv"), VGA("VGA");

	private String sku;

	SKU(String sku) {
		this.sku = sku;
	}

	public String getString() {
		return sku;
	}
}
