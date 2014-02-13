package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import task8.databeans.CommentBean;
import task8.databeans.UserBean;
import task8.databeans.WebsiteVisitBean;
import task8.model.CommentHistoryDAO;
import task8.model.Model;
import task8.model.WebsiteVisitDAO;

public class CommentHistory extends Action {
	private WebsiteVisitDAO websiteVisitDAO;
	private CommentHistoryDAO commentHistoryDAO;

	public CommentHistory(Model model) {
		websiteVisitDAO = model.getWebsiteVisitDAO();
		commentHistoryDAO = model.getCommentHistoryDAO();
	}

	public String getName() {
		return "commentHistory.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "commentHistory.do");
		WebsiteVisitBean websiteVisitBean = new WebsiteVisitBean();
		websiteVisitBean.setPage("User History");
		websiteVisitBean.setDate(new Date(System.currentTimeMillis()));
		websiteVisitDAO.insert(websiteVisitBean);

		try {
			Token accessToken = (Token) session.getAttribute("accessToken");

			if (accessToken == null) {
				return "login.do";
			}
			
			UserBean userBean = (UserBean) session.getAttribute("user");
			CommentBean[] commentBeans = commentHistoryDAO.getCommentsByUser(userBean);
			request.setAttribute("commentHistory", commentBeans);
			return "commentHistory.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "commentHistory.jsp";
		}
	}
}
