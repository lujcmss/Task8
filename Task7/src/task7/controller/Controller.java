package task7.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.databeans.FundBean;
import task7.model.CustomerDAO;
import task7.model.EmployeeDAO;
import task7.model.FundDAO;
import task7.model.Model;

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
        customerBean.setAddr1("penn Ave");
        customerBean.setAddr2("Apt1");
        customerBean.setCash(0);
        customerBean.setCity("Pitt");
        customerBean.setCustomerEmail("c");
        customerBean.setFirstName("customer");
        customerBean.setLastName("CMU");
        customerBean.setPassword("c");
        customerBean.setState("PA");
        customerBean.setZip("15213");
        customerDAO.insert(customerBean);
        
        EmployeeDAO employeeDAO = model.getEmployeeDAO();
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmail("e");
        employeeBean.setFirstName("employee");
        employeeBean.setLastName("CMU");
        employeeBean.setPassword("e");
        employeeDAO.insert(employeeBean);
        
        FundDAO fundDAO = model.getFundDAO();
        FundBean fundBean = new FundBean();
        fundBean.setName("Apple");
        fundBean.setSymbol("APPL");
        fundDAO.insert(fundBean);
        fundBean.setName("Google");
        fundBean.setSymbol("GOOG");
        fundDAO.insert(fundBean);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        Object user = (Object) session.getAttribute("user");
        String action = getActionName(servletPath);
        
        //System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);
        if (action.equals("login.do")) {
        	// Allow these actions without logging in
			return Action.perform(action, request);
        }
        
        if (user == null) {
        	// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
        }
        
      	// Let the logged in user run his chosen action
		return Action.perform(action, request);
    }

    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
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
	   		RequestDispatcher d = request.getRequestDispatcher("jsp/" + nextPage);
	   		d.forward(request, response);
	   		return;
    	}

    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
