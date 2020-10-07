package com.kb.www.common.regexp;

import java.util.regex.Pattern;

public class RegExp {
	//정규 표현식 검사
	public static final int PAGE_NUM=0;
	public static final int ARTICLE_SUBJECT=1;
	public static final int ARTICLE_CONTENT=2;
	public static final int MEMBER_ID=3;
	public static final int MEMBER_PW=4;
	public static final int MEMBER_EMAIL=5;
	public static final int MEMBER_NAME=6;
	
	
	public static final String EXP_PAGE_NUM="[0-9]*$";
	public static final String EXP_ARTICLE_SUBJECT="^.{1,100}$";
	public static final String EXP_ARTICLE_CONTENT="^.{1,65535}$";
	public static final String EXP_MEMBER_ID="^[a-z0-9]{4,20}$";
	public static final String EXP_MEMBER_PW="^.{4,30}$";
	public static final String EXP_MEMBER_EMAIL="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	public static final String EXP_MEMBER_NAME="^[가-힣]{2,4}|[a-zA-Z]{2,10}$";
	
	public static boolean checkString(int type, String data) {
		boolean result=false;
		switch(type) {
		case PAGE_NUM:
			result=Pattern.matches(EXP_PAGE_NUM, data);
			break;
		case ARTICLE_SUBJECT:
			result=Pattern.matches(EXP_ARTICLE_SUBJECT, data);
			break;
		case ARTICLE_CONTENT:
			result=Pattern.matches(EXP_ARTICLE_CONTENT, data);
			break;
		case MEMBER_ID:
			result=Pattern.matches(EXP_MEMBER_ID, data);
			break;
		case MEMBER_PW:
			result=Pattern.matches(EXP_MEMBER_PW, data);
			break;
		case MEMBER_EMAIL:
			result=Pattern.matches(EXP_MEMBER_EMAIL, data);
			break;
		case MEMBER_NAME:
			result=Pattern.matches(EXP_MEMBER_NAME, data);
			break;
		}
		return result;
	}
	
}
