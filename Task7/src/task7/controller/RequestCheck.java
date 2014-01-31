package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.TransactionBean;
import task7.formbeans.RequestForm;
import task7.model.CustomerDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class RequestCheck extends Action {
	private FormBeanFactory<RequestForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestForm.class);

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public RequestCheck(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "requestCheck.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		List<String> success = new ArrayList<String>();
		request.setAttribute("success", success);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "requestCheck.do");

		try {
			String userType = (String) session.getAttribute("userType");
			if (!userType.equals("Customer")) {
				return "logout.do";
			}
			RequestForm form = formBeanFactory.create(request);
			CustomerBean customerBean = (CustomerBean) session
					.getAttribute("user");
			session.setAttribute("user",
					customerDAO.getCustomerByEmail(customerBean.getEmail()));

			if (!form.isPresent()) {
				return "requestCheck.jsp";
			}

			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "requestCheck.jsp";
			}

			long amount = (long) (form.getRequestAmount() * 100);

			synchronized (customerDAO) {
				long cash = customerDAO.getCustomerByEmail(
						customerBean.getEmail()).getCash();
				if (amount > cash) {
					errors.add("No enough Money.");
					return "requestCheck.jsp";
				}
				customerBean.setCash(cash - amount);
				customerDAO.update(customerBean);
			}

			TransactionBean transactionBean = new TransactionBean();
			transactionBean.setAmount(amount);
			transactionBean.setCustomerBean(customerBean);
			transactionBean.setTransactionType("Request");
			transactionBean.setStatus("Pending");
			transactionDAO.insert(transactionBean);
			success.add(" $"+form.getRequestAmount()+" has been requested for withdraw");
			session.setAttribute("user",
					customerDAO.getCustomerByEmail(customerBean.getEmail()));
			return "requestCheck.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "requestCheck.jsp";
		}
	}
}
