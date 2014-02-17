package task8.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;

import task8.databeans.CommentBean;
import task8.databeans.DislikeBean;
import task8.databeans.LikeBean;
import task8.databeans.UserBean;
import task8.databeans.WebsiteVisitBean;
import task8.databeans.TopBean;
import task8.formbeans.CommentFlickerForm;
import task8.model.CommentHistoryDAO;
import task8.model.DislikeDAO;
import task8.model.LikeDAO;
import task8.model.Model;
import task8.model.Twitter;
import task8.model.TwitterEncoder;
import task8.model.WebsiteVisitDAO;

public class CommentHistory extends Action {
	private FormBeanFactory<CommentFlickerForm> formBeanFactory = FormBeanFactory
			.getInstance(CommentFlickerForm.class);

	private LikeDAO likeDAO;
	private DislikeDAO dislikeDAO;
	private WebsiteVisitDAO websiteVisitDAO;
	private CommentHistoryDAO commentHistoryDAO;

	public CommentHistory(Model model) {
		likeDAO = model.getLikeDAO();
		dislikeDAO = model.getDislikeDAO();
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
		List<String> success = new ArrayList<String>();
		request.setAttribute("success", success);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "commentHistory.do");
		WebsiteVisitBean websiteVisitBean = new WebsiteVisitBean();
		websiteVisitBean.setPage("Popular Pictures");
		websiteVisitBean.setDate(new Date(System.currentTimeMillis()));
		websiteVisitDAO.insert(websiteVisitBean);

		try {
			CommentFlickerForm form = formBeanFactory.create(request);

			List<TopBean> topBeans = null;
			String page = (String) session.getAttribute("page");
			if (form.isPresent() && form.getButton() != null) {

				if (form.getButton().equals("mostComment")) {
					topBeans = commentHistoryDAO.getTopPictures(10);
					session.setAttribute("page", "mostComment");
				} else if (form.getButton().equals("mostLike")) {
					topBeans = likeDAO.getTopLikes(10);
					session.setAttribute("page", "mostLike");
				} else if (form.getButton().equals("mostDislike")) {
					topBeans = dislikeDAO.getTopDislikes(10);
					session.setAttribute("page", "mostDislike");
				} else {
					Token accessToken = (Token) session
							.getAttribute("accessToken");

					if (accessToken == null) {
						return "login.do";
					}

					if (page.equals("mostLike")) {
						topBeans = likeDAO.getTopLikes(10);
					} else if (page.equals("mostDislike")) {
						topBeans = dislikeDAO.getTopDislikes(10);
					} else {
						topBeans = commentHistoryDAO.getTopPictures(10);
					}
					
					if (form.getButton().equals("Comment")) {
						CommentBean commentBean = new CommentBean();
						commentBean.setComment(form.getComment());
						commentBean.setUserBean((UserBean) session
								.getAttribute("user"));
						commentBean.setPhotoId(form.getPhotoId());
						commentBean.setTitle(form.getTitle());
						commentBean.setImageSource(form.getImageSource());
						commentBean.setImageSourceOri(form.getImageSourceOri());
						commentBean
								.setDate(new Date(System.currentTimeMillis()));
						commentHistoryDAO.insert(commentBean);

						Twitter twitter = Twitter.getTwitter();
						String text = TwitterEncoder.encode(form.getComment()
								+ " " + form.getImageSource());
						twitter.sendTwitter(accessToken, text);

						success.add("Successfully commented to Twitter!");
					} else if (form.getButton().equals("Like")) {
						LikeBean likeBean = new LikeBean();
						likeBean.setImageSource(form.getImageSource());
						likeBean.setImageSourceOri(form.getImageSourceOri());
						likeBean.setPhotoId(form.getPhotoId());
						likeBean.setTitle(form.getTitle());
						likeBean.setUserBean((UserBean) session
								.getAttribute("user"));
						likeDAO.insert(likeBean);
						success.add("Successed to like!");
					} else { // Dislike
						DislikeBean dislikeBean = new DislikeBean();
						dislikeBean.setImageSource(form.getImageSource());
						dislikeBean.setImageSourceOri(form.getImageSourceOri());
						dislikeBean.setPhotoId(form.getPhotoId());
						dislikeBean.setTitle(form.getTitle());
						dislikeBean.setUserBean((UserBean) session
								.getAttribute("user"));
						dislikeDAO.insert(dislikeBean);
						success.add("Successed to dislike!");
					}
				}
			} else {
				topBeans = commentHistoryDAO.getTopPictures(10);
				session.setAttribute("page", "mostComment");
			}

			session.setAttribute("topBean", topBeans);
			return "commentHistory.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			System.out.println(e);
			return "commentHistory.jsp";
		}
	}
}
