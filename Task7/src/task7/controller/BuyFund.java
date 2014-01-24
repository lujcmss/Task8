package task7.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.PositionBean;
import task7.databeans.TransactionBean;
import task7.formbeans.BuyForm;
import task7.formbeans.CustomerLoginForm;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.Model;
import task7.model.PositionDAO;
import task7.model.TransactionDAO;

public class BuyFund extends Action{
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory.getInstance(BuyForm.class);
	
	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public BuyFund(Model model) {
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() { return "buyFund.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();

		try {
			BuyForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
					
	   	    if (!form.isPresent()) {
		        return "buyFund.jsp";
			}		
			
	   	    errors.addAll(form.getValidationErrors());
		    if (errors.size() != 0) {
		        return "buyFund.jsp";
		    }

		    if (form.getButton() == "search") {
		    	FundBean fundBean = fundDAO.getFundByName(form.getFund());
		    	if (fundBean == null) {
		    		fundBean = fundDAO.getFundByTicker(form.getFund());
		    	}
		    	if (fundBean == null) {
		    		errors.add("No fund Matches.");
		    		return "buyFund.jsp";
		    	}
		    	
		    	session.setAttribute("fund", fundBean);
		    } else if (form.getButton() == "buy") {
		    	// input: user email
		    	CustomerBean customerBean = customerDAO.getCustomerByEmail((String)session.getAttribute("user"));
		    	if (customerBean.getCash() < form.getAmount() * 100) {
		    		errors.add("Not enough Money");
		    		return "buyFund.jsp";
		    	}
		    	customerBean.setCash(customerBean.getCash() - form.getAmount() * 100);
		    	customerDAO.update(customerBean);
		    	
		    	TransactionBean transactionBean = new TransactionBean();
		    	transactionBean.setAmount(form.getAmount() * 100);
		    	transactionBean.setCustomerBean(customerBean);
		    	transactionBean.setFundBean((FundBean)session.getAttribute("fund"));
		    	transactionBean.setTransactionType("buying");
		    	transactionDAO.insert(transactionBean);    	
		    }

	        return "buyFund.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
