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
import task7.formbeans.SellFundForm;
import task7.model.FundDAO;
import task7.model.Model;
import task7.model.PositionDAO;
import task7.model.TransactionDAO;

public class SellFund extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory.getInstance(SellFundForm.class);

	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public SellFund(Model model) {
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() { return "sellFund.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        
		try {
			SellFundForm form = formBeanFactory.create(request);

	   	    if (!form.isPresent()) {
		        return "sellFund.jsp";
			}		
			
	   	    errors.addAll(form.getValidationErrors());
		    if (errors.size() != 0) {
		        return "sellFund.jsp";
		    }

			CustomerBean customerBean = (CustomerBean)session.getAttribute("user");
			PositionBean[] positionBeans = positionDAO.getByCustomerId(customerBean.getCustomerId());
			
		    if (form.getButton() == null || form.getButton().equals("search")) {
		    	if (form.getFund() != null && !form.getFund().equals("")) {
			    	FundBean fundBean = null;
			    	for (int i = 0; i < positionBeans.length; i++) {
			    		if (positionBeans[i].getFundBean().getName().equals(form.getFund())) {
			    			fundBean = positionBeans[i].getFundBean();
			    		}
			    		if (positionBeans[i].getFundBean().getSymbol().equals(form.getFund())) {
			    			fundBean = positionBeans[i].getFundBean();
			    		}
			    	}
			    	if (fundBean == null) {
			    		errors.add("No fund Matches.");
			    		session.setAttribute("fundInfo", null);
			    		return "sellFund.jsp";
			    	}
			    	
			    	FundInfoBean[] fundInfoBeans = new FundInfoBean[1];
			    	fundInfoBeans[0] = new FundInfoBean();
			    	fundInfoBeans[0].setName(fundBean.getName());
			    	fundInfoBeans[0].setSymbol(fundBean.getSymbol());
					for (int j = 0; j < positionBeans.length; j++)
						if (positionBeans[j].getFundBean().getFundId() == fundBean.getFundId()) {
						fundInfoBeans[0].setShare(positionBeans[j].getShares() / 1000.0);
					}
					
					session.setAttribute("sellFundInfo", fundInfoBeans);
		    	} else {
			    	FundInfoBean[] fundInfoBeans = new FundInfoBean[positionBeans.length];
					for (int i = 0; i < positionBeans.length; i++) {
						fundInfoBeans[i] = new FundInfoBean();
						fundInfoBeans[i].setName(positionBeans[i].getFundBean().getName());
						fundInfoBeans[i].setSymbol(positionBeans[i].getFundBean().getSymbol());
						fundInfoBeans[i].setShare(positionBeans[i].getShares() / 1000.0);
					}
					
					session.setAttribute("sellFundInfo", fundInfoBeans);
				}
		    } else if (form.getButton().equals("sell")) {
		    	PositionBean positionBean = new PositionBean();
		    	for (int i = 0; i < positionBeans.length; i++) {
		    		if (positionBeans[i].getFundBean().getName().equals(form.getFundName())) {
		    			positionBean = positionBeans[i];
		    		}
		    	}
		    	
		    	long share = (long) (form.getShare() * 1000);
		    	if (positionBean.getShares() < share) {
		    		errors.add("Not enough share.");
		    		return "sellFund.jsp";
		    	}
		    	positionBean.setShares(positionBean.getShares() - share);
		    	positionDAO.update(positionBean);
		    	
		    	TransactionBean transactionBean = new TransactionBean();
		    	transactionBean.setAmount(share);
		    	transactionBean.setCustomerBean(customerBean);
		    	transactionBean.setFundBean(fundDAO.getFundByName(form.getFundName()));
		    	transactionBean.setTransactionType("Sell");
		    	transactionBean.setStatus("Pending");
		    	transactionDAO.insert(transactionBean);
		    }

	        return "sellFund.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "sellFund.jsp";
        }
    }
}
