package edu.wlxy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterCheckSession
 */
public class FilterCheckSession implements Filter {

    public FilterCheckSession() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession   session=req.getSession();
		String nn=(String)session.getAttribute("uname");
		
		if(nn==null ){
			response.getWriter().print("你还没有登录，无权访问该内容");
		}else{
			chain.doFilter(request, response);// 放行
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
