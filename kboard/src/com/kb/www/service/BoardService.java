package com.kb.www.service;

import java.sql.Connection;
import java.util.ArrayList;


import com.kb.www.dao.BoardDAO;
import com.kb.www.vo.ArticleVO;
import static com.kb.www.common.JdbcUtil.*;



public class BoardService {
//ArticleListAction에서 호출
	public ArrayList<ArticleVO> getArticleList() {
		// TODO Auto-generated method stub
		BoardDAO dao=BoardDAO.getInstance();
		//JdbcUtil의 getConnetcion을 이용하여 MysqlDB와 연결
		Connection con=getConnection();
		//dao와 mysqldb의 데이터를 con을 사용하여 공유
		dao.setConnection(con);
		ArrayList<ArticleVO> list =dao.getArticleList();
		close(con);
		return list;
	}

}
