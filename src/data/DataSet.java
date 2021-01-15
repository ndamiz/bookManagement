package data;

import java.util.HashMap;

public class DataSet {
	public static HashMap<String, BookManagementVO> userData = new HashMap<String, BookManagementVO>();
	public static HashMap<String, BookManagementVO> bookData = new HashMap<String, BookManagementVO>();
	public static HashMap<String, BookManagementVO> rentData = new HashMap<String, BookManagementVO>();
	public DataSet() {
	}
	public static void setUserData() {
		userData.put("nda", new BookManagementVO("김현아", "010-9913-3500", "nda", "1212"));
		userData.put("cool", new BookManagementVO("박세라", "010--5716-9005", "cool", "q1w2"));
		userData.put("tjdbs", new BookManagementVO("김서윤", "010-2121-3131", "tjdbs", "0000"));
		userData.put("dyddn", new BookManagementVO("김용우", "010-9876-5432", "dyddn", "0000"));
		userData.put("wjddnjs", new BookManagementVO("최정원", "010-1357-2468", "wjddnjs", "0000"));
		userData.put("dbswn", new BookManagementVO("차윤주", "010-1010-5656", "dbswn", "0000"));
	}

	public static void setBookData() {
		bookData.put("달러구트 꿈 백화점", new BookManagementVO("달러구트 꿈 백화점", "이미예", "소설", "팩토리나인", "2"));
		bookData.put("바이러스X", new BookManagementVO("바이러스X", "김진명", "소설", "이타북스", "1"));
		bookData.put("녹슨달", new BookManagementVO("녹슨달", "하지은", "소설", "드림노블", "0"));
		bookData.put("2인조", new BookManagementVO("2인조", "이석원", "에세이", "달", "2"));
		bookData.put("마음챙김", new BookManagementVO("마음챙김", "샤우나 샤피로", "자기계발", "안드로메디안", "4"));
		bookData.put("가장 예쁜 생각을 너에게 주고 싶다", new BookManagementVO("가장 예쁜 생각을 너에게 주고싶다", "나태주", "시", "알에이치코리아", "1"));
		bookData.put("봉제인형 살인사건", new BookManagementVO("봉제인형 살인사건", "다니엘 콜", "외국소설", "북플라자", "6"));
		bookData.put("일인칭 단수", new BookManagementVO("일인칭 단수", "무라카미 하루키", "외국소설", "문학동네", "3"));
		bookData.put("골든슬럼버", new BookManagementVO("골든슬럼버", "존 그리샴", "외국소설", "웅진지식하우스", "1"));
		bookData.put("커피마스터클래스", new BookManagementVO("커피마스터클래스", "신기욱", "요리/커피/와인", "북하우스엔", "1"));
		bookData.put("공정하다는 착각", new BookManagementVO("공정하다는 착각", "마이클 샌델", "인문", "와이즈베리", "2"));
		bookData.put("백반기행", new BookManagementVO("백반기행", "허영만", "여행", "가디언", "2"));
	}
	public static void setRentData() {
		rentData.put("nda", new BookManagementVO("nda", "봉제인형 살인사건", "2021-01-10"));
		rentData.put("cool", new BookManagementVO("cool", "백반기행", "2021-01-12"));
		rentData.put("tjdbs", new BookManagementVO("tjdbs", "마음챙김", "2021-01-13"));
		rentData.put("dyddn", new BookManagementVO("dyddn", null, null));
		rentData.put("wjddnjs", new BookManagementVO("wjddnjs", null, null));
		rentData.put("dbswn", new BookManagementVO("dbswn", "녹슨달", "2021-01-10"));
		
	}

}
