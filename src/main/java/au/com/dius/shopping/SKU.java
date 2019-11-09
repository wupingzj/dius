package au.com.dius.shopping;

public enum SKU {
	IPAD("ipd"), MACBOOK("mbp"), APPLETV("atv"), VGAADAPTOR("VGA");

	private String sku;

	SKU(String sku) {
		this.sku = sku;
	}

	public String getString() {
		return sku;
	}
}
