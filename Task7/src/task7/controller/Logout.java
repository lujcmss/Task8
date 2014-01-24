package task7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task7.model.Model;

public class Logout extends Action {

	public Logout(Model model) {
	}

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
        HttpSession session = request.getSession(false);
        session.setAttribute("userType", null);
        session.setAttribute("email", null);
        session.setAttribute("user", null);

		request.setAttribute("message", "You are now logged out");
	    return "login.do";
    }
}

