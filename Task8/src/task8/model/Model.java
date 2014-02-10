package task8.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private UserDAO userDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			// String jdbcDriver = config.getInitParameter("jdbcDriverName");
			// String jdbcURL = config.getInitParameter("jdbcURL");

			// ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO = new UserDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public UserDAO getCustomerDAO() {
		return userDAO;
	}
}
