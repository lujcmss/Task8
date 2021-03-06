package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.FundBean;
import task7.formbeans.CreateFundForm;
import task7.model.FundDAO;
import task7.model.Model;

public class CreateFund extends Action {

	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateFundForm.class);

	private FundDAO fundDAO;

	public CreateFund(Model model) {
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "createFund.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		List<String> success = new ArrayList<String>();
		request.setAttribute("success", success);
		HttpSession session = request.getSession();
		session.setAttribute("funds", fundDAO.getAllFunds());
		session.setAttribute("curPage", "createFund.do");

		try {
			String userType = (String) session.getAttribute("userType");
			if (!userType.equals("Employee")) {
				return "logout.do";
			}
			CreateFundForm form = formBeanFactory.create(request);

			if (!form.isPresent()) {
				return "createFund.jsp";
			}

			errors.addAll(form.getValidationErrors());

			synchronized (fundDAO) {
				if (fundDAO.hasFund(form.getFund()) == true
						|| fundDAO.hasTicker(form.getTicker()) == true) {
					errors.add("Fund already exist.");
				}
	
				if (errors.size() != 0) {
					return "createFund.jsp";
				}

				FundBean fundBean = new FundBean();
				fundBean.setName(form.getFund());
				fundBean.setSymbol(form.getTicker());
				fundDAO.insert(fundBean);
				success.add(form.getFund()+ " Created!");
			}
			
			session.setAttribute("funds", fundDAO.getAllFunds());
			return "createFund.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "createFund.jsp";
		}
	}
}
