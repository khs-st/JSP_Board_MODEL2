package com.kb.www.vo;

public class MemberVO {
	private int mb_sq;
	private String mb_id;
	private String mb_pw;
	private boolean login_st;
	private boolean leave_fl;

	public int getMb_sq() {
		return mb_sq;
	}

	public void setMb_sq(int mb_sq) {
		this.mb_sq = mb_sq;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getMb_pw() {
		return mb_pw;
	}

	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}

	public boolean isLogin_st() {
		return login_st;
	}

	public void setLogin_st(boolean login_st) {
		this.login_st = login_st;
	}

	public boolean isLeave_fl() {
		return leave_fl;
	}

	public void setLeave_fl(boolean leave_fl) {
		this.leave_fl = leave_fl;
	}
}
