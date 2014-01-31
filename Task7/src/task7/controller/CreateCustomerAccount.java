package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.formbeans.CreateCustomerForm;
import task7.model.CustomerDAO;
import task7.model.Model;

public class CreateCustomerAccount extends Action {
	private FormBeanFactory<CreateCustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateCustomerForm.class);

	private CustomerDAO customerDAO;

	public CreateCustomerAccount(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "createCustomerAccount.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(true);
		session.setAttribute("curPage", "manageAccounts.do");

		try {
			String userType = (String) session.getAttribute("userType");
			if (!userType.equals("Employee")) {
				return "logout.do";
			}
			CreateCustomerForm form = formBeanFactory.create(request);
			session.setAttribute("form", form);

			if (!form.isPresent()) {
				return "createCustomerAccount.jsp";
			}

			errors.addAll(form.getValidationErrors());

			synchronized (customerDAO) {
				if (customerDAO.hasCustomer((String) form.getEmail()) == true) {
					errors.add("Username already been used.");
				}
	
				if (errors.size() != 0) {
					return "createCustomerAccount.jsp";
				}

				CustomerBean customerBean = new CustomerBean();
				customerBean.setAddr1(form.getAddr1());
				customerBean.setAddr2(form.getAddr2());
				customerBean.setCash(0);
				customerBean.setCity(form.getCity());
				customerBean.setCustomerEmail(form.getEmail());
				customerBean.setFirstName(form.getFirstName());
				customerBean.setLastName(form.getLastName());
				customerBean.setPassword(form.getPsw());
				customerBean.setState(form.getState());
				customerBean.setZip(form.getZipCode());
				customerDAO.insert(customerBean);
			}

			return "home.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "createCustomerAccount.jsp";
		}
	}
}
