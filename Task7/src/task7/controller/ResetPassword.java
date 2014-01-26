package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.formbeans.ResetPasswordForm;
import task7.model.CustomerDAO;
import task7.model.Model;

public class ResetPassword extends Action {
	private FormBeanFactory<ResetPasswordForm> formBeanFactory = FormBeanFactory.getInstance(ResetPasswordForm.class);
	
	private CustomerDAO customerDAO;
	
	public ResetPassword(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "resetPassword.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        
		try {
			ResetPasswordForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "resetPassword.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		    if (errors.size() != 0) {
		        return "resetPassword.jsp";
		    }
		    
		    CustomerBean customerBean = (CustomerBean)session.getAttribute("userInfo");
		    customerBean.setPassword(form.getNewpsw());
		    customerDAO.update(customerBean);
			
			return "viewCustomerInformation.do";
        } catch (Exception e) {
        	return "resetPassword.jsp";
        }
    }
}

