package task8.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import task8.model.Model;

public class Logout extends Action {

	public Logout(Model model) {
	}

	public String getName() {
		return "logout.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		HttpSession session = request.getSession(false);
		session.setAttribute("accessToken", null);

		return "home.do";
	}
}
