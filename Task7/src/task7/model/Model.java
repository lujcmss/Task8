package task7.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private DateDAO dateDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			//String jdbcDriver = config.getInitParameter("jdbcDriverName");
			//String jdbcURL    = config.getInitParameter("jdbcURL");
			
			//ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			customerDAO = new CustomerDAO();
			employeeDAO = new EmployeeDAO();
			fundDAO = new FundDAO();
			fundPriceHistoryDAO = new FundPriceHistoryDAO();
			positionDAO = new PositionDAO();
			transactionDAO = new TransactionDAO();
			dateDAO = new DateDAO();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	public CustomerDAO getCustomerDAO() {return customerDAO;}
	public EmployeeDAO getEmployeeDAO() {return employeeDAO;}
	public FundDAO getFundDAO() {return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {return fundPriceHistoryDAO;}
	public PositionDAO getPositionDAO() {return positionDAO;}
	public TransactionDAO getTransactionDAO() {return transactionDAO;}
	public DateDAO getDateDAO() {return dateDAO;}
}

