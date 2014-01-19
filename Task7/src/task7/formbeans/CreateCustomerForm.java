package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerForm extends FormBean {
	private String email;
	private String psw;
	private String firstName;
	private String lastName;
	private String addr1;
	private String addr2;
	private String city;
	private String zipCode;
	private String confirm;
	private String state;

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = trimAndConvert(confirm, "<>>\"]");
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = trimAndConvert(state, "<>>\"]");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = trimAndConvert(email, "<>>\"]");
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = trimAndConvert(psw, "<>>\"]");
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

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}

		if (email == null || email.length() == 0) {
			errors.add("Email is required");
		}

		if (psw == null || psw.length() == 0) {
			errors.add("Password is required");
		}

		if (addr1 == null || addr1.length() == 0) {
			errors.add("Address is required");
		}
		if (city == null || city.length() == 0) {
			errors.add("City is required");
		}
		if (zipCode == null || zipCode.length() == 0) {
			errors.add("Zip code is required");
		}
		if (state == null || state.length() == 0) {
			errors.add("State is required");
		}

		if (errors.size() > 0)
			return errors;

		if (email.matches(".*[<>\"].*"))
			errors.add("email may not contain angle brackets or quotes");
		if (psw.matches(".*[<>\"].*"))
			errors.add("Password may not contain angle brackets or quotes");
		if (!confirm.equals(psw))
			errors.add("Password doesn't match password confirmation");

		// we need to check state names also, I found this regex on internet but
		// it's not working properly
		// if(!state.matches("\A(?:A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|PA|RI|S[CD]|T[NX]|UT|V[AT]|W[AIVY]|Alabama|Alaska|Arizona|Arkansas|California|Colorado|Connecticut|Delaware|District
		// of
		// Columbia|Florida|Georgia|Hawaii|Idaho|Illinois|Indiana|Iowa|Kansas|Kentucky|Louisiana|Maine|Maryland|Massachusetts|Michigan|Minnesota|Mississippi|Missouri|Montana|Nebraska|Nevada|New
		// Hampshire|New Jersey|New Mexico|New York|North Carolina|North
		// Dakota|Ohio|Oklahoma|Oregon|Pennsylvania|Rhode Island|South
		// Carolina|South
		// Dakota|Tennessee|Texas|Utah|Vermont|Virginia|Washington|West
		// Virginia|Wisconsin|Wyoming)\Z"))
		// errors.add("Illegal State name");
		if (!zipCode.matches("^d{5}$"))
			errors.add("Illegal Zipcode");

		return errors;
	}

}
