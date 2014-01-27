package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;
import task7.formbeans.EditInfoForm;
import task7.model.CustomerDAO;
import task7.model.EmployeeDAO;
import task7.model.Model;

public class EditInfo extends Action {
	private FormBeanFactory<EditInfoForm> formBeanFactory = FormBeanFactory.getInstance(EditInfoForm.class);
	
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	
	public EditInfo(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "editInfo.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        session.setAttribute("curPage", "home.do");
        
		try {
			EditInfoForm form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
	            return "editInfo.jsp";
	        }
			
			errors.addAll(form.getValidationErrors());
		    if (errors.size() != 0) {
		        return "editInfo.jsp";
		    }
		    
		    String userType = (String) session.getAttribute("userType");
			if (userType.equals("Employee")) {
				EmployeeBean employeeBean = (EmployeeBean)session.getAttribute("user");
				
				employeeBean.setFirstName(form.getFirstName());
				employeeBean.setLastName(form.getLastName());
				
				employeeDAO.update(employeeBean);
			} else {
			    CustomerBean customerBean = (CustomerBean)session.getAttribute("user");
			    
			    customerBean.setAddr1(form.getAddr1());
			    customerBean.setAddr2(form.getAddr2());
			    customerBean.setCity(form.getCity());
			    customerBean.setFirstName(form.getFirstName());
			    customerBean.setLastName(form.getLastName());
			    customerBean.setState(form.getState());
			    customerBean.setZip(form.getZipCode());
	
			    customerDAO.update(customerBean);
			}
			return "home.do";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "editInfo.jsp";
        }
    }
}

