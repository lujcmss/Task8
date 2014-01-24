package task7.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.databeans.FundBean;
import task7.formbeans.CustomerLoginForm;
import task7.model.CustomerDAO;
import task7.model.EmployeeDAO;
import task7.model.FundDAO;
import task7.model.Model;

public class Login extends Action {
	private FormBeanFactory<CustomerLoginForm> formBeanFactory = FormBeanFactory.getInstance(CustomerLoginForm.class);
	
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	
	public Login(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "login.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        
		try {
			//Testing login form bean, by Patrick
			CustomerLoginForm form = formBeanFactory.create(request);
			session.setAttribute("form", form);
			
			if (!form.isPresent()) {
	            return "login.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		        if (errors.size() != 0) {
		            return "login.jsp";
		        }
		    
		    if (form.getUserType().equals("")) {
		    	errors.add("Please select user type.");
		    	return "login.jsp";
		    }

		    if (form.getUserType().equals("Employee")) {
		    	EmployeeBean employeeBean = employeeDAO.getEmployeeByEmail(form.getEmail());
		    	if (employeeBean == null) {
		    		errors.add("No such Employee");
		    		return "login.jsp";
		    	}
		        if (!employeeBean.getPassword().equals(form.getPsw())) {
		            errors.add("Incorrect password");
		            return "login.jsp";
		        }
		    } else {
		    	CustomerBean customerBean = customerDAO.getCustomerByEmail((String)form.getEmail());
		    	if (customerBean == null) {
		    		errors.add("No such Customer");
		    		return "login.jsp";
		    	}
		        if (!customerBean.getPassword().equals(form.getPsw())) {
		            errors.add("Incorrect password");
		            return "login.jsp";
		        }	    	
		    }
		    
	        session.setAttribute("userType", form.getUserType());
	        session.setAttribute("email", form.getEmail());
	        session.setAttribute("date", new Date(100000000));
		    
			return "home.do";
        } catch (Exception e) {
        	errors.add("Please reload website");
        	System.out.println(errors);
        	return "login.jsp";
        }
    }
}
