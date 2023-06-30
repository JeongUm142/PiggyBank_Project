package cash.vo;

import java.util.Objects;

public class Hastag {
	private int cashbookNo;
	private String word;
	private String updatedate;
	private String createdate;
	
	public Hastag() {
		super(); // 부모
	}
	
	public Hastag(int cashbookNo, String word, String updatedate, String createdate) {
		super();
		this.cashbookNo = cashbookNo;
		this.word = word;
		this.updatedate = updatedate;
		this.createdate = createdate;
	}

	public int getCashbookNo() {
		return cashbookNo;
	}
	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
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
		return "Hastag [cashbookNo=" + cashbookNo + ", word=" + word + ", updatedate=" + updatedate + ", createdate="
				+ createdate + "]";
	}
	
}
