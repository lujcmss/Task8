package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import task8.databeans.WebsiteVisitBean;
import task8.model.Model;
import task8.model.WebsiteVisitDAO;

public class Home extends Action {
	
	private String myAccessToken = "155593232-KEVzHH7BIv8zQ4t8Xvk5xuiTzUXFsv2mMIFKQMyF";
	private String myAccessTokenSecret = "d88KpbVh4rgMuCLjbcxhw6EpCUDCBROqpGNAZvU8yDoQI";
	
	private WebsiteVisitDAO websiteVisitDAO;

	public Home(Model model) {
		websiteVisitDAO = model.getWebsiteVisitDAO();
	}

	public String getName() {
		return "home.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "home.do");
		WebsiteVisitBean websiteVisitBean = new WebsiteVisitBean();
		websiteVisitBean.setPage("Home");
		websiteVisitBean.setDate(new Date(System.currentTimeMillis()));
		websiteVisitDAO.insert(websiteVisitBean);

		try {
			Token apponlyAccessToken = new Token(myAccessToken, myAccessTokenSecret);
			session.setAttribute("apponlyAccessToken", apponlyAccessToken);
			
			List<String> pages = new ArrayList<String>();
			pages.add("Home"); pages.add("Flick&Map"); pages.add("Search&Tweet");
			pages.add("Popular Pictures"); pages.add("Statistics");
			session.setAttribute("pages", pages);
			
			return "home.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "home.jsp";
		}
	}
}
