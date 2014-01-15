package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import task7.model.Model;
//akshata test 15 jan 530pm
public class TransactionHistory extends Action {

	public TransactionHistory(Model model) {
	}

	public String getName() { return "transactionHistory.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
		try {
	        return "transactionHistory.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
