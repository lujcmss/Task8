package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.TransactionBean;
import task7.formbeans.DepositForm;
import task7.model.CustomerDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class DepositCheck extends Action {
	private FormBeanFactory<DepositForm> formBeanFactory = FormBeanFactory
			.getInstance(DepositForm.class);

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public DepositCheck(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "depositCheck.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		List<String> success = new ArrayList<String>();
		request.setAttribute("success", success);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "depositCheck.do");

		try {
			DepositForm form = formBeanFactory.create(request);

			if (!form.isPresent()) {
				return "depositCheck.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "depositCheck.jsp";
			}

			if (form.getButton() == null || form.getButton().equals("search")) {
				if (form.getCustomerEmail() != null
						&& !form.getCustomerEmail().equals("")) {
					CustomerBean customerBean = null;
					if (customerDAO.getCustomerByEmail(form.getCustomerEmail()) != null) {
						customerBean = customerDAO.getCustomerByEmail(form
								.getCustomerEmail());
					}

					if (customerBean == null) {
						errors.add("No customer Matches.");
						session.setAttribute("customerList", null);
						return "depositCheck.jsp";
					}

					CustomerBean[] customerListBean = new CustomerBean[1];
					customerListBean[0] = new CustomerBean();
					customerListBean[0].setCustomerEmail(customerBean
							.getEmail());
					;
					customerListBean[0].setFirstName(customerBean
							.getFirstName());
					customerListBean[0].setLastName(customerBean.getLastName());

					session.setAttribute("customerList", customerListBean);
				} else {
					CustomerBean[] customerBeans = customerDAO
							.getAllCustomers();
					CustomerBean[] customerListBean = new CustomerBean[customerBeans.length];

					for (int i = 0; i < customerBeans.length; i++) {
						customerListBean[i] = new CustomerBean();
						customerListBean[i].setCustomerEmail(customerBeans[i]
								.getEmail());
						;
						customerListBean[i].setFirstName(customerBeans[i]
								.getFirstName());
						customerListBean[i].setLastName(customerBeans[i]
								.getLastName());
					}

					session.setAttribute("customerList", customerListBean);
				}
			} else {
				long amount = (long) (form.getDepositAmount() * 100);
				TransactionBean transactionBean = new TransactionBean();
				transactionBean.setAmount(amount);
				transactionBean.setCustomerBean(customerDAO
						.getCustomerByEmail(form.getCustomerEmail()));
				transactionBean.setTransactionType("Deposit");
				transactionBean.setStatus("Pending");
				transactionDAO.insert(transactionBean);
				success.add(form.getCustomerEmail() + " Got a deposit for $" + form.getDepositAmount());
			}

			return "depositCheck.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "depositCheck.jsp";
		}
	}
}
