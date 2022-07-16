package uabc.videoclubs.entities;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

public class ReturnIndex {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp returnDate;

	private String title;

	public ReturnIndex() {
		super();
	}

	public ReturnIndex(Timestamp returnDate, String title) {
		super();
		this.returnDate = returnDate;
		this.title = title;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ReturnIndex [returnDate=" + returnDate + ", title=" + title + "]";
	}

}
