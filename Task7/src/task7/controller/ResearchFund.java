package task7.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.FundInfoBean;
import task7.databeans.FundPriceHistoryBean;
import task7.databeans.PositionBean;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;

public class ResearchFund extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	
	public ResearchFund(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() { return "researchFund.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession(true);
		try {
			//CustomerBean customerBean = (CustomerBean)session.getAttribute("user");
			FundBean[] fundBeans = fundDAO.getAllFunds();
			FundInfoBean[] fundInfoBeans = new FundInfoBean[fundBeans.length];

			//PositionBean[] positionBeans = positionDAO.getByCustomerId(customerBean.getCustomerId());

			for (int i = 0; i < fundBeans.length; i++) {
				long nowPrice = fundPriceHistoryDAO.getPriceByFundAndDate(
						fundBeans[i].getFundId(), (Date)session.getAttribute("date"));

				fundInfoBeans[i] = new FundInfoBean();
				fundInfoBeans[i].setFundPrice(nowPrice / 100.0);
				
				fundInfoBeans[i].setName(fundBeans[i].getName());
				fundInfoBeans[i].setSymbol(fundBeans[i].getSymbol());
				
				/*
				for (int j = 0; j < positionBeans.length; j++)
					if (positionBeans[j].getFundBean().getFundId() == fundBeans[i].getFundId()) {
					fundInfoBeans[i].setShare(positionBeans[j].getShares() / 100.0);
				}
				*/
			}
			session.setAttribute("fundInfo", fundInfoBeans);
			// check for errors
			return "researchFund.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}