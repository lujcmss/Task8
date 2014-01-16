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

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.Model;

public class LoginAction extends Action {

	public LoginAction(Model model) {
	}

	public String getName() { return "login.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession(true);
		try {
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
