package com.kb.www.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kb.www.dao.BoardDAO;
import com.kb.www.vo.ArticleVO;
import com.kb.www.vo.MemberHistoryVO;
import com.kb.www.vo.MemberVO;

import static com.kb.www.common.JdbcUtil.*;

public class BoardService {

	// 회원가입 기능
	public boolean joinMember(MemberVO memberVO, MemberHistoryVO memberHistoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;

		int count_01 = dao.insertMember(memberVO);
		memberHistoryVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		// auto increment인 mb_sq를 Memberhistory 테이블에 저장!
		int count_02 = dao.insertMemberHistory(memberHistoryVO);
		// 둘중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
		if (count_01 > 0 && count_02 > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}

	public MemberVO getMember(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVO vo = dao.getMember(id);
		close(con);
		return vo;
	}

	// 로그인
	public boolean loginMember(MemberVO memberVO, MemberHistoryVO memberHisoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		int count_01 = dao.updateLoginState(memberVO);
		memberHisoryVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		// auto increment인 mb_sq를 Memberhistory 테이블에 저장!
		int count_02 = dao.insertMemberHistory(memberHisoryVO);
		// 둘중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
		if (count_01 > 0 && count_02 > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}

	// 로그아웃
	public boolean logoutMember(MemberVO memberVO, MemberHistoryVO memberHisoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		memberVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		memberHisoryVO.setMb_sq(memberVO.getMb_sq());
		int count_01 = dao.updateLoginState(memberVO);
		// auto increment인 mb_sq를 Memberhistory 테이블에 저장!
		int count_02 = dao.insertMemberHistory(memberHisoryVO);
		// 둘중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
		if (count_01 > 0 && count_02 > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}

	public int getMemberSequence(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		int sq = dao.getMemberSequence(id);
		close(con);
		return sq;
	}

//게시글 목록 구현
	public ArrayList<ArticleVO> getArticleList() {
		// TODO Auto-generated method stub
		BoardDAO dao = BoardDAO.getInstance();
		// JdbcUtil의 getConnetcion을 이용하여 MysqlDB와 연결
		Connection con = getConnection();
		// dao와 mysqldb의 데이터를 con을 사용하여 공유
		dao.setConnection(con);
		ArrayList<ArticleVO> list = dao.getArticleList();
		close(con);
		return list;
	}

//글 내용보기 기능
	public ArticleVO getArticleDetail(int num) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		// dao와 mysqldb의 데이터를 con을 사용하여 공유
		dao.setConnection(con);
		ArticleVO article = null;
		// 조회수 증가 기능
		int count = dao.updateHitCount(num);
		if (count > 0) {
			commit(con);
			article = dao.getArticleDetail(num);
		} else {
			rollback(con);
		}
		close(con);
		return article;
	}
}
