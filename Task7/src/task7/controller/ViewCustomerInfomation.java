package task7.controller;

import java.sql.Date;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.FundInfoBean;
import task7.databeans.PositionBean;
import task7.databeans.TransactionBean;
import task7.formbeans.SellFundForm;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;

public class ViewCustomerInfomation extends Action {

	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public ViewCustomerInfomation(Model model) {
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() { return "viewCustomerInformation.do"; }

	public String perform(HttpServletRequest request) {
		// Set up the errors list
	    List<String> errors = new ArrayList<String>();
	    request.setAttribute("errors", errors);
	    HttpSession session = request.getSession();
	    
		try {
			CustomerBean customerBean = (CustomerBean)session.getAttribute("user");
			FundBean[] fundBeans = fundDAO.getAllFunds();

			PositionBean[] positionBeans = positionDAO.getByCustomerId(customerBean.getCustomerId());
			FundInfoBean[] fundInfoBeans = new FundInfoBean[positionBeans.length];
			
			for (int i = 0; i < positionBeans.length; i++) {

				fundInfoBeans[i] = new FundInfoBean();
				fundInfoBeans[i].setShare(positionBeans[i].getShares() / 100.0);
				
				int fundId = positionBeans[i].getFundBean().getFundId();
				for (int j = 0; j < fundBeans.length; j++) {
					if (fundBeans[j].getFundId() == fundId) {
						long nowPrice = fundPriceHistoryDAO.getPriceByFundAndDate(
								fundId, (Date)session.getAttribute("date"));
						fundInfoBeans[i].setFundPrice(nowPrice / 100.0);

						fundInfoBeans[i].setName(fundBeans[j].getName());
						fundInfoBeans[i].setSymbol(fundBeans[j].getSymbol());
					}
				}
			}
			session.setAttribute("fundInfo", fundInfoBeans);
	        return "viewCustomerInformation.jsp";
	    } catch (Exception e) {
    	    errors.add(e.getMessage());
    	    return "error.jsp";
    	}
    }
}

