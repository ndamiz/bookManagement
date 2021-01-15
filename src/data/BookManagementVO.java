package data;

public class BookManagementVO {
	
	private String userName;  //사용자 이름 
	private String userTel;	  //사용자연락처 
	private String userId;	  //사용자 id 
	private String userPwd;	  //사용자 pwd
	
	private String bookName;  //책이름 
	private String bookAuthor;// 저자
	private String bookGenre; // 장르
	private String publisher; 
	
	private String rentDay;   //대여일자
	private String stock; //제고량 
	
	public BookManagementVO() { 	}
	
	public BookManagementVO(String userName, String userTel, String userId, String userPwd) {
		this.userName = userName;
		this.userTel = userTel;
		this.userId = userId;
		this.userPwd = userPwd;
	}
	public BookManagementVO(String bookName, String bookAuthor, String bookGenre, String publisher, String stock) {
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookGenre = bookGenre;
		this.publisher = publisher;
		this.stock = stock;
	}
	public BookManagementVO(String userId,String bookName, String rentDay) {
		this.userId = userId;
		this.bookName = bookName;
		this.rentDay = rentDay;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public String getRentDay() {
		return rentDay;
	}
	

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setRentDay(String rentDay) {
		this.rentDay = rentDay;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}







	
}
