package task7.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.databeans.FundBean;
import task7.databeans.DateBean;
import task7.databeans.FundPriceHistoryBean;
import task7.model.CustomerDAO;
import task7.model.EmployeeDAO;
import task7.model.FundDAO;
import task7.model.FundPriceHistoryDAO;
import task7.model.Model;
import task7.model.DateDAO;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());

		Action.add(new Home(model));
		Action.add(new Login(model));
		Action.add(new ViewCustomerInformation(model));
		Action.add(new CreateCustomerAccount(model));
		Action.add(new CreateEmployeeAccount(model));
		Action.add(new DepositCheck(model));
		Action.add(new CreateFund(model));
		Action.add(new TransitionDay(model));
		Action.add(new ResearchFund(model));
		Action.add(new BuyFund(model));
		Action.add(new SellFund(model));
		Action.add(new RequestCheck(model));
		Action.add(new TransactionHistory(model));
		Action.add(new Logout(model));
		Action.add(new ChangePassword(model));
		Action.add(new ResetPassword(model));
		Action.add(new EditInfo(model));

		CustomerDAO customerDAO = model.getCustomerDAO();
		CustomerBean customerBean = new CustomerBean();
		customerBean.setAddr1("5869 northumberland St.");
		customerBean.setAddr2("Apt 1");
		customerBean.setCash(1000000);
		customerBean.setCity("Pittsburgh");
		customerBean.setCustomerEmail("jeff");
		customerBean.setFirstName("Jeff");
		customerBean.setLastName("Eppinger");
		customerBean.setPassword("jeff");
		customerBean.setState("PA");
		customerBean.setZip("15213");
		customerDAO.insert(customerBean);

		EmployeeDAO employeeDAO = model.getEmployeeDAO();
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setEmail("root");
		employeeBean.setFirstName("System");
		employeeBean.setLastName("Admin");
		employeeBean.setPassword("root");
		employeeDAO.insert(employeeBean);

		FundDAO fundDAO = model.getFundDAO();
		FundBean fundBean = new FundBean();
		fundBean.setName("apple");
		fundBean.setSymbol("APPL");
		fundDAO.insert(fundBean);
		fundBean.setName("google");
		fundBean.setSymbol("GOOG");
		fundDAO.insert(fundBean);

		FundPriceHistoryDAO fundPriceHistoryDAO = model
				.getFundPriceHistoryDAO();
		FundPriceHistoryBean fundPriceHistoryBean = new FundPriceHistoryBean();
		// apple
		fundPriceHistoryBean.setFundBean(fundDAO.getFundByName("apple"));
		fundPriceHistoryBean.setPrice(1000);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2013-12-01"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);
		fundPriceHistoryBean.setPrice(1500);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2013-12-03"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);
		fundPriceHistoryBean.setPrice(1452);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2013-12-10"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);
		fundPriceHistoryBean.setPrice(2048);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2014-01-01"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);

		// google
		fundPriceHistoryBean.setFundBean(fundDAO.getFundByName("google"));
		fundPriceHistoryBean.setPrice(900);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2013-12-01"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);
		fundPriceHistoryBean.setPrice(1350);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2013-12-03"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);
		fundPriceHistoryBean.setPrice(2193);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2013-12-10"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);
		fundPriceHistoryBean.setPrice(1839);
		fundPriceHistoryBean.setPriceDate(Date.valueOf("2014-01-01"));
		fundPriceHistoryDAO.insert(fundPriceHistoryBean);

		DateDAO dateDAO = model.getDateDAO();
		DateBean dateBean = new DateBean();
		dateBean.setOldDate(null);
		dateBean.setNewDate(Date.valueOf("2014-01-01"));
		dateDAO.insert(dateBean);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		Object user = (Object) session.getAttribute("user");
		String action = getActionName(servletPath);
		String userType = (String) session.getAttribute("userType");
		
		if (action.equals("login.do")) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}
		
		if (user == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
		}
		
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.contains("://")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("jsp/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
