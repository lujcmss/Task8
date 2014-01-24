package task7.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.FundInfoBean;
import task7.databeans.PositionBean;
import task7.databeans.TransactionBean;
import task7.formbeans.BuyForm;
import task7.formbeans.LoginForm;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;
import task7.model.TransactionDAO;

public class BuyFund extends Action{
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory.getInstance(BuyForm.class);
	
	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	
	public BuyFund(Model model) {
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
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

	   	    if (!form.isPresent()) {
		        return "buyFund.jsp";
			}		
			
	   	    errors.addAll(form.getValidationErrors());
		    if (errors.size() != 0) {
		        return "buyFund.jsp";
		    }

			CustomerBean customerBean = (CustomerBean)session.getAttribute("user");
			FundBean[] fundBeans = fundDAO.getAllFunds();
			PositionBean[] positionBeans = positionDAO.getByCustomerId(customerBean.getCustomerId());
			
		    if (form.getButton() == null || form.getButton().equals("search")) {
		    	if (form.getFund() != null && !form.getFund().equals("")) {
			    	FundBean fundBean = fundDAO.getFundByName(form.getFund());
			    	if (fundBean == null) {
			    		fundBean = fundDAO.getFundByTicker(form.getFund());
			    	}
			    	if (fundBean == null) {
			    		errors.add("No fund Matches.");
			    		session.setAttribute("fundInfo", null);
			    		return "buyFund.jsp";
			    	}
			    	
			    	FundInfoBean[] fundInfoBeans = new FundInfoBean[1];
			    	fundInfoBeans[0] = new FundInfoBean();
			    	fundInfoBeans[0].setName(fundBean.getName());
			    	fundInfoBeans[0].setSymbol(fundBean.getSymbol());
					for (int j = 0; j < positionBeans.length; j++)
						if (positionBeans[j].getFundBean().getFundId() == fundBean.getFundId()) {
						fundInfoBeans[0].setShare(positionBeans[j].getShares() / 100.0);
					}
					
					session.setAttribute("fundNum", 1);
					session.setAttribute("fundInfo", fundInfoBeans);
		    	} else {
			    	FundInfoBean[] fundInfoBeans = new FundInfoBean[fundBeans.length];
					for (int i = 0; i < fundBeans.length; i++) {
						fundInfoBeans[i] = new FundInfoBean();
						fundInfoBeans[i].setName(fundBeans[i].getName());
						fundInfoBeans[i].setSymbol(fundBeans[i].getSymbol());
						
						for (int j = 0; j < positionBeans.length; j++)
							if (positionBeans[j].getFundBean().getFundId() == fundBeans[i].getFundId()) {
							fundInfoBeans[i].setShare(positionBeans[j].getShares() / 100.0);
						}
					}
					
					session.setAttribute("fundNum", fundBeans.length);
					session.setAttribute("fundInfo", fundInfoBeans);
				}
		    } else if (form.getButton().equals("buy")) {
		    	if (customerBean.getCash() < form.getAmount() * 100) {
		    		errors.add("Not enough Money.");
		    		return "buyFund.jsp";
		    	}
		    	customerBean.setCash(customerBean.getCash() - (long)(form.getAmount() * 100));
		    	customerDAO.update(customerBean);
		    	
		    	TransactionBean transactionBean = new TransactionBean();
		    	transactionBean.setAmount((long)(form.getAmount() * 100));
		    	transactionBean.setCustomerBean(customerBean);
		    	transactionBean.setFundBean(fundDAO.getFundByName(form.getFundName()));
		    	transactionBean.setTransactionType("Buy (pending)");
		    	transactionDAO.insert(transactionBean);    	
		    }

	        return "buyFund.jsp";
        } catch (Exception e) {
        	System.out.println(e);
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
