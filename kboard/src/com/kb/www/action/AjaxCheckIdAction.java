package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxCheckIdAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		// id 정규 표현식으로 검사필요

		BoardService service = new BoardService();
		request.setAttribute("count", service.getMemberCount(id));

		ActionForward forward = new ActionForward();
		forward.setPath("/views/ajax/checkId.jsp");
		return forward;
	}
}
