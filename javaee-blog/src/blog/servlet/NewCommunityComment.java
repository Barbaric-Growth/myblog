package blog.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Comment;
import blog.service.CommentService;
import blog.utils.DateUtils;
import blog.utils.FailException;
import blog.utils.Form2Bean;

/**
 * Servlet implementation class NewCommunityComment
 */
@WebServlet("/NewCommunityComment")
public class NewCommunityComment extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cookie_name = "comment_cookie" + request.getParameter("id");

		// 判断是否恶意提交
		boolean isRpeat = false;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookie_name)) {
					isRpeat = true;
					break;
				}
			}
		}

		// 返回的信息
		String info;
		if (!isRpeat) {
			Comment cm;
			// 获取对象
			try {
				cm = Form2Bean.commentForm2Bean(request);
				CommentService cs = CommentService.getInstance();
				boolean result = cs.addCommunityComment(cm);
				if (!result) {
					info = "comment failed!";
				} else {
					info = "comment success!";
				}
			} catch (FailException e) {
				e.printStackTrace();
				info = "comment failed!";
			}
		} else {
			info = "repeat submit comment!";
		}

		// 发送新的cookie
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		String currentTime = format.format(date);
		Cookie c = new Cookie(cookie_name, currentTime);
		c.setMaxAge(60 * 60);
		c.setPath("/Blog");
		response.addCookie(c);

		request.setAttribute("info", info);
		request.getRequestDispatcher("/CommunityArticleServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
