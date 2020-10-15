package com.kb.www.action;

import com.kb.www.common.*;
import com.kb.www.common.loginmanager.LoginManager;
import com.kb.www.dao.BoardDAO;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberHistoryVO;
import com.kb.www.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_LEAVE;

public class LeaveAction implements Action {
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

		BoardService svc = new BoardService();
		MemberVO memberVO = new MemberVO();

		memberVO = svc.getMember(id);
		memberVO.setLeave_fl(true);
		memberVO.setLogin_st(false);
		MemberHistoryVO memberHistoryVO = new MemberHistoryVO();
		memberHistoryVO.setMb_sq(svc.getMemberSequence(id));
		memberHistoryVO.setEvt_type(MEMBER_HISTORY_EVENT_LEAVE);

		if (!svc.leaveMember(memberVO, memberHistoryVO)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 탈퇴에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/logout.do");
		forward.setRedirect(true);
		return forward;
	}

}
