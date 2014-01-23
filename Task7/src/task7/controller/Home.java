package task7.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.Model;

public class Home extends Action {
	
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	
	public Home(Model model) {
	}

	public String getName() { return "home.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        
		try {
			FundBean[] fundBeans = fundDAO.getAllFunds();
			
			session.setAttribute("allFunds", fundBeans);
			
	        return "home.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
