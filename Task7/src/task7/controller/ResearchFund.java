package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;
import task7.databeans.FundInfoBean;
import task7.databeans.FundPriceHistoryBean;
import task7.formbeans.ResearchFundForm;
import task7.model.CustomerDAO;
import task7.model.DateDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;

public class ResearchFund extends Action {
	private FormBeanFactory<ResearchFundForm> formBeanFactory = FormBeanFactory
			.getInstance(ResearchFundForm.class);

	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private DateDAO dateDAO;

	public ResearchFund(Model model) {
		fundDAO = model.getFundDAO();
		dateDAO = model.getDateDAO();
		customerDAO = model.getCustomerDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "researchFund.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(true);
		session.setAttribute("curPage", "funds.do");
		session.setAttribute("searched", false);
		
		try {
			CustomerBean customerBean = (CustomerBean) session
					.getAttribute("user");
			session.setAttribute("user",
					customerDAO.getCustomerByEmail(customerBean.getEmail()));

			ResearchFundForm form = formBeanFactory.create(request);

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "researchFund.jsp";
			}

			FundBean[] fundBeans = fundDAO.getAllFunds();
			
			
			if (!form.isPresent() || form.getButton() == null || form.getButton().equals("search")) {
				if (form.isPresent() && form.getFundName() != null
						&& !form.getFundName().equals("")) {
					FundBean fundBean = fundDAO.getFundByName(form
							.getFundName());
					
					if (fundBean == null) {
						fundBean = fundDAO.getFundByTicker(form.getFundName());
					}
					if (fundBean == null) {
						errors.add("No fund Matches.");
						return "researchFund.jsp";
					}
					
					
					FundPriceHistoryBean[] fundPriceHistoryBeans = fundPriceHistoryDAO
							.getHistoryByFundId(fundBean.getFundId());

					long minPrice = fundPriceHistoryBeans[0].getPrice();
					long maxPrice = fundPriceHistoryBeans[0].getPrice();
					long avgPrice = fundPriceHistoryBeans[0].getPrice();
					
					for (int i = 1; i < fundPriceHistoryBeans.length; i++) {
						long tmp = fundPriceHistoryBeans[i].getPrice();
						if (minPrice > tmp) minPrice = tmp;
						if (maxPrice < tmp) maxPrice = tmp;
						avgPrice += tmp;
					}

					long nowPrice = fundPriceHistoryDAO
							.getPriceByFundAndDate(
									fundBean.getFundId(), dateDAO
											.getDate().getNewDate());

					FundInfoBean[] fundInfoBeans = new FundInfoBean[1];
					fundInfoBeans[0] = new FundInfoBean();
					fundInfoBeans[0].setName(fundBean.getName());
					fundInfoBeans[0].setSymbol(fundBean.getSymbol());
					fundInfoBeans[0].setFundPrice(nowPrice / 100.0);
					fundInfoBeans[0].setFundMaxPrice(maxPrice / 100.0);
					fundInfoBeans[0].setFundMinPrice(minPrice / 100.0);
					fundInfoBeans[0].setFundAvgPrice(avgPrice / 100.0 / fundPriceHistoryBeans.length);
					
					session.setAttribute("fundName", fundBean.getName());
					session.setAttribute("searched", true);
					session.setAttribute("fundInfo", fundInfoBeans);
				} else {
					FundInfoBean[] fundInfoBeans = new FundInfoBean[fundBeans.length];
					
					for (int i = 0; i < fundBeans.length; i++) {
						
						long nowPrice = fundPriceHistoryDAO
								.getPriceByFundAndDate(
										fundBeans[i].getFundId(), dateDAO
												.getDate().getNewDate());
						
						fundInfoBeans[i] = new FundInfoBean();
						fundInfoBeans[i].setFundPrice(nowPrice / 100.0);
						
						FundPriceHistoryBean[] fundPriceHistoryBeans = fundPriceHistoryDAO
								.getHistoryByFundId(fundBeans[i].getFundId());
						long minPrice = fundPriceHistoryBeans[0].getPrice();
						long maxPrice = fundPriceHistoryBeans[0].getPrice();
						long avgPrice = fundPriceHistoryBeans[0].getPrice();
						
						for (int j = 1; j < fundPriceHistoryBeans.length; j++) {
							long tmp = fundPriceHistoryBeans[j].getPrice();
							if (minPrice > tmp) minPrice = tmp;
							if (maxPrice < tmp) maxPrice = tmp;
							avgPrice += tmp;
						}
						
						fundInfoBeans[i].setName(fundBeans[i].getName());
						fundInfoBeans[i].setSymbol(fundBeans[i].getSymbol());
						fundInfoBeans[i].setFundMaxPrice(maxPrice / 100.0);
						fundInfoBeans[i].setFundMinPrice(minPrice / 100.0);
						fundInfoBeans[i].setFundAvgPrice(avgPrice / 100.0 / fundPriceHistoryBeans.length);
						
					}
					
					session.setAttribute("fundInfo", fundInfoBeans);
					session.setAttribute("searched", false);
				}
			}

			return "researchFund.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "researchFund.jsp";
		}
	}
}