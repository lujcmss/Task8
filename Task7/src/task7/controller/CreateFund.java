package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.FundBean;
import task7.formbeans.CreateFundForm;
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
        HttpSession session = request.getSession();
        session.setAttribute("funds", fundDAO.getAllFunds());
        
		try {
			CreateFundForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "createFund.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());

		    if (fundDAO.hasFund(form.getFund()) == true || fundDAO.hasTicker(form.getTicker()) == true) {
		    	errors.add("Fund already exist.");
		    }
		    
		    if (errors.size() != 0) {
		        return "createFund.jsp";
	        }
		   	
		    FundBean fundBean = new FundBean();
		    fundBean.setName(form.getFund());
		    fundBean.setSymbol(form.getTicker());
		    fundDAO.insert(fundBean);
		    
		    session.setAttribute("funds", fundDAO.getAllFunds());
	        return "createFund.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "createFund.jsp";
        }
    }
}

