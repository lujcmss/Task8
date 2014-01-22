package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import task7.formbeans.CustomerLoginForm;
import task7.formbeans.SellFundForm;
import task7.model.Model;

public class SellFund extends Action {
	private FormBeanFactory<SellFundForm> formBeanFactory = FormBeanFactory.getInstance(SellFundForm.class);

	public SellFund(Model model) {
	}

	public String getName() { return "sellFund.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
		try {
			
			SellFundForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "sellFund.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		        if (errors.size() != 0) {
		            return "sellFund.jsp";
		        }
			
			
			
			
			
			
	        return "sellFund.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
