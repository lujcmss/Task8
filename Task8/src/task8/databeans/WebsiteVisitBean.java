package task8.databeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK8_WEBSITEVISIT")
public class WebsiteVisitBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int websiteVisitId;

	@Column(nullable = false)
	private String page;
	
	@Column(nullable = false)
	private Date date;
	
	public int getWebsiteVisitId() {
		return websiteVisitId;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}