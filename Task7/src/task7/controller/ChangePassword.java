package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.formbeans.ChangePasswordForm;
import task7.model.CustomerDAO;
import task7.model.EmployeeDAO;
import task7.model.Model;

public class ChangePassword extends Action {
	private FormBeanFactory<ChangePasswordForm> formBeanFactory = FormBeanFactory.getInstance(ChangePasswordForm.class);
	
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	
	public ChangePassword(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "changePassword.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        
		try {
			ChangePasswordForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "changePassword.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		    if (errors.size() != 0) {
		        return "changePassword.jsp";
		    }
		    
		    String userType = (String) session.getAttribute("userType");
			if (userType.equals("Employee")) {
				EmployeeBean employeeBean = employeeDAO.getEmployeeByEmail((String)session.getAttribute("email"));
				if (!employeeBean.getPassword().equals(form.getOldpsw())) {
					errors.add("Wrong Password!");
				}
				
				employeeBean.setPassword(form.getNewpsw());
				employeeDAO.update(employeeBean);
			} else {
			    CustomerBean customerBean = customerDAO.getCustomerByEmail((String)session.getAttribute("email"));
			    if (!customerBean.getPassword().equals(form.getOldpsw())) {
			    	errors.add("Wrong password!");
			    	return "changePassword.jsp";
			    }
			    
			    customerBean.setPassword(form.getNewpsw());
			    customerDAO.update(customerBean);
			}
			return "home.do";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "changePassword.jsp";
        }
    }
}

