package com.kb.www.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.kb.www.vo.ArticleVO;

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

	public ArrayList<ArticleVO> getArticleList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO vo = new ArticleVO();
		ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
		String NowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		vo.setWriteDate(NowDate);
		try {
			pstmt = con.prepareStatement("select * from kboard");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo.setArticleNum(rs.getInt("num"));
				vo.setArticleTitle(rs.getString("subject"));
				vo.setId(rs.getString("id"));
				vo.setHit(rs.getInt("hit"));
				vo.setWriteDate(rs.getString("wdate"));
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
