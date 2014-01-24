package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.TransactionBean;
import task7.formbeans.DepositForm;
import task7.formbeans.RequestForm;
import task7.model.CustomerDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class RequestCheck extends Action {
	private FormBeanFactory<RequestForm> formBeanFactory = FormBeanFactory.getInstance(RequestForm.class);
	
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	
	public RequestCheck(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "requestCheck.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
		try {
			RequestForm form = formBeanFactory.create(request);
			CustomerBean customerBean = (CustomerBean) session.getAttribute("user"); 

			if (!form.isPresent()) {
	            return "requestCheck.jsp";
	        }

			errors.addAll(form.getValidationErrors());
			
			if (form.getRequestAmount() * 100 > customerBean.getCash()) {
				errors.add("No enough Money.");
			}
			
		    if (errors.size() != 0) {
		        return "requestCheck.jsp";
		    }
		    customerBean.setCash(customerBean.getCash() - (long)(form.getRequestAmount() * 100));
		    customerDAO.update(customerBean);
		    
		    TransactionBean transactionBean = new TransactionBean();
		    transactionBean.setAmount((long)(form.getRequestAmount() * 100));
		    transactionBean.setCustomerBean(customerBean);
		    transactionBean.setTransactionType("Request");
		    transactionBean.setPending(true);
		    transactionDAO.insert(transactionBean);
			// check for errors
			return "requestCheck.jsp";
        } catch (Exception e) {
        	System.out.println(e);
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
