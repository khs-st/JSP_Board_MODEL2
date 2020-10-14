package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.loginmanager.*;
import com.kb.www.common.regexp.*;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVO;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.regexp.RegExp.ARTICLE_CONTENT;
import static com.kb.www.common.regexp.RegExp.ARTICLE_SUBJECT;
import static com.kb.www.common.regexp.RegExp.PAGE_NUM;

public class ArticleUpdateProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String num = request.getParameter("num");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		BoardService svc = new BoardService();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		// sce개념 null검사부터 해야함. 만약 안하면 프로그램이 멈춘다.
		// 중요!!!!!! 정규표현식 검사하는 클래스파일 만들어 한번에 사용하기 RegExp클래스파일로 사용
		if (subject == null || subject.equals("") || !RegExp.checkString(ARTICLE_SUBJECT, subject) || content == null
				|| content.equals("") || !RegExp.checkString(ARTICLE_CONTENT, content)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		if (num == null || num.equals("") || !RegExp.checkString(PAGE_NUM, num)) {
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
		String writerId = svc.getWriterId(buff);
		if (writerId == null || !id.equals(writerId)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		ArticleVO vo = new ArticleVO();
		vo.setArticleNum(buff);
		vo.setArticleTitle(subject);
		vo.setArticleContent(content);

		if (!svc.updateArticle(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글을 수정하는데 실패하였습니다.'); location.href='/';</script>");
			out.close();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/detail.do?num=" + buff);
		forward.setRedirect(true);
		return forward;
	}
}
