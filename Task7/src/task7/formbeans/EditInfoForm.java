package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EditInfoForm extends FormBean {
	private String firstName;
	private String lastName;
	private String addr1;
	private String addr2;
	private String city;
	private String zipCode;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = trimAndConvert(state, "<>>\"]");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = trimAndConvert(firstName, "<>>\"]");
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = trimAndConvert(lastName, "<>>\"]");
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = trimAndConvert(addr1, "<>>\"]");
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = trimAndConvert(addr2, "<>>\"]");
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = trimAndConvert(city, "<>>\"]");
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = trimAndConvert(zipCode, "<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (firstName == null || firstName.length() == 0) {
			errors.add("Firstname is required");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Lastname is required");
		}
		
		if (zipCode != null && !zipCode.matches("^\\d{1,10}$"))
			errors.add("Illegal Zipcode");


		if (firstName.length()>30) {
			errors.add("Firstname is too long");
		}

		if (lastName.length()>30) {
			errors.add("Lastname is too long");
		}
		
		return errors;
	}

}
