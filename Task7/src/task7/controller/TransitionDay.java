package task7.controller;

import java.sql.Date;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PRIVATE_MEMBER;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;
import task7.databeans.TransactionBean;
import task7.databeans.TransitionBean;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;
import task7.model.TransactionDAO;

public class TransitionDay extends Action {
	
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public TransitionDay(Model model) {
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() { return "transitionDay.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        
		try {
			
			if (request.getAttribute("button").equals("Transition")) {
				int num = (Integer)session.getAttribute("fundNum");
				synchronized (fundDAO) {
					FundBean[] fundBeans = fundDAO.getAllFunds();
					TransitionBean[] transitionBeans = new TransitionBean[fundBeans.length];
					
					if (num != fundBeans.length) {
						for (int i = 0; i < fundBeans.length; i++) {
							transitionBeans[i] = new TransitionBean();
							transitionBeans[i].setFundId(fundBeans[i].getFundId());
							transitionBeans[i].setFundName(fundBeans[i].getName());
							transitionBeans[i].setFundSymbol(fundBeans[i].getSymbol());
							
							
							transitionBeans[i].setLastday((Date)session.getAttribute("date"));
							double oldPrice = fundPriceHistoryDAO.getPriceByFundAndDate(
									fundBeans[i].getFundId(), (Date)session.getAttribute("date"));
							transitionBeans[i].setOldPrice(oldPrice);
						}
						
						session.setAttribute("fundNum", fundBeans.length);
						session.setAttribute("transitionDayFunds", transitionBeans);
						errors.add("There is some new funds.");
						return "transitionDay.jsp";
					} else {
						Date newDate = (Date)request.getAttribute("newDate");
						Date oldDate = (Date)session.getAttribute("date");
						
						if (oldDate.before(newDate)) {
							for (int i = 0; i < num; i++) {
								FundPriceHistoryBean fundPriceHistoryBean = new FundPriceHistoryBean();
								fundPriceHistoryBean.setFundBean(fundDAO.getFundById(i+1));
								String newPrice = (String)request.getAttribute("newPrice_"+i);
								fundPriceHistoryBean.setPrice((long)(Double.parseDouble(newPrice) * 100));
								fundPriceHistoryBean.setPriceDate(newDate);
								fundPriceHistoryDAO.insert(fundPriceHistoryBean);
							}
							
							TransactionBean[] transactionBeans = transactionDAO.getPendings(true);
							for (int i = 0; i < transactionBeans.length; i++) {
								TransactionBean tran = transactionBeans[i];
								String type = tran.getTransactionType();
								if (type.equals("Buy")) {
									//CustomerBean customerBean = tran.getCustomerBean();
									//FundBean fundBean = tran.getFundBean();
								} else if (type.equals("Sell")) {
									
								} else if (type.equals("Request")) {
									
								} else if (type.equals("Deposit")){
									
								}
								tran.setPending(false);
								transactionDAO.update(tran);
							}
						} else {
							errors.add("The new date must be after the old date.");
							return "transitionDay.jsp";
						}
					}
				}
			} else {
				System.out.println("............");
				FundBean[] fundBeans = fundDAO.getAllFunds();
				TransitionBean[] transitionBeans = new TransitionBean[fundBeans.length];

				for (int i = 0; i < fundBeans.length; i++) {
					transitionBeans[i] = new TransitionBean();
					transitionBeans[i].setFundId(fundBeans[i].getFundId());
					transitionBeans[i].setFundName(fundBeans[i].getName());
					transitionBeans[i].setFundSymbol(fundBeans[i].getSymbol());
					transitionBeans[i].setLastday((Date)session.getAttribute("date"));
					double oldPrice = fundPriceHistoryDAO.getPriceByFundAndDate(
							fundBeans[i].getFundId(), (Date)session.getAttribute("date"));
					transitionBeans[i].setOldPrice(oldPrice);
				}
				
				session.setAttribute("fundNum", fundBeans.length);
				session.setAttribute("transitionDayFunds", transitionBeans);				
			}
			
	        return "transitionDay.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "transitionDay.jsp";
        }
    }
}
