package blog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.model.User;

public class registerUtils {
	public static boolean login(HttpServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
			return false;

		UserDao dao = UserDaoImpl.getInstance();
		boolean flag = dao.registerlogin(username, password);
		if(!flag) {
			return false;
		}
		
		return true;

	}
}
