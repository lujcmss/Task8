package task7.databeans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK7_FUND")
public class FundBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int fundId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String symbol;

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

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
}
