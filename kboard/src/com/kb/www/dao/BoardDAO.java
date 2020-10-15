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
			pstmt = con.prepareStatement("select " + "b.num" + ",m.mb_id," + "b.subject," + "b.content," + "b.hit,"
					+ "b.wdate" + " from kboard b" + " inner join member m on b.mb_sq = m.sq");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setArticleNum(rs.getInt("num"));
				vo.setArticleTitle(rs.getString("subject"));
				vo.setArticleContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setWriteDate(rs.getString("wdate"));
				vo.setId(rs.getString("mb_id"));
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
			pstmt = con.prepareStatement("select" + " b.num" + ", b.mb_sq" + ", b.subject" + ", b.content" + ", b.hit"
					+ ", b.wdate" + ", b.udate" + ", b.ddate" + ", m.mb_id" + " from kboard b"
					+ " inner join member m on b.mb_sq = m.sq" + " where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo2 = new ArticleVO();
				vo2.setArticleNum(rs.getInt("num"));
				vo2.setArticleTitle(rs.getString("subject"));
				vo2.setArticleContent(rs.getString("content"));
				vo2.setHit(rs.getInt("hit"));
				vo2.setWriteDate(rs.getString("wdate"));
				vo2.setUpdateDate(rs.getString("udate"));
				vo2.setDeleteDate(rs.getString("ddate"));
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

	// insertArticle 글쓰기 구현
	public int insertArticle(ArticleVO vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into kboard (mb_sq,subject, content) values(?,?,?)");
			pstmt.setInt(1, vo.getMb_sq());
			pstmt.setString(2, vo.getArticleTitle());
			pstmt.setString(3, vo.getArticleContent());
			count = pstmt.executeUpdate();
			commit(con);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// updateArticle 글 수정 구현
	public int updateArticle(ArticleVO vo) {
		PreparedStatement pstmt = null;
		String NowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		vo.setUpdateDate(NowDate);
		int count = 0;
		try {
			pstmt = con.prepareStatement("update kboard set subject=?, content=?, udate=? where num=?");
			pstmt.setString(1, vo.getArticleTitle());
			pstmt.setString(2, vo.getArticleContent());
			pstmt.setString(3, vo.getUpdateDate());
			pstmt.setInt(4, vo.getArticleNum());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// deleteArticle 글 삭제 구현
	public int deleteArticle(int num) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("delete from kboard where num=?");
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
			commit(con);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

// getWriterId 글 작성자 글 번호로 불러오기
	public String getWriterId(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			pstmt = con
					.prepareStatement("select m.mb_id from kboard b inner join member m on b.mb_sq = m.sq where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("mb_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
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

	// 사용자 회원가입(member테이블에 insert)
	public int insertMember(MemberVO memberVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con
					.prepareStatement("insert into member(mb_id, mb_pw,mb_name,mb_email,mb_gender) values(?,?,?,?,?);");
			pstmt.setString(1, memberVO.getMb_id());
			pstmt.setString(2, memberVO.getMb_pw());
			pstmt.setString(3, memberVO.getMb_name());
			pstmt.setString(4, memberVO.getMb_email());
			pstmt.setString(5, memberVO.getMb_gender());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 탈퇴한 사용자 회원가입(동일한 id)
	public int insertLeavedMember(MemberVO memberVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"update member set mb_pw=?,mb_name=?,mb_email=?,mb_gender=?,leave_fl=? where mb_id=? and sq=? and leave_fl=true");
			pstmt.setString(1, memberVO.getMb_pw());
			pstmt.setString(2, memberVO.getMb_name());
			pstmt.setString(3, memberVO.getMb_email());
			pstmt.setString(4, memberVO.getMb_gender());
			pstmt.setBoolean(5, false);
			pstmt.setString(6, memberVO.getMb_id());
			pstmt.setInt(7, memberVO.getMb_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 사용자 회원정보수정
	public int updateMember(MemberVO memberVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update member set mb_pw=?,mb_email=? where mb_id=?");
			pstmt.setString(1, memberVO.getMb_pw());
			pstmt.setString(2, memberVO.getMb_email());
			pstmt.setString(3, memberVO.getMb_id());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	// 외래키를 이용하여 sq값에 따른 mb_id 값 입력
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

	// 아이디 중복체크를 위해 아이디 불러오기
//	public boolean getMemberId(String id) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean isChk = false;
//		try {
//			pstmt = con.prepareStatement("select mb_id from member where mb_id=?");
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				if (rs.getString("mb_id").equals(id)) {
//					isChk = true;
//				} else {
//					isChk = false;
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return isChk;
//	}

//member_history테이블에 insert
	public int insertMemberHistory(MemberHistoryVO memberHisoryVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into member_history (mb_sq,evt_type,name,email,gender) values (?,?,?,?,?)");
			pstmt.setInt(1, memberHisoryVO.getMb_sq());
			pstmt.setInt(2, memberHisoryVO.getEvt_type());
			pstmt.setString(3, memberHisoryVO.getName());
			pstmt.setString(4, memberHisoryVO.getEmail());
			pstmt.setString(5, memberHisoryVO.getGender());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

//사용자 정보 가져오기
	public MemberVO getMemberInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		try {
			// binary는 대소문자 구분 mysqldb는 대소문자 구분해야함
			pstmt = con.prepareStatement("select mb_id,mb_pw,mb_email from member where binary(mb_id)=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setMb_id(rs.getString("mb_id"));
				vo.setMb_pw(rs.getString("mb_pw"));
				vo.setMb_email(rs.getString("mb_email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

	// 사용자 로그인
	public MemberVO getMember(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		try {
			// binary는 대소문자 구분 mysqldb는 대소문자 구분해야함
			pstmt = con.prepareStatement(
					"select sq,mb_id,mb_pw,mb_name,mb_email,mb_gender from member where binary(mb_id)=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setMb_sq(rs.getInt("sq"));
				vo.setMb_id(rs.getString("mb_id"));
				vo.setMb_pw(rs.getString("mb_pw"));
				vo.setMb_name(rs.getString("mb_name"));
				vo.setMb_email(rs.getString("mb_email"));
				vo.setMb_gender(rs.getString("mb_gender"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

//로그인 기록 갱신
	public int updateLoginState(MemberVO memberVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update member set login_st=? where leave_fl=false and sq=?");
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

//회원탈퇴 (leave_fl==0 회원탈퇴 안한것, leave_fl==1 회원탈퇴한것)
	public int updateLeaveFlag(MemberVO memberVO) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update member set leave_fl=?, login_st=? where sq=?");
			pstmt.setBoolean(1, memberVO.isLeave_fl());
			pstmt.setBoolean(2, memberVO.isLogin_st());
			pstmt.setInt(3, memberVO.getMb_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

//로그인 중복체크를 위한 멤버카운트
	public int getMemberCount(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from member" + " where binary(mb_id)=? and leave_fl=false");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	// 탈퇴후 중복체크를 위한 멤버카운트
	public int getLeaveMemberCount(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from member" + " where binary(mb_id)=? and leave_fl=true");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	// member_history 불러오기
	public ArrayList<MemberHistoryVO> getMemberHistory(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberHistoryVO> list = new ArrayList<MemberHistoryVO>();
		try {
			pstmt = con.prepareStatement("select * from member_history");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberHistoryVO vo = new MemberHistoryVO();
				vo.setEvt_type(rs.getInt("evt_type"));
				vo.setDttm(rs.getString("dttm"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
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

}
