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
			
			if (!form.isPresent()) {
	            return "login.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		        if (errors.size() != 0) {
		            return "login.jsp";
		        }
		    
		    if (form.getUserType() == "") {
		    	errors.add("Please select user type.");
		    	return "login.jsp";
		    }
		    
		    if (form.getUserType() == "Employee") {
		    	EmployeeBean employeeBean = employeeDAO.getEmployee(form.getEmail());
		    	if (employeeBean == null) {
		    		errors.add("No such Employee");
		    		return "login.jsp";
		    	}
		        if (!employeeBean.getPassword().equals(md5(form.getPsw()))) {
		            errors.add("Incorrect password");
		            return "login.jsp";
		        }
		    } else {
		    	CustomerBean customerBean = customerDAO.getCustomer(form.getEmail());
		    	if (customerBean == null) {
		    		errors.add("No such Customer");
		    		return "login.jsp";
		    	}
		        if (!customerBean.getPassword().equals(md5(form.getPsw()))) {
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
	
	private String md5(String org) {
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		    md.update(org.getBytes(), 0, org.length());
		    String re = new BigInteger(1, md.digest()).toString(16);
		    return re;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    return null;
	}
}
