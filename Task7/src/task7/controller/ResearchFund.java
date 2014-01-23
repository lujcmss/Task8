package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;

public class ResearchFund extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public ResearchFund(Model model) {
	}

	public String getName() { return "researchFund.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession(true);
		try {
			FundBean fundBean = fundDAO.getAllFunds();
			FundPriceHistoryBean fundPriceHistoryBean = fundPriceHistoryDAO.getAllHistory();
			
			// check for errors
			return "researchFund.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}