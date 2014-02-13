package task8.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private UserDAO userDAO;
	private CommentHistoryDAO commentHistoryDAO;
	private WebsiteVisitDAO websiteVisitDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			// String jdbcDriver = config.getInitParameter("jdbcDriverName");
			// String jdbcURL = config.getInitParameter("jdbcURL");

			// ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO = new UserDAO();
			commentHistoryDAO = new CommentHistoryDAO();
			websiteVisitDAO = new WebsiteVisitDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public CommentHistoryDAO getCommentHistoryDAO() {
		return commentHistoryDAO;
	}

	public WebsiteVisitDAO getWebsiteVisitDAO() {
		return websiteVisitDAO;
	}
}
