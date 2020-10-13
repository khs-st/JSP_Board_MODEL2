package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.loginmanager.LoginManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		ActionForward forward = new ActionForward();
		if (lm.getMemberId(request.getSession()) == null) {
			forward.setPath("/views/joinPage/joinForm.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}
