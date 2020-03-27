package blog.service;

import java.util.List;

import blog.dao.CommentDao;
import blog.daoImpl.CommentDaoImpl;
import blog.model.Comment;

public class CommentService {

	private CommentDao dao;

	private static CommentService instance;

	private CommentService() {
		dao = CommentDaoImpl.getInstance();
	}

	public static final CommentService getInstance() {
		if (instance == null) {
			try {
				instance = new CommentService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public List loadComment(int article_id) {
		return dao.getComment(article_id);
	}

	public boolean addComment(Comment comment) {
		return dao.addComment(comment);
	}

	public int star_diss(int id, int star_or_diss) {
		return dao.star_diss(id, star_or_diss);
	}

	public boolean deleteComment(int id) {
		return dao.deleteComment(id);
	}

	public List CommunityloadComment(int article_id) {
		// TODO Auto-generated method stub
		return dao.getCommunityComment(article_id);
	}

	public boolean addCommunityComment(Comment comment) {
		// TODO Auto-generated method stub
		return dao.addCommunityComment(comment);
	}

	public boolean deleteCommunityComment(int id) {
		// TODO Auto-generated method stub
		return dao.deleteCommunityComment(id);
	}

	public int star_Communitydiss(int id, int star_or_diss) {
		// TODO Auto-generated method stub
		return dao.star_Communitydiss(id, star_or_diss);
	}
}
