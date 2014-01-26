package task7.databeans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class TransactionHistoryBean {
	private int transactionId;
	private FundBean fundBean;
	private Date executeDate;
	private double shares;
	private double amount;
	private String transactionType;
	private double sharePrice;
	private String status;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public FundBean getFundBean() {
		return fundBean;
	}
	public void setFundBean(FundBean fundBean) {
		this.fundBean = fundBean;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public double getShares() {
		return shares;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}
}
