package blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.ArticleService;
import blog.utils.StringUtils;

/**
 * Servlet implementation class CommunitySortServlet
 */
@WebServlet("/CommunitySortServlet")
public class CommunitySortServlet extends HttpServlet {
	
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取的是所有分类 还是一个分类的文章
				String get = StringUtils.pareCode(request.getParameter("get"));
				// 初始化分类和和文章
				ArticleService as = ArticleService.getInstance();
				request.setAttribute("sort_article_map", as.getCommunitySortAndAirticle(get));
				// 转发
				request.getRequestDispatcher("/page/communitysort.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
