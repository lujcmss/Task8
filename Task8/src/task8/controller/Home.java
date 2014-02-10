package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import task8.databeans.UserBean;
import task8.model.UserDAO;
import task8.model.Model;

public class Home extends Action {
	
	private String myAccessToken = "155593232-KEVzHH7BIv8zQ4t8Xvk5xuiTzUXFsv2mMIFKQMyF";
	private String myAccessTokenSecret = "d88KpbVh4rgMuCLjbcxhw6EpCUDCBROqpGNAZvU8yDoQI";
	
	private UserDAO userDAO;

	public Home(Model model) {
		userDAO = model.getCustomerDAO();
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

		Token apponlyAccessToken = new Token(myAccessToken, myAccessTokenSecret);
		session.setAttribute("apponlyAccessToken", apponlyAccessToken);
		try {
			if (errors.size() != 0) {
				return "home.do";
			}
			
			return "home.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "home.jsp";
		}
	}
}
