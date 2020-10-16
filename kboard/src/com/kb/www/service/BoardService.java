package com.kb.www.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kb.www.dao.BoardDAO;
import com.kb.www.vo.ArticleVO;
import com.kb.www.vo.MemberHistoryVO;
import com.kb.www.vo.MemberVO;

import static com.kb.www.common.JdbcUtil.*;
import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_JOIN;
import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_REJOIN;

public class BoardService {

	// 회원가입 기능
	public boolean joinMember(MemberVO memberVO, MemberHistoryVO memberHistoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		memberHistoryVO.setEvt_type(MEMBER_HISTORY_EVENT_JOIN);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		int count_01 = dao.insertMember(memberVO);
		memberHistoryVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		// auto increment인 mb_sq를 Memberhistory 테이블에 저장!
		int count_02 = dao.insertMemberHistory(memberHistoryVO);
		// 셋중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
		if (count_01 > 0 && count_02 > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	//탈퇴한 회원 회원가입 기능
	public boolean joinLeavedMember(MemberVO memberVO, MemberHistoryVO memberHistoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		memberHistoryVO.setEvt_type(MEMBER_HISTORY_EVENT_REJOIN);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		int count_01 = dao.insertLeavedMember(memberVO);
		memberHistoryVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		// auto increment인 mb_sq를 Memberhistory 테이블에 저장!
		int count_02 = dao.insertMemberHistory(memberHistoryVO);
		// 셋중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
		if (count_01 > 0 && count_02 > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}

	// 회원정보수정 기능
	public boolean updateMember(MemberVO memberVO, MemberHistoryVO memberHistoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		memberHistoryVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		int count_01 = dao.updateMember(memberVO);
		// auto increment인 mb_sq를 Memberhistory 테이블에 저장!
		int count_02 = dao.insertMemberHistory(memberHistoryVO);
		// 셋중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
		if (count_01 > 0 && count_02 > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}

	// 아이디 중복검사
	public int getMemberCount(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getMemberCount(id);
		if(count==0) {
			int count2=dao.getLeaveMemberCount(id);
			count2=count;
			return count2;
		}
		close(con);
		return count;
	}

	// 중복체크 내가한것 옛날버전임
//	public boolean isDuplicatedId(String id) {
//		BoardDAO dao = BoardDAO.getInstance();
//		Connection con = getConnection();
//		dao.setConnection(con);
//		boolean isDupId = dao.getMemberId(id);
//		if (isDupId == false) {
//			commit(con);
//		} else {
//			rollback(con);
//		}
//		close(con);
//		return isDupId;
//	}
//회원 아이디 가져오기
	public MemberVO getMember(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVO vo = dao.getMember(id);
		close(con);
		return vo;
	}
	//탈퇴한 회원 아이디 가져오기
	public MemberVO getLeaveMember(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVO vo = dao.getLeaveMember(id);
		close(con);
		return vo;
	}

//회원정보가져오기
	public MemberVO getMemberInfo(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		MemberVO vo = dao.getMemberInfo(id);
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
		memberVO.setLogin_st(true);
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
	public boolean logoutMember(MemberVO memberVO, MemberHistoryVO memberHistoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		memberVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		memberHistoryVO.setMb_sq(memberVO.getMb_sq());
		memberHistoryVO.setName(memberVO.getMb_name());
		memberHistoryVO.setGender(memberVO.getMb_gender());
		memberHistoryVO.setEmail(memberVO.getMb_email());
		int count_01 = dao.updateLoginState(memberVO);
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

	public int getMemberSequence(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		int sq = dao.getMemberSequence(id);
		close(con);
		return sq;
	}

	// 회원탈퇴 구현
	public boolean leaveMember(MemberVO memberVO, MemberHistoryVO memberHisoryVO) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		memberVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
		memberHisoryVO.setName(memberVO.getMb_name());
		memberHisoryVO.setEmail(memberVO.getMb_email());
		memberHisoryVO.setGender(memberVO.getMb_gender());
		memberHisoryVO.setMb_sq(memberVO.getMb_sq());
		int count_01 = dao.updateLeaveFlag(memberVO);
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

	// Member History
	public ArrayList<MemberHistoryVO> getMemberHistory(String id) {
		BoardDAO dao = BoardDAO.getInstance();
		// JdbcUtil의 getConnection을 이용해서 mysqldb와 연결
		Connection con = getConnection();
		// dao와 mysqldb의 데이터를 con을 이용해서 공유
		dao.setConnection(con);
		ArrayList<MemberHistoryVO> list = dao.getMemberHistory(id);
		close(con);
		return list;
	}

	// 글쓰기 기능
	public boolean insertArticle(ArticleVO vo) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		int count = dao.insertArticle(vo);
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
			return isSuccess;
		}
		close(con);
		return isSuccess;
	}

	// 글 수정 기능
	public boolean updateArticle(ArticleVO vo) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		int count = dao.updateArticle(vo);
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}

	// 글 삭제 기능
	public boolean deleteArticle(int num) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		boolean isSuccess = false;
		int count = dao.deleteArticle(num);
		if (count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
			return isSuccess;
		}
		close(con);
		return isSuccess;
	}

	// 글 작성자 글 번호를 이용해 가져오는 기능
	public String getWriterId(int num) {
		BoardDAO dao = BoardDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		// isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
		String id = dao.getWriterId(num);
		close(con);
		return id;
	}
}
