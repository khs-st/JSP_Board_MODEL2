package com.kb.www.action;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.bcrpyt.*;
import com.kb.www.common.loginmanager.*;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberHistoryVO;
import com.kb.www.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_LOGOUT;

public class MemberLogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String mb_id = lm.getMemberId(session);
		if (mb_id != null) {
			lm.removeSession(mb_id);
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}

	public void logoutProc(String mb_id) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMb_id(mb_id);

		// 로그인 상태 false로 변경
		memberVO.setLogin_st(false);

		MemberHistoryVO memberHistoryVO = new MemberHistoryVO();
		memberHistoryVO.setEvt_type(MEMBER_HISTORY_EVENT_LOGOUT);
		
		BoardService svc = new BoardService();
		if (!svc.logoutMember(memberVO, memberHistoryVO)) {
			System.out.println(mb_id + " 회원의 로그아웃 처리에 실패하였습니다.");
		}
	}

}
