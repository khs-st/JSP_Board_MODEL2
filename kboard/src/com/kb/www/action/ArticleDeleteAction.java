package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.loginmanager.*;
import com.kb.www.common.regexp.*;
import com.kb.www.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.regexp.RegExp.ARTICLE_NUM;
import static com.kb.www.common.regexp.RegExp.IS_NUMBER;

public class ArticleDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardService svc = new BoardService();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		// sce개념 null검사부터 해야함. 만약 안하면 프로그램이 멈춘다.
		// 중요!!!!!! 정규표현식 검사하는 클래스파일 만들어 한번에 사용하기 RegExp클래스파일로 사용
		String num = request.getParameter("num");
		if (num == null || num.equals("") || !RegExp.checkString(ARTICLE_NUM, num)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		int buff = Integer.parseInt(num);
		if (buff <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		// 위까지 통과한다면 num은 1이상 숫자부터 된다. 오류제어 끝

		String writerId = svc.getWriterId(buff);
		if (writerId == null || !id.equals(writerId)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		if (!svc.deleteArticle(buff)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글을 삭제하는데 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
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
		ActionForward forward = new ActionForward();
		forward.setPath("/list.do?pn=" + page);
		forward.setRedirect(true);
		return forward;
	}
}
