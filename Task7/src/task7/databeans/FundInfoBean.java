package task7.databeans;

public class FundInfoBean {

	private int fundId;
	private String name;
	private String symbol;
	private double fundPrice;
	private double fundMaxPrice;
	private double fundMinPrice;
	private double fundAvgPrice;
	private double share;

	
	public double getFundMaxPrice() {
		return fundMaxPrice;
	}

	public void setFundMaxPrice(double fundMaxPrice) {
		this.fundMaxPrice = fundMaxPrice;
	}

	public double getFundMinPrice() {
		return fundMinPrice;
	}

	public void setFundMinPrice(double fundMinPrice) {
		this.fundMinPrice = fundMinPrice;
	}

	public double getFundAvgPrice() {
		return fundAvgPrice;
	}

	public void setFundAvgPrice(double fundAvgPrice) {
		this.fundAvgPrice = fundAvgPrice;
	}

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