package task7.databeans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="TASK7_TRANSCATION")
public class TransactionBean {
	
	@Id @GeneratedValue
	private int transactionId;
	
	@JoinColumn(name="CUSTOMERID")
	private CustomerBean customerBean;
	
	@JoinColumn(name="FUNDID")
	private FundBean fundBean;
	
	@Column(nullable=false)
	private Date executeDate;

	@Column(nullable=false)
	private long shares;

	@Column(nullable=false)
	private long amount;

	@Column(nullable=false)
	private String transactionType;
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public FundBean getFundBean() {
		return fundBean;
	}

	public void setFundBean(FundBean fundBean) {
		this.fundBean = fundBean;
	}

	public Date getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}

	public long getShares() {
		return shares;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}
