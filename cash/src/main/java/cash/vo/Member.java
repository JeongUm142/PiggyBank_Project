package cash.vo;

public class Member {
	private String memberId;
	private String memberPw;
	private String updatedate;
	private String createdate;
	
	public Member() {
		super();
	}
	
	public Member(String memberId, String memberPw, String updatedate, String createdate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.updatedate = updatedate;
		this.createdate = createdate;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
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
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", updatedate=" + updatedate
				+ ", createdate=" + createdate + "]";
	}
}
