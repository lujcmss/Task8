package task8.controller;

import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

import task8.formbeans.CommentFlickerForm;
import task8.model.Twitter;
import task8.model.TwitterEncoder;
import task8.model.UserDAO;
import task8.model.Model;

public class CommentFlickr extends Action {
	private FormBeanFactory<CommentFlickerForm> formBeanFactory = FormBeanFactory
			.getInstance(CommentFlickerForm.class);
	private UserDAO userDAO;

	public CommentFlickr(Model model) {
		userDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "commentFlickr.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "commentFlickr.do");

		try {
			CommentFlickerForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
				return "commentFlickr.jsp";
			}
			
			String searchTagReturn = (String) session.getAttribute("SearchTagReturn");
			if (form.getButton() == null || (form.getButton().equals("Search Tags") && form.getTags() == null)) {
				searchTagReturn = "great wall";
			} else if (form.getButton().equals("Search Tags")) {
				searchTagReturn = form.getTags();
			} else {
				Token accessToken = (Token) session.getAttribute("accessToken");

				if (accessToken == null) {
					errors.add("No access Token");
					return "login.do";
				}
				
				Twitter twitter = Twitter.getTwitter();
				String text = TwitterEncoder.encode(form.getComment() + " " + form.getImageSource());
				
				twitter.sendTwitter(accessToken, text);
			}
			
			session.setAttribute("SearchTagReturn", searchTagReturn);
			return "commentFlickr.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "commentFlickr.jsp";
		}
	}
}
