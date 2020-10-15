package com.kb.www.action;

import com.kb.www.common.*;
import com.kb.www.common.bcrpyt.BCrypt;
import com.kb.www.common.loginmanager.LoginManager;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberHistoryVO;
import com.kb.www.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import static com.kb.www.constants.Constants.MEMBER_HISTORY_EVENT_LOGIN;

public class MemberLoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mb_id = request.getParameter("id");
		String mb_pw = request.getParameter("pw");
		// sce개념 null검사부터 해야함. 만약 안하면 프로그램이 멈춘다.
		// 중요!!!!!! 정규표현식 검사하는 클래스파일 만들어 한번에 사용하기 RegExp클래스파일로 사용
		if (mb_id == null || mb_id.equals("") || mb_pw == null || mb_pw.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		mb_id = mb_id.trim();
		BoardService svc = new BoardService();
		MemberVO memberVO = svc.getMember(mb_id);
		if (memberVO.isLeave_fl() == true || memberVO == null || !BCrypt.checkpw(mb_pw, memberVO.getMb_pw())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디나 비밀번호를 잘못입력하셨습니다.'); location.href='/';</script>");
			out.close();
			return null;
		}

		// 로그인 기록 넣기위해 이름,이메일,성별 가져오기
		memberVO.setLogin_st(true);
		MemberHistoryVO memberHistoryVO = new MemberHistoryVO();
		memberHistoryVO.setMb_sq(memberVO.getMb_sq());
		memberHistoryVO.setEvt_type(MEMBER_HISTORY_EVENT_LOGIN);
		memberHistoryVO.setName(memberVO.getMb_name());
		memberHistoryVO.setEmail(memberVO.getMb_email());
		memberHistoryVO.setGender(memberVO.getMb_gender());

		if (!svc.loginMember(memberVO, memberHistoryVO)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인에 실패하였습니다.'); location.href='/';</script>");
			out.close();
			return null;
		}

		// 로그인관리
		LoginManager lm = LoginManager.getInstance();
		// 세션은 request에 담겨있음
		HttpSession session = request.getSession();
		lm.setSession(session, memberVO.getMb_id());
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
