package com.kb.www.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.regexp.RegExp;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVO;

import static com.kb.www.common.regexp.RegExp.PAGE_NUM;

public class ArticleDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardService svc = new BoardService();
		String num = request.getParameter("num");
		// sce개념 null검사부터 해야함. 만약 안하면 프로그램이 멈춘다.
		// 중요!!!!!! 정규표현식 검사하는 클래스파일 만들어 한번에 사용하기 RegExp클래스파일로 사용
		if (num == null || num.equals("") || !RegExp.checkString(PAGE_NUM, num)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}
		int buff = Integer.parseInt(num);
		if (buff <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}
//위까지 통과한다면 num은 1이상 숫자부터 된다. 오류제어 끝
		ArticleVO article = svc.getArticleDetail(buff);
		if (article == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}

		ActionForward forward = new ActionForward();
		request.setAttribute("article", article);
		forward.setPath("/views/detail.jsp");
		return forward;
	}
}
