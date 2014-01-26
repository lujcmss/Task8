package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.FundBean;
import task7.databeans.FundInfoBean;
import task7.model.DateDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;

public class ResearchFund extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private DateDAO dateDAO;
	
	public ResearchFund(Model model) {
		fundDAO = model.getFundDAO();
		dateDAO = model.getDateDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
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
						fundBeans[i].getFundId(), dateDAO.getDate().getNewDate());

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