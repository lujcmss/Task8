package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.databeans.TransactionBean;
import task7.model.FundDAO;
import task7.model.Model;
import task7.model.TransactionDAO;
//akshata test 15 jan 530pm
//pato was here back test
public class TransactionHistory extends Action {
	private TransactionDAO transactionDAO = new TransactionDAO();
	private TransactionBean[] transaction ;
	public TransactionHistory(Model model) {
		//transactionDAO  = model.getTransactionDAO();
	}
	
	public String getName() { return "transactionHistory.do"; }
	
	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
                //Identifying if the user session is created, if its not, then it will return the user
        //to the login page
      
        System.out.println("eneters transaction history");
        
	
			
	      //    if (session.getAttribute("email") == null){
	        //	  errors.add("Not logged in");
	        	//  return "login.jsp";
	          //}
			
	          //Checking the userType
	         // if (session.getAttribute("userType") == "Employee"){
	        	  
	        	  
	          //}else{
	        	 // session.getAttribute("email"); 
	        	  //It must be displayed by order, i think it will display by default.
	        	 // String email = (String) (session.getAttribute("email"));
			 System.out.println("Just before doing dao thing.");
			 		
			String email = "df";	
			//this is just a test, the email shall be collected from the session.
			transaction = transactionDAO.getTransactionsPerUser(email);
			//Here we can use a for loop  to get all the variables out if required..
			System.out.println(transaction[0].getAmount());
	        	
	        	  
	          //}
	          
	          
			
	        	   
	          
	          
	          
	        
			
			
			
	        return "transactionHistory.jsp";
       
    }
}
