package data;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class UserMenu {
	Scanner scan = new Scanner(System.in); 
	String uId = Login.uId;
	String select1;
	boolean bo2 =true;
	String a = "도서명", b = "저자", c = "장르", d="출판사", 	e="재고량";
	public static boolean bo=true;
	public UserMenu() { }
	public void userSearch() {	
		while(true) {
			System.out.println("\n[도서검색 및 목록]");
			String search = conInput("검색할 도서명을 입력하세요 (Enter 입력 시 전체 도서목록 확인 가능합니다.)");
			Set<String> keySet = DataSet.bookData.keySet();
			Iterator<String> i = keySet.iterator();
			int count = 0;

			System.out.println("===============================================================================================================");
			while(i.hasNext()) {
				String key = (String)i.next();
				BookManagementVO vo = DataSet.bookData.get(key);
				if(vo.getBookName().indexOf(search) >= 0) {
					if(count ==0) {
						System.out.printf("%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-5.5s\t\t\n","[도서명]","[저자]","[장르]","[출판사]","[재고량]");	
					}
					if(vo.getBookName().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookName());
					else if(vo.getBookName().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookName());
					
					if(vo.getBookAuthor().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookAuthor());
					else if(vo.getBookAuthor().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookAuthor());
					
					if(vo.getBookGenre().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookGenre());
					else if(vo.getBookGenre().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookGenre());
					
					if(vo.getPublisher().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getPublisher());
					else if(vo.getPublisher().length() >=5) System.out.printf(" %-10.10s\t", vo.getPublisher());
					
					System.out.printf("%-10.10s\n", vo.getStock());
					count++;
				}
			}//end while
			
			if(count ==0) {
				System.out.println("\n\n검색한 도서는 현재 보유하고 있지 않습니다.");
			}
			System.out.println("===============================================================================================================\n");
			while (true){
				String exit = conInput("[1. 재검색]\t[2.메뉴돌아가기] ");
				if(exit.equals("2")) {
					System.out.println("메뉴로 돌아갑니다.\n");
					bo2 = false;
					break;
				}else if(exit.equals("1")) {
					System.out.println("도서 재검색 합니다.\n");
					break;
				}else {
					System.out.println("잘못 입력 하셨습니다.\n");
				}
			}
			if(bo2 == false) break;
		}//end while
	}
	public void rentOk() {
		System.out.println("\n===[도서목록]==================================================================================================");
		Set<String> rList = DataSet.bookData.keySet();
		Iterator<String> ii = rList.iterator();
		System.out.printf("%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-5.5s\t\t\n","[도서명]","[저자]","[장르]","[출판사]","[재고량]");	
		while(ii.hasNext()) {
			BookManagementVO vo = DataSet.bookData.get(ii.next());
			if(vo.getBookName().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookName());
			else if(vo.getBookName().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookName());
			
			if(vo.getBookAuthor().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookAuthor());
			else if(vo.getBookAuthor().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookAuthor());
			
			if(vo.getBookGenre().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookGenre());
			else if(vo.getBookGenre().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookGenre());
			
			if(vo.getPublisher().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getPublisher());
			else if(vo.getPublisher().length() >=5) System.out.printf(" %-10.10s\t", vo.getPublisher());
			
			System.out.printf("%-10.10s\n", vo.getStock());
		}//end while
		System.out.println("===============================================================================================================\n");
	}
	public void bookRent() {
		Calendar now = Calendar.getInstance();
		String ptn = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(ptn);
		String today = sdf.format(now.getTime());
		System.out.println("\n===[도서 대여 및 반납]===========================================================================================");
		while(true) {	
			try {
				BookManagementVO vo = DataSet.rentData.get(uId);
				System.out.println("---[현재 대여이력]-------------------------------------");
				if(!(vo.getRentDay()==null)) {
				System.out.printf("%s\t\t%-10.10s\n" , "[대여날짜]", "[도서명]");
				System.out.printf("%s\t\t%-10.10s\n", vo.getRentDay(), vo.getBookName());
				System.out.println("-------------------------------------------------------\n");
				}else {
					System.out.println("현재 대여중인 도서가 없습니다.");
					System.out.println("-------------------------------------------------------\n");
				}
			}catch(NullPointerException ne) {
				System.out.println("현재 대여중인 도서가 없습니다.");
				System.out.println("-------------------------------------------------------\n");
			}//end try
		while(true) {
			String select = conInput("\n[1.도서대여]  [2.도서반납]  [3.메뉴 돌아가기]");
			if(select.equals("1")) {
				BookManagementVO vo = DataSet.rentData.get(uId);
				if (!(vo.getBookName() == null)) {
					System.out.println("\n대여는 1인당 1권씩만 가능합니다. 반납 후 대여 가능 합니다.");
					break;
				}else {
					while(true){
						String bName = conInput("\n대여하실 도서명을 입력해주세요. (Enter 입력 시 대여가능한 도서목록 확인 가능합니다.)");
						try {	
							BookManagementVO vo1 = DataSet.bookData.get(bName);
							int stock = Integer.parseInt(vo1.getStock());
							//정확한 도서 명을 입력하였을 경우 
							if(stock > 0 && vo.getBookName() == null) { 
								DataSet.rentData.put(uId, new BookManagementVO(uId, bName, today)); //렌트 목록에 저장..  
								vo1.setStock(Integer.toString(stock-1));
								System.out.println("\n["+bName +"] 은/는 "+ today +" 대여 완료 되었습니다.\n");
								break;
							}else if (stock == 0) {
								System.out.println("\n[" + bName + "] 은/는 재고량이 없어 대여 불가합니다.\n");
							}
						}catch(NullPointerException ne) {
							// 단어만 입력 했을 경우 ?
							Set<String> keySet = DataSet.bookData.keySet();
							Iterator<String> i = keySet.iterator();
							int count = 0;
							System.out.println("\n대여를 원하는 도서명을 확인 후 정확하게 입력해주세요");
							while(i.hasNext()) {
								String key = (String)i.next();
								BookManagementVO vo2 = DataSet.bookData.get(key);
								if(vo2.getBookName().indexOf(bName) >= 0) {
									if(count==0) {
										System.out.printf("%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-5.5s\t\t\n","[도서명]","[저자]","[장르]","[출판사]","[재고량]");
									}
									if(vo2.getRentDay() == null) {
										if(vo2.getBookName().length() <5 ) System.out.printf(" %-10.10s\t\t", vo2.getBookName());
										else if(vo2.getBookName().length() >=5) System.out.printf(" %-10.10s\t", vo2.getBookName());
										
										if(vo2.getBookAuthor().length() <5 ) System.out.printf(" %-10.10s\t\t", vo2.getBookAuthor());
										else if(vo2.getBookAuthor().length() >=5) System.out.printf(" %-10.10s\t", vo2.getBookAuthor());
										
										if(vo2.getBookGenre().length() <5 ) System.out.printf(" %-10.10s\t\t", vo2.getBookGenre());
										else if(vo2.getBookGenre().length() >=5) System.out.printf(" %-10.10s\t", vo2.getBookGenre());
										
										if(vo2.getPublisher().length() <5 ) System.out.printf(" %-10.10s\t\t", vo2.getPublisher());
										else if(vo2.getPublisher().length() >=5) System.out.printf(" %-10.10s\t", vo2.getPublisher());
										
										System.out.printf("%-10.10s\n", vo2.getStock());
									}
									count ++;
								}//end if
							}//end while
							if (count==0) {
							System.out.println("\n입력하신 내용과 일치하는 도서가 없습니다.");
							}
						}//end catch
						System.out.println();
					}// end else if
				}//end while
			}else if(select.equals("2")) {
				
				try {
					BookManagementVO vo1 = DataSet.rentData.get(uId);
					BookManagementVO vo = DataSet.bookData.get(vo1.getBookName());
					if (vo1.getBookName() ==null) {
						System.out.println("\n반납할수 없습니다. 대여중인 도서가 없습니다.");
					}else {
					select1 = conInput("\n1.["+vo1.getBookName()+"] 반납 \n2.이전 메뉴 돌아가기");
					}
					if(select1.equals("1")){
						if(!(vo1.getRentDay()==null)) {
							vo.setStock(Integer.toString (Integer.parseInt(vo.getStock())+1));
							System.out.println("\n[" +vo1.getBookName()+ "] 은/는 " + today +" 반납 완료 되었습니다.");
							DataSet.rentData.put(uId, new BookManagementVO(uId, null, null));
							break;
						}
					}else if (select1.equals("2")) {
						System.out.println("이전 메뉴로 돌아갑니다");
						break;
						
					}else {
						System.out.println("잘못입력하셨습니다.");
					}
				}catch(NullPointerException ne) {
					
				}
			}else if(select.equals("3")) {
				bo2 = false;
				System.out.println("이전 메뉴로 돌아갑니다. ");
				break;
			}else{
				System.out.println("\n잘못 입력 하셨습니다.");				
			}
		}//end while	
		if(bo2 == false) {
			break;
		}
		}//end while
	}

	public void infor() {
		BookManagementVO vo = DataSet.userData.get(uId);
		while(true) {
		bo = true; 
		System.out.println("===개인정보 ============");;
		System.out.printf("이름 : %s\n연락처 : %s\n아이디 : %s\n비밀번호 : %s\n", vo.getUserName(), vo.getUserTel(), vo.getUserId(), vo.getUserPwd());
		System.out.println("========================");
		String change = conInput("[1.개인정보 수정]  [2. 회원탈퇴]  [3.메뉴 돌아가기] ");
			if(change.equals("1")) {
				while(true) {
					String select = conInput("[1. 연락처 수정]  [2.비밀번호 수정]  [3.이전 단계]\n(아이디와 성함은 수정이 불가합니다)");
					if(select.equals("1")) {
						while(true) {
							try {
							System.out.println("수정하실 연락처를 입력해 주세요 ");
							String tel = conInput("연락처 (숫자만 입력해 주세요) : 010-");
		
							if (Integer.parseInt(tel) == Integer.parseInt(tel)) //catch로 이동
							if(tel.length()<8 || tel.length()>8) {
								System.out.println("번호를 잘못 입력 하셨습니다. (010을 제외한 숫자 8자리 입력 해주세요) ");
							}else {
								System.out.println("연락처 수정이 완료되었습니다.\n");
								vo.setUserTel("010-"+tel.substring(0, 4)+"-"+tel.substring(4, 8));
								break;
							}
							}catch(NumberFormatException nfe) {
								System.out.println("010을 제외한 숫자 8자리를 입력해주세요.");		
							}
						}//end while
					}else if(select.equals("2")) {
						while(true) {
							System.out.println("수정하실 비밀번호를 입력해 주세요 ");
							String pwd =conInput("비밀번호 (4자리이상만 가능합니다) : ");
							if(pwd.length() <4) System.out.println("4자리 이상 입력하세요");
							else {
								vo.setUserPwd(pwd);
								System.out.println("비밀번호 수정이 완료되었습니다.\n");
								break;
							}
						}
					}else if(select.equals("3")) {
						System.out.println("이전 메뉴로 돌아갑니다.\n");
						break;
					}else {
						System.out.println("잘못입력하셨습니다.");
					}
				}//end while
			}else if(change.equals("2")) {
				while(true) {
					String check = conInput("회원탈퇴를 하시겠습니까 Y / N");
					if(check.toLowerCase().equals("y")) {  
						System.out.println("본인 확인을 위하여 아이디와 비밀번호를 입력해 주세요");
						String id = conInput("아이디 : ");
						String pwd = conInput("비밀번호 : ");
						if(id.equals(vo.getUserId()) && pwd.equals(vo.getUserPwd())) {
							System.out.println("회원탈퇴 되었습니다. 메인화면으로 돌아갑니다.");
							DataSet.userData.remove(id);
							DataSet.rentData.remove(id);
							bo = false;
							break; //메인화면나가야함 
						}else {
							System.out.println("잘못입력하셨습니다.");
						}
					}else if (check.toLowerCase().equals("n")) {
						System.out.println("이전화면으로 돌아갑니다.");
						break;
					}else System.out.println("잘못 입력 하였습니다.");
				}//end while

			}else if(change.equals("3")) {
				System.out.println("메뉴로 돌아갑니다.\n");
				break; //while break 
			}else {
				System.out.println("잘못입력하셨습니다.");
			}
			if(bo==false) break;
		}//end while
	}
	
	
	
	
	
	
	public String conInput(String msg) {
		System.out.print(msg);
		return scan.nextLine();
	}
}
