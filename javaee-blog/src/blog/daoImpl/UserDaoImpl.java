package blog.daoImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import blog.dao.TagDao;
import blog.dao.UserDao;
import blog.db.C3P0Connection;
import blog.model.User;
import blog.utils.DBUtils;

/**
 * 用户服务的具体实现类
 *
 */
public class UserDaoImpl implements UserDao {

	private Connection conn;

	private UserDaoImpl() {
		conn = C3P0Connection.getInstance().getConnection();
	}

	private static UserDao instance;

	public static final UserDao getInstance() {
		if (instance == null) {
			try {
				instance = new UserDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see blog.daoImpl.UserDao#register(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean register(String username, String password) {
		// todo
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see blog.daoImpl.UserDao#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) {
		String sql = "select * from t_user ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// bean导入
			while (rs.next()) {
				String s1 = rs.getString("user_name");
				String s2 = rs.getString("user_password");
				if (username.equalsIgnoreCase(s1) && password.equalsIgnoreCase(s2)) {
					return true;
				}
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean registerlogin(String username, String password) {
		// TODO Auto-generated method stub
		int count=0;
		String sql = "select * from t_user ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// bean导入
			while (rs.next()) {
				String s1 = rs.getString("user_name");
				String s2 = rs.getString("user_password");
				if (username.equalsIgnoreCase(s1)) {
					return false;
				}
				count++;
			}
			sql = "insert into t_user values(?,?,?)";
			PreparedStatement ps1;
			ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, count+1);
			ps1.setString(2, username);
			ps1.setString(3, password);
			ps1.executeUpdate();
			DBUtils.Close(ps1);
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean managelogin(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from t_manageuser ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// bean导入
			while (rs.next()) {
				String s1 = rs.getString("user_name");
				String s2 = rs.getString("user_password");
				if (username.equalsIgnoreCase(s1) && password.equalsIgnoreCase(s2)) {
					return true;
				}
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
