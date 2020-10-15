package com.kb.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.action.ArticleDeleteAction;
import com.kb.www.action.ArticleDetailAction;
import com.kb.www.action.ArticleListAction;
import com.kb.www.action.ArticleRegisterAction;
import com.kb.www.action.ArticleUpdateAction;
import com.kb.www.action.ArticleUpdateProAction;
import com.kb.www.action.ArticleWriteAction;
import com.kb.www.action.LeaveAction;
import com.kb.www.action.MemberHistoryAction;
import com.kb.www.action.MemberInfoFormAction;
import com.kb.www.action.MemberInfoProAction;
import com.kb.www.action.MemberJoinFormAction;
import com.kb.www.action.MemberJoinProcAction;
import com.kb.www.action.MemberLoginFormAction;
import com.kb.www.action.MemberLoginProcAction;
import com.kb.www.action.MemberLogoutAction;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.sun.org.apache.xalan.internal.xsltc.dom.ForwardPositionIterator;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		// 이벤트 처리 콘트롤러 모듈화
		// 게시글 목록 보기 실행
		if (command.equals("/list.do")) {
			action = new ArticleListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 게시글 내용 보기 실행
		else if (command.equals("/detail.do")) {
			action = new ArticleDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원가입 실행
		else if (command.equals("/joinProc.do")) {
			action = new MemberJoinProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/join.do")) {
			action = new MemberJoinFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그인 실행
		else if (command.equals("/loginProc.do")) {
			action = new MemberLoginProcAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/login.do")) {
			action = new MemberLoginFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원탈퇴
		else if (command.equals("/leave.do")) {
			action = new LeaveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원정보수정
		else if (command.equals("/memberinfo.do")) {
			action = new MemberInfoFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberinfoPro.do")) {
			action = new MemberInfoProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그아웃
		else if (command.equals("/logout.do")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 멤버 히스토리
		else if (command.equals("/history.do")) {
			action = new MemberHistoryAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 글쓰기
		else if (command.equals("/write.do")) {
			action = new ArticleWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/register.do")) {
			action = new ArticleRegisterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 글 수정
		else if (command.equals("/update.do")) {
			action = new ArticleUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/updatePro.do")) {
			action = new ArticleUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 글 삭제
		else if (command.equals("/delete.do")) {
			action = new ArticleDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
