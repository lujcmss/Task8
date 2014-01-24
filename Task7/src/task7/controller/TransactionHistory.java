package task7.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.databeans.FundBean;
import task7.databeans.FundInfoBean;
import task7.databeans.PositionBean;
import task7.databeans.TransactionBean;
import task7.databeans.TransactionHistoryBean;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.TransactionDAO;

public class TransactionHistory extends Action {
	
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public TransactionHistory(Model model) {
		transactionDAO  = model.getTransactionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() { return "transactionHistory.do"; }
	
	public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
		try {
			CustomerBean customerBean = (CustomerBean)session.getAttribute("user");
			TransactionBean[] transactionBeans = transactionDAO.getTransactionsByCustomerId(customerBean.getCustomerId());
			TransactionHistoryBean[] transactionHistoryBeans = new TransactionHistoryBean[transactionBeans.length];
			
			for (int i = 0; i < transactionBeans.length; i++) {
				transactionHistoryBeans[i] = new TransactionHistoryBean();
				
				transactionHistoryBeans[i].setExecuteDate(transactionBeans[i].getExecuteDate());
				transactionHistoryBeans[i].setFundName(transactionBeans[i].getFundBean().getName());
				transactionHistoryBeans[i].setTransactionId(transactionBeans[i].getTransactionId());
				transactionHistoryBeans[i].setTransactionType(transactionBeans[i].getTransactionType());
				
				String type = transactionHistoryBeans[i].getTransactionType();
				if (type.startsWith("D") || type.startsWith("R") || type.equals("Buy (pending)")) {
					transactionHistoryBeans[i].setAmount(transactionBeans[i].getAmount() / 100.0);
					transactionHistoryBeans[i].setShares(-1);
					transactionHistoryBeans[i].setSharePrice(-1);
				} else if (type.equals("Sell (pending)")) {
					transactionHistoryBeans[i].setShares(transactionBeans[i].getAmount() / 1000.0);
					transactionHistoryBeans[i].setAmount(-1);
					transactionHistoryBeans[i].setSharePrice(-1);
				} else if (type.equals("Sell")) {
					long price = fundPriceHistoryDAO.getPriceByFundAndDate(
							transactionBeans[i].getFundBean().getFundId(), transactionBeans[i].getExecuteDate());
					long shares = transactionBeans[i].getAmount();
					transactionHistoryBeans[i].setShares(shares / 1000.0);
					transactionHistoryBeans[i].setSharePrice(price / 100.0);
					transactionHistoryBeans[i].setAmount((shares / 1000.0) * (price / 100.0));
				} else {
					long price = fundPriceHistoryDAO.getPriceByFundAndDate(
							transactionBeans[i].getFundBean().getFundId(), transactionBeans[i].getExecuteDate());
					long amount = transactionBeans[i].getAmount();
					transactionHistoryBeans[i].setAmount(amount / 100.0);
					transactionHistoryBeans[i].setSharePrice(price / 100.0);
					transactionHistoryBeans[i].setShares((amount / 100.0) * (price / 100.0));
				}
			}

			session.setAttribute("historyInfo", transactionHistoryBeans);
	        return "transactionHistory.jsp";
        } catch (Exception e) {
        	System.out.println(e);
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}
}
