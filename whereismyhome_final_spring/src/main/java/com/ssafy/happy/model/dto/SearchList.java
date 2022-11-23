package com.ssafy.happy.model.dto;

public class SearchList {
//	addr.sidoName, addr.gugunName, addr.dongCode, addr.dongName, addr.totalName
	private String sidoName;
	private String gugunName;
	private String dongCode;
	private String dongName;
	private String totalName;
	
	public SearchList() {}

	public SearchList(String sidoName, String gugunName, String dongCode, String dongName, String totalName) {
		super();
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.totalName = totalName;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getTotalName() {
		return totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}
}
