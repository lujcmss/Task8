package task8.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private UserDAO userDAO;
	private LikeDAO likeDAO;
	private DislikeDAO dislikeDAO;
	private CommentHistoryDAO commentHistoryDAO;
	private WebsiteVisitDAO websiteVisitDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			// String jdbcDriver = config.getInitParameter("jdbcDriverName");
			// String jdbcURL = config.getInitParameter("jdbcURL");

			// ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO = new UserDAO();
			likeDAO = new LikeDAO();
			dislikeDAO = new DislikeDAO();
			commentHistoryDAO = new CommentHistoryDAO();
			websiteVisitDAO = new WebsiteVisitDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public LikeDAO getLikeDAO() {
		return likeDAO;
	}
	
	public DislikeDAO getDislikeDAO() {
		return dislikeDAO;
	}
	
	public CommentHistoryDAO getCommentHistoryDAO() {
		return commentHistoryDAO;
	}

	public WebsiteVisitDAO getWebsiteVisitDAO() {
		return websiteVisitDAO;
	}
}
