package data;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {
	Scanner scan = new Scanner(System.in); 
	public static String uId =null;
	boolean bo = true, bo2 = true;
	public Login() { }
	
	public void userLoginCheck() {
		while(true) {
			Boolean bo = true;
			try {
				System.out.println("\n로그인을 위하여 ID와 PWD를 입력하세요.");
				uId= conInput("아이디 : ");
				String pwd = conInput("비밀번호 : ");
				BookManagementVO vo = DataSet.userData.get(uId);
				if( uId.equals(vo.getUserId()) && pwd.equals(vo.getUserPwd()) ) {
					System.out.println("로그인 성공\n");
					break;
				}else throw new NullPointerException();
			}catch(NullPointerException ne) {
				System.out.println("아이디와 비밀번호 잘못 입력하셨습니다.\n");
				while(true) {
					String select = conInput("[1.로그인 재시도]  [2.메인으로 돌아가기]");
					if(select.equals("1")) break;
					else if (select.equals("2")) {
						uId = null; bo = false; break;
					}else System.out.println("잘못입력 하셨습니다.");
				}
			}//end catch
			if(bo==false) break;
		}//end while
	}
	public void adminLoginCheck() {
		while(true) {
			try {
				System.out.println("관리자 로그인을 위하여 ID와 PWD를 입력하세요.");
				String id = conInput("아이디 : ");
				String pwd = conInput("비밀번호 : ");
				if( id.toLowerCase().equals("master")&& pwd.equals("1234")) {
					System.out.println("관리자 시스템 로그인되었습니다.");
					break;
				}else System.out.println("아이디와 비밀번호를 확인 후 다시입력해주세요.");	
			}catch(NullPointerException ne) {
				System.out.println("아이디와 비밀번호를 확인 후 다시입력해주세요.");
			}
		}//end while
	}
	
	public void userJoin() {
		System.out.println("회원가입을 위한 정보를 입력하세요.");
		System.out.println("잘못된 정보 입력 시 사용이 불가합니다.");
		String pwd = null, name=null, tel=null;
		
		while(true) {	
			try {
				while(true) {
					uId = conInput("아이디 (영어, 숫자, 3자리이상 가능합니다.) : ");
					String pattern = "^[a-zA-Z0-9]*$";
					boolean pt = Pattern.matches(pattern, uId);
					if(pt==false || (uId.length() <3 || uId.length() > 10)) System.out.println("아이디에는 영어, 숫자, 3자리이상만 가능합니다.");
					else{
						BookManagementVO vo = DataSet.userData.get(uId);
						if(uId.equals(vo.getUserId())) System.out.println("중복 아이디입니다. 다른 아이디로 입력해주세요");
					}
				}// end while
			}catch(NullPointerException ne) {
				bo =true;  bo2 = true;
				while(true){
					pwd =conInput("비밀번호 (4자리이상만 가능합니다) : ");
					if(pwd.length() <4) System.out.println("4자리 이상 입력하세요");
						//가입불가 비번다시쳐라고 나와야함 
					else break;
				}//while end
				while(true){
					name = conInput("성함 :" ); //한글, 이름  들어가야해 
					char[] a = name.toCharArray();
					String pattern = "^[a-zA-Z가-힣]*$";
					boolean pt = Pattern.matches(pattern, name);
					if(pt==false)  System.out.println("영/한으로 입력해주세요");
					else {
						if(a.length > 1 ) break;
						else System.out.println("성함을 너무 짧게 입력하셨습니다.");
					}
				}//end while
				while(true) {
					tel = conInput("연락처 (숫자만 입력해 주세요) : 010-");
					try { 
						if (Integer.parseInt(tel) == Integer.parseInt(tel)) //catch로 이동
						if(tel.length()<8 || tel.length()>8) {
							System.out.println("번호를 잘못 입력 하셨습니다. (010을 제외한 숫자 8자리 입력 해주세요) ");
						}else {
							while(true) {
								String yn = conInput("입력한 내용이 맞습니까 [ Y / N ] ");
								if(yn.equals("Y") || yn.equals("y")) {  
									System.out.println("\n가입이 완료되었습니다. 메인메뉴로 돌아갑니다.");
									DataSet.userData.put(uId, new BookManagementVO(name, "010-"+tel.substring(0, 4)+"-"+tel.substring(4, 8), uId, pwd));
									DataSet.rentData.put(uId, new BookManagementVO(uId, null, null));
									bo = false;
									break;
								}else if (yn.equals("N") || yn.equals("n")) {
									String select = conInput("[1. 다시작성]  [2.메인으로 돌아가기]");
									switch(select) {
									case "1": bo2 = false; break;
									case "2": bo = false; break;
									}
								}else System.out.println("잘못 입력 하였습니다.");
								if(bo==false || bo2==false) break;	
							}//end while
						}//end else
					}catch(NumberFormatException nfe) {
							System.out.println("010을 제외한 숫자 8자리를 입력해주세요.");					
					}	
					if(bo==false || bo2==false) break;
				}//while
			}//catch
			if(bo == false) break;
		}//while
	}

	public String conInput(String msg) {
		System.out.print(msg);
		return scan.nextLine();
	}
}
