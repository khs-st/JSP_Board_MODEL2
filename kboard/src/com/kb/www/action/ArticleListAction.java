package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.pagenation.*;
import com.kb.www.common.regexp.*;
import com.kb.www.service.BoardService;
import com.kb.www.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.kb.www.common.regexp.RegExp.IS_NUMBER;

public class ArticleListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 페이지네이션 후 게시글 불러오기
		// getArticleCount를 query문으로 변수를 받아 (Webservlet처럼 *.do처럼) query를 이용해 페이지네이션 구현
		String pageNum = request.getParameter("pn");
		if (pageNum == null || !RegExp.checkString(IS_NUMBER, pageNum)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		int page = Integer.parseInt(pageNum);
		if (page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		String filter = request.getParameter("filter");
		String keyword = request.getParameter("keyword");
		String query = "";
		if (filter == null || filter.equals("")) {
			filter = "all";
		}

		if (keyword != null && !keyword.equals("")) {
			query = makeSearchQuery(filter, keyword);
		}

		BoardService svc = new BoardService();
		Pagenation pagenation = new Pagenation(page, svc.getArticleCount(query));
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/list.do?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<ArticleVO> list = svc.getArticleList(pagenation, query);

		ActionForward forward = new ActionForward();
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", list);
		forward.setPath("/views/list.jsp");
		return forward;
	}

	private String makeSearchQuery(String filter, String keyword) {
		String query = null;
		if (filter.equals("all")) {
			query = " and (subject like '%" + keyword + "%' or content like '%" + keyword + "%')";
		} else if (filter.equals("subject")) {
			query = " and (subject like '%" + keyword + "%')";
		} else {
			query = " and (content like '%" + keyword + "%')";
		}

		return query;
	}
}
