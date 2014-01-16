package task7.databeans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="TASK7_FUNDPRICEHISTORY")
public class FundPriceHistoryBean {
	@Id
	@JoinColumn(name="FUNDID")
	private FundBean fundBean;

	@Id
	private Date priceDate;
	
	@Column(nullable=false)
	private long price;
	
	public FundBean getFundBean() {
		return fundBean;
	}

	public void setFundBean(FundBean fundBean) {
		this.fundBean = fundBean;
	}

	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
}
