package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.TransactionBean;
import task7.formbeans.LoginForm;
import task7.formbeans.DepositForm;
import task7.model.CustomerDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class DepositCheck extends Action {
	private FormBeanFactory<DepositForm> formBeanFactory = FormBeanFactory.getInstance(DepositForm.class);
	
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	
	public DepositCheck(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "depositCheck.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
      
		try {
			DepositForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "depositCheck.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		    if (errors.size() != 0) {
		        return "depositCheck.jsp";
		    }
		    
		    TransactionBean transactionBean = new TransactionBean();
		    transactionBean.setAmount((long)(form.getDepositAmount() * 100));
		    transactionBean.setCustomerBean(customerDAO.getCustomerByEmail((String)form.getUsr()));
		    transactionBean.setTransactionType("deposit");
		    transactionDAO.insert(transactionBean);
		    
	        return "depositCheck.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "depositCheck.jsp";
        }
    }
}
