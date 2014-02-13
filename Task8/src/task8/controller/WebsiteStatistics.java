package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task8.databeans.CommentGraphBean;
import task8.databeans.WebsiteVisitBean;
import task8.model.CommentHistoryDAO;
import task8.model.Model;
import task8.model.WebsiteVisitDAO;

public class WebsiteStatistics extends Action {
	private WebsiteVisitDAO websiteVisitDAO;
	private CommentHistoryDAO commentHistoryDAO;

	public WebsiteStatistics(Model model) {
		websiteVisitDAO = model.getWebsiteVisitDAO();
		commentHistoryDAO = model.getCommentHistoryDAO();
	}

	public String getName() {
		return "websiteStatistics.do";
	}

	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "websiteStatistics.do");
		WebsiteVisitBean websiteVisitBean = new WebsiteVisitBean();
		websiteVisitBean.setPage("Statistics");
		websiteVisitBean.setDate(new Date(System.currentTimeMillis()));
		websiteVisitDAO.insert(websiteVisitBean);
		
		try {
			Date date = new Date(System.currentTimeMillis() - 10*86400*1000);
			WebsiteVisitBean[] websiteVisitBeans = websiteVisitDAO.getVisitBefore(date);
			
			List<String> dates = new ArrayList<String>();
			for (int i = 0; i < websiteVisitBeans.length; i++) {
				date = websiteVisitBeans[i].getDate();
				String dateString = date.toString().substring(0, 10);
				if (!dates.contains(dateString)) {
					int index = 0;
					while (index < dates.size() && dateString.compareTo(dates.get(index)) > 0) {
						index++;
					}
					dates.add(index, dateString);
				}
			}
			request.setAttribute("dates", dates);
			
			List<String> pages = (List<String>) session.getAttribute("pages");
			
			List<Integer> visits = new ArrayList<Integer>();
			int[][] tmpVisit = new int[dates.size()][pages.size()];
			for (int i = 0; i < websiteVisitBeans.length; i++) {
				date = websiteVisitBeans[i].getDate();
				String dateString = date.toString().substring(0, 10);
				tmpVisit[dates.indexOf(dateString)][pages.indexOf(websiteVisitBeans[i].getPage())] += 1;
			}
			for (int i = 0; i < dates.size(); i++) {
				for (int j = 0; j < pages.size(); j++) {
					visits.add(tmpVisit[i][j]);
				}
			}
			request.setAttribute("visits", visits);
			
			List<CommentGraphBean> commentGraphBeans = commentHistoryDAO.getTopComments(5);
			request.setAttribute("commentGraph", commentGraphBeans);
			
			return "websiteStatistics.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "websiteStatistics.jsp";
		}
	}
}
