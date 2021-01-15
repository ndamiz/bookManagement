import java.util.Scanner;

import data.AdminMenu;
import data.BookManagementVO;
import data.DataSet;
import data.Login;
import data.UserMenu;

public class ManagementMain {
	Scanner scan = new Scanner(System.in); 
	boolean bo;
	String userId;
	public ManagementMain() {
		DataSet.setUserData();
		DataSet.setBookData();
		DataSet.setRentData();
	}
	public void start() {
		bo=true;
		while(true) {
			// 메인메뉴
			while(true) {
			String in =conInput("[1.고객로그인]  [2.회원가입]  [0.관리자로그인]"); //나중에 봐서 관리자메뉴에 프로그램종료 넣기 
			Login li = new Login();
			switch(in) {
				case "1": 
					try {
						li.userLoginCheck(); 
						userId = Login.uId;
						BookManagementVO vo = DataSet.userData.get(userId);
						if(!( userId.equals(vo.getUserId()))){ 
							break; //로그인실패하고 메서드 빠져나왓을때 반복돌리기 위함.. 
						}
					}catch(NullPointerException ne) {break;}
					//고객 메뉴 출력 
					while(true) {
							String uMenu =conInput("[1.도서검색]  [2.대여가능 도서목록]  [3.도서 대여 및 반납]  [4.본인정보확인]  [0.로그아웃]");
							UserMenu um = new UserMenu();
							//스위치문 -> 해당 메뉴 메서드 
							switch(uMenu) {
								case"1": 
									um.userSearch();
									break;
								case"2": 
									um.rentOk();
									break;
								case"3": 
									um.bookRent();
									break;
								case"4": 
									try {
									um.infor();
									userId = Login.uId;
									BookManagementVO vo = DataSet.userData.get(userId);
									if(vo.getUserId() == null){ 
										break; 
									}
								}catch(NullPointerException ne) {}
							}//end switch 
							
							if(uMenu.equals("0")) {
								System.out.println("로그아웃 합니다.\n");
								break;
							}
							if(UserMenu.bo == false) {
								break;
							}
					}//end while (고객메뉴) 
					break;
				case "2":
					li.userJoin();	break;
				case "0":
					li.adminLoginCheck();  
					//관리자메뉴 출력 
					while(true) {
						String aMenu =conInput("[1.도서관리]  [2.회원관리]  [3.로그아웃]  [0.프로그램종료]");
						AdminMenu am = new AdminMenu();
						//스위치문 -> 해당 메뉴 메서드 
						switch(aMenu) {
							case"1": 
								am.book();
								break;
							case"2": 
								am.user();
								break;
							case"0": 
								System.exit(0);
						}//end switch 관리자 메뉴선택 
						if(aMenu.equals("3")) {
							System.out.println("로그아웃 합니다.\n");
							break;
						}
					}//end while 관리자 메뉴
				}//end switch 메인메뉴 메뉴선택 
			}	//end while 메인메뉴 
		}//end while
	}
	
	//콘솔 입력받는 메서드
	public String conInput(String msg) {
		System.out.print(msg);
		return scan.nextLine();
	}
	public static void main(String[] args) {
		ManagementMain mm = new ManagementMain();
		mm.start();

	}

}
