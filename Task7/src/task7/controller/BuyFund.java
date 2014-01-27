package task7.controller;

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
import task7.model.CustomerDAO;
import task7.model.DateDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;
import task7.model.TransactionDAO;

public class BuyFund extends Action{
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory.getInstance(BuyForm.class);
	
	private FundDAO fundDAO;
	private DateDAO dateDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	
	public BuyFund(Model model) {
		fundDAO = model.getFundDAO();
		dateDAO = model.getDateDAO();
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
        session.setAttribute("curPage", "funds.do");
        
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
					fundInfoBeans[0].setFundPrice(fundPriceHistoryDAO.getPriceByFundAndDate(
							fundBeans[0].getFundId(), dateDAO.getDate().getNewDate()));
					
					for (int j = 0; j < positionBeans.length; j++)
						if (positionBeans[j].getFundBean().getFundId() == fundBean.getFundId()) {
						fundInfoBeans[0].setShare(positionBeans[j].getShares() / 1000.0);
					}
					
					session.setAttribute("buyFundInfo", fundInfoBeans);
		    	} else {
			    	FundInfoBean[] fundInfoBeans = new FundInfoBean[fundBeans.length];
					for (int i = 0; i < fundBeans.length; i++) {
						fundInfoBeans[i] = new FundInfoBean();
						fundInfoBeans[i].setName(fundBeans[i].getName());
						fundInfoBeans[i].setSymbol(fundBeans[i].getSymbol());
						fundInfoBeans[i].setFundPrice(fundPriceHistoryDAO.getPriceByFundAndDate(
								fundBeans[i].getFundId(), dateDAO.getDate().getNewDate()));
						
						for (int j = 0; j < positionBeans.length; j++) {
							if (positionBeans[j].getFundBean().getFundId() == fundBeans[i].getFundId()) {
								fundInfoBeans[i].setShare(positionBeans[j].getShares() / 1000.0);
							}
						}
					}
					
					session.setAttribute("buyFundInfo", fundInfoBeans);
				}
		    } else if (form.getButton().equals("buy")) {
		    	long amount = (long) (form.getAmount() * 100);
		    	
		    	synchronized (customerDAO) {
			    	if (customerBean.getCash() < amount) {
			    		errors.add("Not enough Money.");
			    		return "buyFund.jsp";
			    	}
			    	customerBean.setCash(customerBean.getCash() - amount);
			    	customerDAO.update(customerBean);
				}
		    	
		    	TransactionBean transactionBean = new TransactionBean();
		    	transactionBean.setAmount(amount);
		    	transactionBean.setCustomerBean(customerBean);
		    	transactionBean.setFundBean(fundDAO.getFundByName(form.getFundName()));
		    	transactionBean.setTransactionType("Buy");
		    	transactionBean.setStatus("Pending");
		    	transactionDAO.insert(transactionBean);    	
		    }

	        return "buyFund.jsp";
        } catch (Exception e) {
        	System.out.println(e);
        	errors.add(e.getMessage());
        	return "buyFund.jsp";
        }
    }
}
