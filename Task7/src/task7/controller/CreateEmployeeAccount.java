package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.EmployeeBean;
import task7.formbeans.CreateEmployeeForm;
import task7.model.EmployeeDAO;
import task7.model.Model;

public class CreateEmployeeAccount extends Action {
	private FormBeanFactory<CreateEmployeeForm> formBeanFactory = FormBeanFactory.getInstance(CreateEmployeeForm.class);
	
	private EmployeeDAO employeeDAO;
	
	public CreateEmployeeAccount(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "createEmployeeAccount.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession(true);
        session.setAttribute("curPage", "manageAccounts.do");
        
		try {
			CreateEmployeeForm form = formBeanFactory.create(request);
			session.setAttribute("form", form);
			if (!form.isPresent()) {
	            return "createEmployeeAccount.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
			
		    if (employeeDAO.hasEmployee(form.getEmail()) == true) {
		    	errors.add("Email already been used.");
		    }
		    
		    if (errors.size() != 0) {
		        return "createEmployeeAccount.jsp";
		    }
		    
		    synchronized (employeeDAO) {
			    EmployeeBean employeeBean = new EmployeeBean();
			    employeeBean.setEmail(form.getEmail());
			    employeeBean.setFirstName(form.getFirstName());
			    employeeBean.setLastName(form.getLastName());
			    employeeBean.setPassword(form.getPsw());
			    employeeDAO.insert(employeeBean);
			}
		    
			return "home.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "createEmployeeAccount.jsp";
        }
    }
}
