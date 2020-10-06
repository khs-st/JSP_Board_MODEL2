package com.kb.www.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.kb.www.vo.ArticleVO;
import com.kb.www.vo.MemberHistoryVO;
import com.kb.www.vo.MemberVO;
import com.mysql.cj.protocol.Resultset;

import static com.kb.www.common.JdbcUtil.close;
import static com.kb.www.common.JdbcUtil.commit;

public class BoardDAO {
	private Connection con;

	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final BoardDAO INSTANCE = new BoardDAO();
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

//게시글 목록 보기 기능
	public ArrayList<ArticleVO> getArticleList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
		try {
			pstmt = con.prepareStatement("select * from kboard.kboard");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setArticleNum(rs.getInt("num"));
				vo.setArticleTitle(rs.getString("subject"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

//게시물 내용 보기 기능
	public ArticleVO getArticleDetail(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO vo2 = null;
		try {
			pstmt = con.prepareStatement("select * from kboard where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo2 = new ArticleVO();
				vo2.setArticleNum(rs.getInt("num"));
				vo2.setArticleTitle(rs.getString("subject"));
				vo2.setArticleContent(rs.getString("content"));
				vo2.setHit(rs.getInt("hit"));
				vo2.setWriteDate("wdate");
				vo2.setWriteDate(rs.getString("udate"));
				vo2.setId(rs.getString("mb_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo2;
	}

	// 조회수 증가 기능
	public int updateHitCount(int num) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update kboard set hit=hit+1 where num=?");
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 사용자 회원가입 및 로그인
	public int insertMember(MemberVO memberVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into member(mb_id, mb_pw) values(?,?)");
			pstmt.setString(1, memberVO.getMb_id());
			pstmt.setString(2, memberVO.getMb_pw());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int getMemberSequence(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mb_sq = 0;
		try {
			pstmt = con.prepareStatement("select sq from member where mb_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mb_sq = rs.getInt("sq");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return mb_sq;
	}

	public int insertMemberHistory(MemberHistoryVO memberHisoryVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into member_history (mb_sq,evt_type) values (?,?)");
			pstmt.setInt(1, memberHisoryVO.getMb_sq());
			pstmt.setInt(2, memberHisoryVO.getEvt_type());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVO getMember(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		try {
			// binary는 대소문자 구분 mysqldb는 대소문자 구분해야함
			pstmt = con.prepareStatement("select sq,mb_id,mb_pw from member where binary(mb_id)=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setMb_sq(rs.getInt("sq"));
				vo.setMb_id(rs.getString("mb_id"));
				vo.setMb_pw(rs.getString("mb_pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

	public int updateLoginState(MemberVO memberVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update member set login_st=? where sq=?");
			pstmt.setBoolean(1, memberVO.isLogin_st());
			pstmt.setInt(2, memberVO.getMb_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
