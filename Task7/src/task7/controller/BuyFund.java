package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import task7.formbeans.BuyForm;
import task7.formbeans.CustomerLoginForm;
import task7.model.Model;

public class BuyFund extends Action{
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory.getInstance(BuyForm.class);
	public BuyFund(Model model) {
	}

	public String getName() { return "buyFund.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
        
        
        
        
        
        
		try {
			//Adding the formbean error validation
			
				BuyForm form = formBeanFactory.create(request);
				request.setAttribute("form",form);
						
				  if (!form.isPresent()) {
			          return "buyFund.jsp";
			      }		
	
	
			errors.addAll(form.getValidationErrors());
		        if (errors.size() != 0) {
		            return "buyFund.jsp";
		        }
			
			
			
			
	        return "buyFund.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
