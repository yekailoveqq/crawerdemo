package org.simple.crawerDemo.crawer;


/**
 * 
 * @author ge
 * 图书信息
 */
public class BookInfo {
	
	public BookInfo(){}

	@CellConfig(index = 0)
	private String bookName;
	
	@CellConfig(index = 1)
	private String bookScore;
	
	@CellConfig(index = 2)
	private String bookComment;
	
	@CellConfig(index = 3)
	private String bookAuthor;
	
	@CellConfig(index = 4)
	private String bookPublish;
	
	@CellConfig(index = 5)
	private String bookTime;
	
	@CellConfig(index = 6)
	private String bookPrice;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookScore() {
		return bookScore;
	}

	public void setBookScore(String bookScore) {
		this.bookScore = bookScore;
	}

	public String getBookComment() {
		return bookComment;
	}

	public void setBookComment(String bookComment) {
		this.bookComment = bookComment;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublish() {
		return bookPublish;
	}

	public void setBookPublish(String bookPublish) {
		this.bookPublish = bookPublish;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public BookInfo(String bookName, String bookScore, String bookComment, String bookAuthor, String bookPublish,
			String bookTime, String bookPrice) {
		super();
		this.bookName = bookName;
		this.bookScore = bookScore;
		this.bookComment = bookComment;
		this.bookAuthor = bookAuthor;
		this.bookPublish = bookPublish;
		this.bookTime = bookTime;
		this.bookPrice = bookPrice;
	}
}
