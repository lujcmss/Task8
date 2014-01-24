package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.FundBean;
import task7.formbeans.CreateCustomerForm;
import task7.formbeans.CreateFundForm;
import task7.model.CustomerDAO;
import task7.model.FundDAO;
import task7.model.Model;

public class CreateFund extends Action {
	
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);
	
	private FundDAO fundDAO;
	
	public CreateFund(Model model) {
		fundDAO = model.getFundDAO();
	}

	public String getName() { return "createFund.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
		try {
			CreateFundForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "createFund.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());

		    if (fundDAO.hasFund((String)form.getFund()) == true || fundDAO.hasTicker((String)form.getTicker()) == true) {
		    	errors.add("Fund already exist.");
		    }
		    
		    if (errors.size() != 0) {
		        return "createFund.jsp";
	        }
		   	
		    FundBean fundBean = new FundBean();
		    fundBean.setName(form.getFund());
		    fundBean.setSymbol(form.getTicker());
		    fundDAO.insert(fundBean);
		    
	        return "home.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}

