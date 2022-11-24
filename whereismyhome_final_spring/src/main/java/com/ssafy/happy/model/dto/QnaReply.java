package com.ssafy.happy.model.dto;

public class QnaReply {
	private int qnareplyno;
	private String replycontent;
	private String replydate;
	private String userid;
	private int qnano;
	
	public QnaReply() {
	}

	public QnaReply(int qnareplyno, String replycontent, String replydate, String userid, int qnano) {
		super();
		this.qnareplyno = qnareplyno;
		this.replycontent = replycontent;
		this.replydate = replydate;
		this.userid = userid;
		this.qnano = qnano;
	}

	public int getQnareplyno() {
		return qnareplyno;
	}

	public void setQnareplyno(int qnareplyno) {
		this.qnareplyno = qnareplyno;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public String getReplydate() {
		return replydate;
	}

	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getQnano() {
		return qnano;
	}

	public void setQnano(int qnano) {
		this.qnano = qnano;
	}

	@Override
	public String toString() {
		return "QnaReply [qnareplyno=" + qnareplyno + ", replycontent=" + replycontent + ", replydate=" + replydate
				+ ", userid=" + userid + ", qnano=" + qnano + "]";
	}

	
}
