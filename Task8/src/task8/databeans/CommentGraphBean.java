package task8.databeans;

public class CommentGraphBean {
	
	private String username;
	private int count;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void incCount(int inc) {
		this.count += inc;
	}
}