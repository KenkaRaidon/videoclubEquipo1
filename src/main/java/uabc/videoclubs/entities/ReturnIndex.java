package uabc.videoclubs.entities;

import java.sql.Timestamp;

public class ReturnIndex {
	private Timestamp rturnDate;
	private String title;

	public ReturnIndex(Timestamp rturnDate, String title) {
		super();
		this.rturnDate = rturnDate;
		this.title = title;
	}

	public Timestamp getRturnDate() {
		return rturnDate;
	}

	public void setRturnDate(Timestamp rturnDate) {
		this.rturnDate = rturnDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReturnIndex [rturnDate=");
		builder.append(rturnDate);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

}
