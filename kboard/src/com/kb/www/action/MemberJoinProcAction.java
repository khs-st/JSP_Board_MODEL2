package com.kb.www.action;

import com.kb.www.common.*;
import com.kb.www.common.bcrpyt.BCrypt;
import com.kb.www.common.loginmanager.LoginManager;
import com.kb.www.common.regexp.RegExp;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberVO;
import com.kb.www.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.bcrpyt.BCrypt.*;
import static com.kb.www.common.regexp.RegExp.*;
import static com.kb.www.constants.Constants.*;

public class MemberJoinProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		if (lm.getMemberId(request.getSession()) != null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/");
			forward.setRedirect(true);
			return forward;
		}
		String mb_id = request.getParameter("id");
		String mb_pw = request.getParameter("pw");
		String pw_confirm = request.getParameter("pw_confirm");
		String mb_name = request.getParameter("mb_name");
		String mb_email = request.getParameter("mb_email");
		String mb_gender = request.getParameter("mb_gender");
		// sce개념 null검사부터 해야함. 만약 안하면 프로그램이 멈춘다.
		// 중요!!!!!! 정규표현식 검사하는 클래스파일 만들어 한번에 사용하기 RegExp클래스파일로 사용
		if (mb_id == null || mb_id.equals("") || !RegExp.checkString(MEMBER_ID, mb_id) || mb_pw == null
				|| mb_pw.equals("") || !RegExp.checkString(MEMBER_PW, mb_pw) || mb_email == null || mb_email.equals("")
				|| !RegExp.checkString(MEMBER_EMAIL, mb_email) || mb_name == null || mb_name.equals("")
				|| !RegExp.checkString(MEMBER_NAME, mb_name)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		if (!mb_pw.equals(pw_confirm)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}

		// Member객체 이용하여 아이디 및 이름,이메일,성별 넣기
		MemberVO memberVO = new MemberVO();
		memberVO.setMb_id(mb_id);
		// 비밀번호 암호화(BCrypt 자바클래스 활용)
		memberVO.setMb_pw(BCrypt.hashpw(mb_pw, gensalt(12)));
		memberVO.setMb_name(mb_name);
		memberVO.setMb_email(mb_email);
		memberVO.setMb_gender(mb_gender);
		// MemberHistory객체 이용하여 아이디 및 이름,이메일,성별 넣기
		MemberHistoryVO memberHistoryVO = new MemberHistoryVO();
		memberHistoryVO.setEvt_type(MEMBER_HISTORY_EVENT_JOIN);
		memberHistoryVO.setName(mb_name);
		memberHistoryVO.setEmail(mb_email);
		memberHistoryVO.setGender(mb_gender);

		BoardService svc = new BoardService();
		if (!svc.joinMember(memberVO, memberHistoryVO)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입에 실패하였습니다.'); location.href='/';</script>");
			out.close();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
