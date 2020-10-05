package com.kb.www.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVO;

public class ArticleListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BoardService svc=new BoardService();
		ArrayList<ArticleVO> articleList=svc.getArticleList();
		ActionForward forward=new ActionForward();
		request.setAttribute("list",articleList);
		forward.setPath("/views/list.jsp");
		return forward;
	}
}
