package task8.databeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK8_COMMENT")
public class CommentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int commentId;

	@Column(nullable = false)
	private UserBean userBean;
	
	private String comment;
	private Date date;
	private String imageSource;
	private String imageSourceOri;
	
	public int getCommentId() {
		return commentId;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getImageSource() {
		return imageSource;
	}
	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}
	public String getImageSourceOri() {
		return imageSourceOri;
	}
	public void setImageSourceOri(String imageSourceOri) {
		this.imageSourceOri = imageSourceOri;
	}
}