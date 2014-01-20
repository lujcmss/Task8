package task7.controller;

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
import task7.databeans.FundBean;
import task7.formbeans.CustomerLoginForm;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.Model;

public class Login extends Action {
	private FormBeanFactory<CustomerLoginForm> formBeanFactory = FormBeanFactory.getInstance(CustomerLoginForm.class);
	public Login(Model model) {
	}

	public String getName() { return "login.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession(true);
        System.out.println("passLogin");
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
			//This test was made by Trey to test the create dao of a fund when page loads
			
			
			FundBean fundBean = new FundBean();
			fundBean.setName("Apple");
			fundBean.setSymbol("APPL");
			FundDAO fundDAO = new FundDAO();
			fundDAO.insert(fundBean);
			
			// check for errors
			return "login.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	System.out.println(errors);
        	return "error.jsp";
        }
    }
}
