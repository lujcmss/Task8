package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

import task8.databeans.UserBean;
import task8.formbeans.LoginForm;
import task8.formbeans.CommentFlickerForm;
import task8.model.Twitter;
import task8.model.UserDAO;
import task8.model.Model;

public class Login extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);

	private UserDAO userDAO;

	public Login(Model model) {
		userDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "login.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();

		try {
			if (errors.size() != 0) {
				return "login.do";
			}
			
			Twitter twitter = Twitter.getTwitter();
			Token requestToken = twitter.getRequestToken();
			session.setAttribute("requestToken", requestToken);
			
			String url = twitter.getURL(requestToken);
			request.setAttribute("TwitterRedirect", url);
			return "login.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "login.jsp";
		}
	}
}
