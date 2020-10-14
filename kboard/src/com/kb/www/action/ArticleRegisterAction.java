package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.loginmanager.*;
import com.kb.www.common.regexp.*;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.regexp.RegExp.*;

public class ArticleRegisterAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
			out.close();
			return null;
		}

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

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
		BoardService svc = new BoardService();
		ArticleVO vo = new ArticleVO();
		vo.setArticleTitle(subject);
		vo.setArticleContent(content);
		vo.setMb_sq(svc.getMemberSequence(id));

		if (!svc.insertArticle(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글을 저장하는데 실패하였습니다.'); location.href='/';</script>");
			out.close();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/list.do");
		forward.setRedirect(true);
		return forward;
	}
}
