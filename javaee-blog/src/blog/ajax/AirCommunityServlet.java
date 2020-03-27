package blog.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.ArticleService;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AirCommunityServlet
 */
@WebServlet("/AirCommunityServlet")
public class AirCommunityServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 业务操作 获取评论id
		String id = request.getParameter("id");
		// 返回的数据
		JSONObject jo = new JSONObject();
		boolean repeat = false;
		// 写一个cookie防止重复提交
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("star_arti" + id)) {
				// 重复提交了数据
				jo.put("msg", "failed");
				repeat = true;
				break;
			}
		}
		if (!repeat) {

			ArticleService as = ArticleService.getInstance();
			int new_star = as.starCommunityArticle(Integer.parseInt(id));

			jo.put("msg", "success");
			jo.put("new_star", new_star);
			// 发送新的cookie
			Cookie cookie = new Cookie("star_arti" + id, System.currentTimeMillis() + "");
			// 设置有效期 15分钟
			cookie.setMaxAge(15 * 60);
			// 设置有效目录
			cookie.setPath("/Blog");
			// 写会浏览器
			response.addCookie(cookie);
		}
		// 写回ajax
		response.getWriter().println(jo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
