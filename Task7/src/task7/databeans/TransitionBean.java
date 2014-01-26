package task7.databeans;

import java.sql.Date;

public class TransitionBean {
	private int fundId;
	private Date lastDay;
	private FundBean fundBean;
	private double oldPrice;
	private double newPrice;
	
	public int getFundId() {
		return fundId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public Date getLastDay() {
		return lastDay;
	}
	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}
	public FundBean getFundBean() {
		return fundBean;
	}
	public void setFundBean(FundBean fundBean) {
		this.fundBean = fundBean;
	}
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
}
