package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;

import task8.databeans.WebsiteVisitBean;
import task8.formbeans.ViewFlickerForm;
import task8.model.Twitter;
import task8.model.Model;
import task8.model.WebsiteVisitDAO;

public class ViewFlickr extends Action {
	private FormBeanFactory<ViewFlickerForm> formBeanFactory = FormBeanFactory
			.getInstance(ViewFlickerForm.class);

	private WebsiteVisitDAO websiteVisitDAO;

	public ViewFlickr(Model model) {
		websiteVisitDAO = model.getWebsiteVisitDAO();
	}

	public String getName() {
		return "viewFlickr.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "viewFlickr.do");
		WebsiteVisitBean websiteVisitBean = new WebsiteVisitBean();
		websiteVisitBean.setPage("Flick&Map");
		websiteVisitBean.setDate(new Date(System.currentTimeMillis()));
		websiteVisitDAO.insert(websiteVisitBean);

		try {
			ViewFlickerForm form = formBeanFactory.create(request);

			String searchTagReturn = null;
			if (!form.isPresent() || form.getButton() == null
					|| form.getTags().equals("")) {
				Twitter twitter = Twitter.getTwitter();
				Token apponlyAccessToken = (Token) session
						.getAttribute("apponlyAccessToken");
				searchTagReturn = twitter.getPopularTags(apponlyAccessToken);
			} else {
				searchTagReturn = form.getTags();
			}

			request.setAttribute("SearchTagReturn", searchTagReturn);
			return "viewFlickr.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "viewFlickr.jsp";
		}
	}
}
