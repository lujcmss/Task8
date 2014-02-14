package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task8.databeans.WebsiteVisitBean;
import task8.formbeans.ViewFlickerForm;
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

			String mapTag = null;
			if (!form.isPresent() || form.getButton() == null) {
				/*
				Twitter twitter = Twitter.getTwitter();
				Token accessToken = (Token) session.getAttribute("apponlyAccessToken");
				searchTagReturn = twitter.getPopularTags(accessToken);
				*/
				if (mapTag == null || mapTag.equals("")) {
					mapTag = "Restaurant";
				}
			} else if (form.getValidationErrors().size() != 0) {
				errors = form.getValidationErrors();
				return "viewFlickr.jsp";
			} else {
				mapTag = form.getTags();
				session.setAttribute("lat", form.getLat());
				session.setAttribute("lon", form.getLon());
			}

			request.setAttribute("mapTag", mapTag);
			return "viewFlickr.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "viewFlickr.jsp";
		}
	}
}
