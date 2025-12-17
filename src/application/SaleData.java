package application;

public class SaleData {
	private int id;
	private double price;
	private int amount;
	private String productName;
	private String saleDate;
	
	public SaleData(int id, double price, int amount, String productName, String saleDate) {
		super();
		this.id = id;
		this.price = price;
		this.amount = amount;
		this.productName = productName;
		this.saleDate = saleDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	
	
	

}
