package blog.dao;

import blog.model.User;

public interface UserDao {

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean register(String username, String password);

	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean login(String username, String password);

	boolean registerlogin(String username, String password);

	boolean managelogin(String username, String password);

}