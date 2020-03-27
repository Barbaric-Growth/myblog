package blog.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter implements Filter {
	private List<String> list = new ArrayList<String>();

	public CommentFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
//过滤评论敏感词
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8"); 
		HttpServletRequest rq = (HttpServletRequest) req;
		HttpServletResponse rp = (HttpServletResponse) resp;
		String values = (String) rq.getAttribute("w_content");
		System.out.println(values);
		for (String str : list) { // 如果遇到敏感词汇，就将其替代为 ***
			if (values.contains(str)) {
				values = values.replaceAll(str, "***");
			}
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}
