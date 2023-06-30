package cash.vo;

import java.util.Objects;

public class Cashbook {
	private int cashbookNo;
	private String category;
	private String cashbookDate;
	private int price;
	private String updatedate;
	private String createdate;
	
	public Cashbook() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cashbook(int cashbookNo, String category, String cashbookDate, int price, String updatedate,
			String createdate) {
		super();
		this.cashbookNo = cashbookNo;
		this.category = category;
		this.cashbookDate = cashbookDate;
		this.price = price;
		this.updatedate = updatedate;
		this.createdate = createdate;
	}

	public int getCashbookNo() {
		return cashbookNo;
	}
	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCashbookDate() {
		return cashbookDate;
	}
	public void setCashbookDate(String cashbookDate) {
		this.cashbookDate = cashbookDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	@Override
	public String toString() {
		return "Cashbook [cashbookNo=" + cashbookNo + ", category=" + category + ", cashbookDate=" + cashbookDate
				+ ", price=" + price + ", updatedate=" + updatedate + ", createdate=" + createdate + "]";
	}
	
}
