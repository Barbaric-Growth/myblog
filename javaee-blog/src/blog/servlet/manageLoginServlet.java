package blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.db.VisitorDB;
import blog.service.ArticleService;
import blog.service.TagService;
import blog.utils.LoginUtils;
import blog.utils.registerUtils;

/**
 * Servlet implementation class mangeLogin
 */
@WebServlet("/manageLoginServlet")
public class manageLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		boolean flag = LoginUtils.managelogin(request);
		if(flag)
		{
			ArticleService as = ArticleService.getInstance();
			request.setAttribute("articles", as.getCommunityArticle());
			// 传所有的分类
			request.setAttribute("ResultSet", as.getCommunity());
			request.setAttribute("sort", as.getCommunityAllSort());
			// 传所有的标签
			TagService ts = TagService.getInstance();
			request.setAttribute("tags", ts.getCommunityAllTag());
			// 传网站的统计数据
			request.setAttribute("visited", VisitorDB.totalVisit());
			request.setAttribute("member", VisitorDB.totalMember());
			// 转发到 博客主页 界面
			request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mesg", "用户名或者密码错误");
			request.getRequestDispatcher("manage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
