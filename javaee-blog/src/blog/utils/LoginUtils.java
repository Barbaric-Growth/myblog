package blog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.model.User;

public class LoginUtils {

	public static boolean login(HttpServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return false;

		UserDao dao = UserDaoImpl.getInstance();
		boolean flag = dao.login(username, password);
		User user =  new User(username, password);

		if(!flag) {
			
			return false;
		}
		// 写入session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return true;

	}

	public static boolean managelogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return false;

		UserDao dao = UserDaoImpl.getInstance();
		boolean flag = dao.managelogin(username, password);
		User user =  new User(username, password);

		if(!flag) {
			
			return false;
		}
		// 写入session
		HttpSession session = request.getSession();
		session.setAttribute("manageuser", user);
		return true;
	}

}
