package com.ssafy.happy.model.dto;

public class Qna {
	private int qnano;
	private String qnasubject;
	private String qnacontent;
	private String regtime;
	private int hit;
	private String userid;
	
	public Qna() {
	}

	public Qna(int qnano, String qnasubject, String qnacontent, String regtime, int hit, String userid) {
		super();
		this.qnano = qnano;
		this.qnasubject = qnasubject;
		this.qnacontent = qnacontent;
		this.regtime = regtime;
		this.hit = hit;
		this.userid = userid;
	}

	public int getQnano() {
		return qnano;
	}

	public void setQnano(int qnano) {
		this.qnano = qnano;
	}

	public String getQnasubject() {
		return qnasubject;
	}

	public void setQnasubject(String qnasubject) {
		this.qnasubject = qnasubject;
	}

	public String getQnacontent() {
		return qnacontent;
	}

	public void setQnacontent(String qnacontent) {
		this.qnacontent = qnacontent;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Qna [qnano=" + qnano + ", qnasubject=" + qnasubject + ", qnacontent=" + qnacontent + ", regtime="
				+ regtime + ", hit=" + hit + ", userid=" + userid + "]";
	}

}
