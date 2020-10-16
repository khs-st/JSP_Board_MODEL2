package com.kb.www.common.loginmanager;

import com.kb.www.action.MemberLogoutAction;
import com.kb.www.common.Action;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Enumeration;
import java.util.Hashtable;

public class LoginManager implements HttpSessionBindingListener {
	private static Hashtable LoginUsers = new Hashtable();

	private LoginManager() {
		super();
	}

	public static LoginManager getInstance() {
		return LoginManager.LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final LoginManager INSTANCE = new LoginManager();
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		LoginUsers.put(event.getSession(), event.getName());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		Action action = new MemberLogoutAction();
		((MemberLogoutAction) action).logoutProc(getMemberId(event.getSession()));
		LoginUsers.remove(event.getSession());
	}

	public void removeSession(String id) {
		Enumeration e = LoginUsers.keys();
		HttpSession session = null;
		while (e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			if (LoginUsers.get(session).equals(id)) {
				session.invalidate();
			}
		}
	}

	public boolean isLogin(String sessionId) {
		boolean isLogin = false;
		Enumeration e = LoginUsers.keys();
		String key = "";
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			if (sessionId.equals(key)) {
				isLogin = true;
			}
		}
		return isLogin;
	}

	public void setSession(HttpSession session, String id) {
		session.setAttribute(id, this);
	}

	public String getMemberId(HttpSession session) {
		return (String) LoginUsers.get(session);
	}
}
