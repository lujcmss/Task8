package task7.databeans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK7_POSITION")
public class PositionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName = "CUSTOMERID")
	private CustomerBean customerBean;

	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName = "FUNDID")
	private FundBean fundBean;

	@Column(nullable = false)
	private long shares;

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

	public long getShares() {
		return shares;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}
}
