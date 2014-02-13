package task8.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import task8.databeans.CommentBean;
import task8.databeans.UserBean;
import task8.databeans.WebsiteVisitBean;
import task8.model.CommentHistoryDAO;
import task8.model.Model;
import task8.model.UserDAO;
import task8.model.WebsiteVisitDAO;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());

		Action.add(new Home(model));
		Action.add(new Login(model));
		Action.add(new Logout(model));
		Action.add(new CommentFlickr(model));
		Action.add(new ViewFlickr(model));
		Action.add(new TwitterCallback(model));
		Action.add(new CommentHistory(model));
		Action.add(new WebsiteStatistics(model));
		
		UserDAO userDAO = model.getUserDAO();
		CommentHistoryDAO commentHistoryDAO = model.getCommentHistoryDAO();
		WebsiteVisitDAO websiteVisitDAO = model.getWebsiteVisitDAO();
		
		UserBean userBean = new UserBean();
		userBean.setScreen_name("Trey Lu");
		userDAO.insert(userBean);
		userBean.setScreen_name("Patricio");
		userDAO.insert(userBean);
		userBean.setScreen_name("David Wu");
		userDAO.insert(userBean);
		userBean.setScreen_name("Yi Li");
		userDAO.insert(userBean);
		userBean.setScreen_name("Akshata");
		userDAO.insert(userBean);
		
		CommentBean commentBean = new CommentBean();
		commentBean.setUserBean(userDAO.getUserByUsername("Trey Lu"));
		commentBean.setComment("Perfect!");
		for (int i = 0; i < 10; i++) {
			commentBean.setDate(new Date(System.currentTimeMillis() - 1*86400*1000));
			commentHistoryDAO.insert(commentBean);
		}
		
		commentBean.setUserBean(userDAO.getUserByUsername("Patricio"));
		commentBean.setComment("Nice.");
		for (int i = 0; i < 5; i++) {
			commentBean.setDate(new Date(System.currentTimeMillis() - 3*86400*1000));
			commentHistoryDAO.insert(commentBean);
		}

		commentBean.setUserBean(userDAO.getUserByUsername("David Wu"));
		commentBean.setComment("Well Done!");
		for (int i = 0; i < 9; i++) {
			commentBean.setDate(new Date(System.currentTimeMillis() - 2*86400*1000));
			commentHistoryDAO.insert(commentBean);
		}

		commentBean.setUserBean(userDAO.getUserByUsername("Yi Li"));
		commentBean.setComment("Good!");
		for (int i = 0; i < 6; i++) {
			commentBean.setDate(new Date(System.currentTimeMillis() - i*86400*1000));
			commentHistoryDAO.insert(commentBean);
		}
		
		commentBean.setUserBean(userDAO.getUserByUsername("Akshata"));
		commentBean.setComment("Excellent!");
		for (int i = 0; i < 2; i++) {
			commentBean.setDate(new Date(System.currentTimeMillis() - 2*86400*1000));
			commentHistoryDAO.insert(commentBean);
		}
		
		WebsiteVisitBean websiteVisitBean = new WebsiteVisitBean();
		
		websiteVisitBean.setPage("Home");
		for (int i = 0; i < 50; i++) {
			websiteVisitBean.setDate(new Date(System.currentTimeMillis() - i*8640*1000));
			websiteVisitDAO.insert(websiteVisitBean);
		}

		websiteVisitBean.setPage("Flick&Map");
		for (int i = 0; i < 100; i++) {
			websiteVisitBean.setDate(new Date(System.currentTimeMillis() - i*4640*1000));
			websiteVisitDAO.insert(websiteVisitBean);
		}

		websiteVisitBean.setPage("Search&Tweet");
		for (int i = 0; i < 70; i++) {
			websiteVisitBean.setDate(new Date(System.currentTimeMillis() - i*6400*1000));
			websiteVisitDAO.insert(websiteVisitBean);
		}
		
		websiteVisitBean.setPage("User History");
		for (int i = 0; i < 40; i++) {
			websiteVisitBean.setDate(new Date(System.currentTimeMillis() - i*10640*1000));
			websiteVisitDAO.insert(websiteVisitBean);
		}
		
		websiteVisitBean.setPage("Statistics");
		for (int i = 0; i < 50; i++) {
			websiteVisitBean.setDate(new Date(System.currentTimeMillis() - i*7000*1000));
			websiteVisitDAO.insert(websiteVisitBean);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.contains("://")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("jsp/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
