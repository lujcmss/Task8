package task7.databeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public class FundInfoBean {
	
	private String name;
	private String symbol;
	private double fundPrice;
	private double share;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getFundPrice() {
		return fundPrice;
	}
	public void setFundPrice(double fundPrice) {
		this.fundPrice = fundPrice;
	}
	public double getShare() {
		return share;
	}
	public void setShare(double share) {
		this.share = share;
	}	
}