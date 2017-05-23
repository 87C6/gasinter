package com.wangsd.filter;

import com.wangsd.common.util.ConfigUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//如果没有登录
		String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/", 1), req.getRequestURI().length());
		if (!"/login.jsp".equals(requestURI) && !"/getPwd.jsp".equals(requestURI)) {
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute(ConfigUtil.getSessionInfoName()) == null) {
				res.sendRedirect(req.getContextPath() + "/login.jsp");
				return;
			}
		}
		//继续访问其他资源
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
