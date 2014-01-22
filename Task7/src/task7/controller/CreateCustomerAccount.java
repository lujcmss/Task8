package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.formbeans.CreateCustomerForm;
import task7.formbeans.CustomerLoginForm;
import task7.model.Model;

public class CreateCustomerAccount extends Action {
	private FormBeanFactory<CreateCustomerForm> formBeanFactory = FormBeanFactory.getInstance(CreateCustomerForm.class);

	public CreateCustomerAccount(Model model) {
	}

	public String getName() { return "createCustomerAccount.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession(true);
        
        
        
        
        
        
		try {
			CreateCustomerForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "createCustomerAccount.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		        if (errors.size() != 0) {
		            return "createCustomerAccount.jsp";
		        }
			
			
			
			
			
			// check for errors
			return "createCustomerAccount.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
