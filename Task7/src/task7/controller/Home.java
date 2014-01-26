package task7.controller;

import java.sql.Date;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.databeans.FundBean;
import task7.databeans.FundInfoBean;
import task7.databeans.PositionBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;

public class Home extends Action {
	
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	
	public Home(Model model) {
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() { return "home.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        
		try {
			String userType = (String) session.getAttribute("userType");
			if (userType.equals("Employee")) {
				// to something
			} else {
				CustomerBean customerBean = (CustomerBean)session.getAttribute("user");
				FundBean[] fundBeans = fundDAO.getAllFunds();
	
				PositionBean[] positionBeans = positionDAO.getByCustomerId(customerBean.getCustomerId());
				FundInfoBean[] fundInfoBeans = new FundInfoBean[positionBeans.length];
				
				for (int i = 0; i < positionBeans.length; i++) {
	
					fundInfoBeans[i] = new FundInfoBean();
					fundInfoBeans[i].setShare(positionBeans[i].getShares() / 1000.0);
					fundInfoBeans[i].setFundId(positionBeans[i].getFundBean().getFundId());
					
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
			}
	        return "home.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
