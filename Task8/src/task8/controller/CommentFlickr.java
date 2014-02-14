package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;

import task8.databeans.CommentBean;
import task8.databeans.UserBean;
import task8.databeans.WebsiteVisitBean;
import task8.formbeans.CommentFlickerForm;
import task8.model.CommentHistoryDAO;
import task8.model.Twitter;
import task8.model.TwitterEncoder;
import task8.model.Model;
import task8.model.WebsiteVisitDAO;

public class CommentFlickr extends Action {
	private FormBeanFactory<CommentFlickerForm> formBeanFactory = FormBeanFactory
			.getInstance(CommentFlickerForm.class);

	private WebsiteVisitDAO websiteVisitDAO;
	private CommentHistoryDAO commentHistoryDAO;

	public CommentFlickr(Model model) {
		commentHistoryDAO = model.getCommentHistoryDAO();
		websiteVisitDAO = model.getWebsiteVisitDAO();
	}

	public String getName() {
		return "commentFlickr.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		List<String> success = new ArrayList<String>();
		request.setAttribute("success", success);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "commentFlickr.do");
		WebsiteVisitBean websiteVisitBean = new WebsiteVisitBean();
		websiteVisitBean.setPage("Search&Tweet");
		websiteVisitBean.setDate(new Date(System.currentTimeMillis()));
		websiteVisitDAO.insert(websiteVisitBean);

		try {
			CommentFlickerForm form = formBeanFactory.create(request);

			String searchTagReturn = (String) session
					.getAttribute("SearchTagReturn");
			if (!form.isPresent()
					|| form.getButton() == null
					|| (form.getButton().equals("Search Tags") && form
							.getTags() == null)) {
				/*
				Twitter twitter = Twitter.getTwitter();
				Token accessToken = (Token) session.getAttribute("apponlyAccessToken");
				searchTagReturn = twitter.getPopularTags(accessToken);
				*/
				if (searchTagReturn != null) {
					searchTagReturn = "Pittsburgh"; 
				}
			} else if (form.getButton().equals("Search Tags")) {
				searchTagReturn = form.getTags();
			} else {
				Token accessToken = (Token) session.getAttribute("accessToken");

				if (accessToken == null) {
					return "login.do";
				}

				CommentBean commentBean = new CommentBean();
				commentBean.setComment(form.getComment());
				commentBean
						.setUserBean((UserBean) session.getAttribute("user"));
				commentBean.setImageSource(form.getImageSource());
				commentBean.setImageSourceOri(form.getImageSourceOri());
				commentBean.setDate(new Date(System.currentTimeMillis()));
				commentHistoryDAO.insert(commentBean);

				Twitter twitter = Twitter.getTwitter();
				String text = TwitterEncoder.encode(form.getComment() + " "
						+ form.getImageSource());
				twitter.sendTwitter(accessToken, text);

				success.add("Successfully commented to Twitter!");
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
