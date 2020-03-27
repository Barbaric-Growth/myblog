package blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.TagService;
import blog.utils.StringUtils;

/**
 * Servlet implementation class CommunityTagServlet
 */
@WebServlet("/CommunityTagServlet")
public class CommunityTagServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String get = StringUtils.pareCode(request.getParameter("get"));
		// 初始化标签
		TagService ts = TagService.getInstance();
		request.setAttribute("id_tag_map", ts.getCommunityTagAndArticle(get));

		request.getRequestDispatcher("/page/communitytags.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
