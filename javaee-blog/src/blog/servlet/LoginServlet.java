package blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.Tag;

import blog.dao.ArticleDao;
import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.db.VisitorDB;
import blog.model.Article;
import blog.model.User;
import blog.service.ArticleService;
import blog.service.TagService;
import blog.utils.LoginUtils;

/**
 * Login->index.jsp->init data
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean flag = LoginUtils.login(request);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(flag||user!=null) {
			//解决中文乱码
			response.setContentType("text/html");		
			PrintWriter out = response.getWriter();				
			request.setCharacterEncoding("UTF-8");		
			response.setCharacterEncoding("UTF-8");
			
			// 初始化分类
			ArticleService as = ArticleService.getInstance();
			request.setAttribute("sort_count_map", as.getSortAndCount());
			// 初始化获取标签
			TagService ts = TagService.getInstance();
			request.setAttribute("tag_list", ts.getAllTag());

			// 初始化侧边栏 日志、分类、标签的个数
			request.setAttribute("article_number", as.getCount(ArticleDao.SEARCH_ARTICLE));
			request.setAttribute("sort_number", as.getCount(ArticleDao.SEARCH_SORT));
			request.setAttribute("tags_number", ts.getTagCount());

			// 阅读排行
			request.setAttribute("visit_rank", as.getVisitRank());

			// 传网站的统计数据
			request.setAttribute("visited", VisitorDB.totalVisit());
			request.setAttribute("member", VisitorDB.totalMember());
			//分页
			//当前页数
			int pageIndex=1;
			//页量
			int pageSize=5;
			//总页数
			int pageCount=1;
			//获取从a标签中传输的值（request.getParameter可以拿取表单的值，也可以拿取a标签所传输的值）		
			String pageIndex2 = request.getParameter("pageIndex");		
			System.out.println(pageIndex2);		
			if (pageIndex2!=null&&!pageIndex2.equals("")) {			 
				pageIndex = Integer.parseInt(pageIndex2);		
				}
					
			List<Article>lt=as.getArticle();		
			//获取总数据量		
			int total=lt.size();		
			//获取总页数		
			pageCount=total%pageSize==0?total/pageSize:(total/pageSize)+1;		
			//判定最小页数为1，不能低于1		
			if (pageIndex<1) {			
				pageIndex=1;		
				//判定最大页数不能高于总页数		
				}else if (pageIndex>pageCount) {			
					pageIndex=pageCount;		
			}
			int page=(pageIndex-1)*pageSize;		
			int size=pageSize;				
			List<Article>lt1=as.fengYe(page, size); 
			// 初始化文章列表
			request.setAttribute("article_list", lt1);
			//当前页数		
			request.setAttribute("page",pageIndex);                   
			//总页数	    
			request.setAttribute("count", pageCount);                   
			//存放数据的集合        
			request.setAttribute("list", lt1);
			// 转发到 博客主页 界面
			request.getRequestDispatcher("/page/main.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mesg", "用户名或者密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
