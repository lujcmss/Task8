package task7.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.DateBean;
import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;
import task7.databeans.PositionBean;
import task7.databeans.TransactionBean;
import task7.databeans.TransitionBean;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.PositionDAO;
import task7.model.DateDAO;
import task7.model.TransactionDAO;

public class TransitionDay extends Action {

	private FundDAO fundDAO;
	private DateDAO dateDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public TransitionDay(Model model) {
		fundDAO = model.getFundDAO();
		dateDAO = model.getDateDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "transitionDay.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		session.setAttribute("curPage", "transitionDay.do");

		try {
			if (request.getParameter("button") != null
					&& request.getParameter("button").equals("Transition")) {
				int num = (Integer) session.getAttribute("fundNum");
				synchronized (fundDAO) {
					FundBean[] fundBeans = fundDAO.getAllFunds();
					TransitionBean[] transitionBeans = new TransitionBean[fundBeans.length];

					if (num != fundBeans.length) {
						DateBean date = dateDAO.getDate();
						for (int i = 0; i < fundBeans.length; i++) {
							transitionBeans[i] = new TransitionBean();
							transitionBeans[i].setFundId(fundBeans[i]
									.getFundId());
							transitionBeans[i].setFundBean(fundBeans[i]);
							transitionBeans[i].setLastDay(date.getNewDate());
							double oldPrice = fundPriceHistoryDAO
									.getPriceByFundAndDate(
											fundBeans[i].getFundId(),
											date.getNewDate()) / 100.0;
							transitionBeans[i].setOldPrice(oldPrice);
						}

						session.setAttribute("fundNum", fundBeans.length);
						session.setAttribute("transitionDayFunds",
								transitionBeans);
						errors.add("There are some new funds.");
						return "transitionDay.jsp";
					} else {
						DateBean date = dateDAO.getDate();
						date.setOldDate(dateDAO.getDate().getNewDate());
						try {
							date.setNewDate(Date.valueOf(request
									.getParameter("newDate")));
						} catch (IllegalArgumentException e) {
							errors.add("Wrong date format. It should be YYYY-MM-DD");
							return "transitionDay.jsp";
						}

						if (date.getOldDate().before(date.getNewDate())) {
							for (int i = 0; i < num; i++) {
								FundPriceHistoryBean fundPriceHistoryBean = new FundPriceHistoryBean();
								fundPriceHistoryBean.setFundBean(fundDAO
										.getFundById(i + 1));
								String newPrice = (String) request
										.getParameter("newPrice_" + i);
								fundPriceHistoryBean.setPrice((long) (Double
										.parseDouble(newPrice) * 100));
								fundPriceHistoryBean.setPriceDate(date
										.getNewDate());
								fundPriceHistoryDAO
										.insert(fundPriceHistoryBean);
							}

							TransactionBean[] transactionBeans = transactionDAO
									.getByStatus("Pending");
							for (int i = 0; i < transactionBeans.length; i++) {
								TransactionBean tran = transactionBeans[i];
								String type = tran.getTransactionType();

								CustomerBean customerBean = tran
										.getCustomerBean();
								if (customerBean == null)
									continue;

								if (type.equals("Buy")) {
									FundBean fundBean = tran.getFundBean();
									PositionBean positionBean = positionDAO
											.getByCustomerAndFund(customerBean
													.getCustomerId(), fundBean
													.getFundId());

									double price = fundPriceHistoryDAO
											.getPriceByFundAndDate(
													fundBean.getFundId(),
													date.getNewDate());
									long share = (long) (tran.getAmount()
											/ price * 1000);

									if (positionBean == null) {
										positionBean = new PositionBean();
										positionBean
												.setCustomerBean(customerBean);
										positionBean.setFundBean(fundBean);
										positionBean.setShares(share);
										positionDAO.insert(positionBean);
									} else {
										positionBean.setShares(positionBean
												.getShares() + share);
										positionDAO.update(positionBean);
									}

								} else if (type.equals("Sell")) {
									FundBean fundBean = tran.getFundBean();

									double price = fundPriceHistoryDAO
											.getPriceByFundAndDate(
													fundBean.getFundId(),
													date.getNewDate()) / 100;
									long amount = (long) (tran.getAmount()
											* price / 10);
									customerBean.setCash(customerBean.getCash()
											+ amount);
									customerDAO.update(customerBean);

								} else if (type.equals("Request")) {
									// nothing to do
								} else if (type.equals("Deposit")) {
									customerBean.setCash(customerBean.getCash()
											+ tran.getAmount());
									customerDAO.update(customerBean);
								}

								if (tran.getStatus().equals("Pending")) {
									tran.setStatus("Done");
								}

								tran.setExecuteDate(date.getNewDate());
								transactionDAO.update(tran);
							}

							dateDAO.update(date);

							fundBeans = fundDAO.getAllFunds();
							transitionBeans = new TransitionBean[fundBeans.length];

							Date newDate = dateDAO.getDate().getNewDate();
							for (int i = 0; i < fundBeans.length; i++) {
								transitionBeans[i] = new TransitionBean();
								transitionBeans[i].setFundId(fundBeans[i]
										.getFundId());
								transitionBeans[i].setFundBean(fundBeans[i]);
								transitionBeans[i].setLastDay(newDate);
								double oldPrice = fundPriceHistoryDAO
										.getPriceByFundAndDate(
												fundBeans[i].getFundId(),
												newDate) / 100.0;
								transitionBeans[i].setOldPrice(oldPrice);
							}

							session.setAttribute("fundNum", fundBeans.length);
							session.setAttribute("transitionDayFunds",
									transitionBeans);
						} else {
							errors.add("The new date must be after the old date.");
							return "transitionDay.jsp";
						}
					}
				}
			} else {
				FundBean[] fundBeans = fundDAO.getAllFunds();
				TransitionBean[] transitionBeans = new TransitionBean[fundBeans.length];

				Date newDate = dateDAO.getDate().getNewDate();
				for (int i = 0; i < fundBeans.length; i++) {
					transitionBeans[i] = new TransitionBean();
					transitionBeans[i].setFundId(fundBeans[i].getFundId());
					transitionBeans[i].setFundBean(fundBeans[i]);
					transitionBeans[i].setLastDay(newDate);
					double oldPrice = fundPriceHistoryDAO
							.getPriceByFundAndDate(fundBeans[i].getFundId(),
									newDate) / 100.0;
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
