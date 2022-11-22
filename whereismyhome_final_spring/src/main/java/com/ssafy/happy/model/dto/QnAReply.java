package com.ssafy.happy.model.dto;

public class QnAReply {
	private String replycontent;
	private String replydate;
	private String userid;
	
	public QnAReply() {
	}

	public QnAReply(String replycontent, String replydate, String userid) {
		super();
		this.replycontent = replycontent;
		this.replydate = replydate;
		this.userid = userid;
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

	@Override
	public String toString() {
		return "QnAReply [replycontent=" + replycontent + ", replydate=" + replydate + ", userid=" + userid + "]";
	}
	
}
