package task8.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

import task8.formbeans.LoginForm;
import task8.formbeans.TwitterCallbackForm;
import task8.model.Model;
import task8.model.Twitter;
import task8.model.UserDAO;

public class TwitterCallback extends Action {
	private FormBeanFactory<TwitterCallbackForm> formBeanFactory = FormBeanFactory
			.getInstance(TwitterCallbackForm.class);

	private UserDAO userDAO;

	public TwitterCallback(Model model) {
		userDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "twitterCallback.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();

		try {
			TwitterCallbackForm form = formBeanFactory.create(request);
			
			Twitter twitter = Twitter.getTwitter();
			Token requestToken = (Token) session.getAttribute("requestToken");
			//System.out.println(requestToken);
			
			if (!requestToken.getToken().equals(form.getOauth_token())) {
				errors.add("Invalid Request Token");
				return "login.jsp";
			}
			
			Verifier verifier = new Verifier(form.getOauth_verifier());
			Token accessToken = twitter.getAccessToken(requestToken, verifier);
			//session.setAttribute("requestToken", null);
			
			session.setAttribute("accessToken", accessToken);
			return "commentFlickr.do";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "login.jsp";
		}
	}
}