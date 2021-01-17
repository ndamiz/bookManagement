package data;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class AdminMenu extends UserMenu{
	Scanner scan = new Scanner(System.in); 	
	String select;
	boolean bo =true, bo1 = true;
	String pattern = "^[0-9]*$";
	String zz= "성함", xx= "연릭처",  cc= "아이디", vv="비밀번호";
	public AdminMenu() { }
	
	public void book() {
		while(true) {
		select = conInput("\n[1.도서목록]  [2.도서 재고관리]  [3. 이전메뉴]");

		if(select.equals("1")) { //도서목록 
			super.rentOk();
		}else if(select.equals("2")) { //도서 재고관리 (1. 도서 추가, 2.도서 삭제)
			String se1 = conInput("\n[1.신규도서 등록]  [2.재고 입고/출고 등록]  [3.도서 삭제]  [4. 이전메뉴]");
			if(se1.equals("1")) { //도서 추가 
				while(true) {
					String putBook = conInput("\n 도서명 : ");
					String putAuthor = conInput("저자 : ");
					String putGenre = conInput("장르 : ");
					String putpublisher = conInput("출판사 : ");
					String putStock = conInput("입고량 : ");
					boolean pt = Pattern.matches(pattern, putStock);
					if(pt == false) {System.out.println("숫자만 입력하세요"); 
					}else {
					DataSet.bookData.put(putBook, new BookManagementVO(putBook, putAuthor, putGenre, putpublisher, putStock));
					System.out.println(putBook + "추가되었습니다. ");
					}
				//추가로 더 입고할건지 
				String qst = conInput("[1]추가 신규도서 등록  [2]이전 메뉴 ");
				if(qst.equals("2")) break; //반복문 탈출
				}//1번선택 while end 
				
			}else if(se1.equals("2")) { //재고 입고등록 
				while(true) {
					try {
						String repBook = conInput("재고 수정 도서명 : " );
						BookManagementVO vo = DataSet.bookData.get(repBook); //입력안할경우오류  	
						if( repBook.equals(vo.getBookName())) {
							System.out.println("===["+repBook+"]정보===========================================================================================");
							System.out.printf("%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-5.5s\t\t\n","[도서명]","[저자]","[장르]","[출판사]","[재고량]");
							if(vo.getBookName().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookName());
							else if(vo.getBookName().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookName());
								
							if(vo.getBookAuthor().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookAuthor());
							else if(vo.getBookAuthor().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookAuthor());
								
							if(vo.getBookGenre().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookGenre());
							else if(vo.getBookGenre().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookGenre());
								
							if(vo.getPublisher().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getPublisher());
							else if(vo.getPublisher().length() >=5) System.out.printf(" %-10.10s\t", vo.getPublisher());
								
							System.out.printf("%-10.10s\n", vo.getStock());
							System.out.println("===============================================================================================================\n");

							
							while(true) {
								bo1 = true;
								try {
								String putStoct = conInput("입/출고 수량 (입고는 양수로 입력, 출고는 음수로 입력) :" );
								if(Integer.parseInt(putStoct) == 0) {
									System.out.println("입력 값이 0개 이므로 입/출고 수량에 변화가 없습니다.");
									break;
								}else if (Integer.parseInt(vo.getStock()) + Integer.parseInt(putStoct) < 0) { 
									System.out.println("재고량보다 많은 수를 입력하셨습니다. 출고가 불가합니다.");
									break;
								}else{
									while(true) {
										String yn = conInput("["+repBook +"] 도서에 ["+putStoct+ "개] 입/출고 맞으십니까? [Y/N]");
										if(yn.equalsIgnoreCase("y")) {
											if(Integer.parseInt(putStoct) < 0) {
												System.out.println( putStoct +"개 출고처리 되었습니다. ");
											}else if(Integer.parseInt(putStoct) > 0){
												System.out.println(putStoct + "개 입고처리 되었습니다. ");
											}
											vo.setStock(Integer.toString(Integer.parseInt(vo.getStock()) + (Integer.parseInt(putStoct))));
											bo1=false;
											break;
										}else if(yn.equalsIgnoreCase("n")) {
											break;
										}else {
											System.out.println("잘못입력하셨습니다.");
										}
									}//end while 
								}// end else
								}catch(NumberFormatException nfe) {
									System.out.println("숫자만 입력이 가능합니다.");
								}
								if (bo1 == false) break;
							}//end while
							while(true) {
							String qst = conInput("[1]추가 입고 등록  [2]이전 메뉴 ");
							if(qst.equals("2")) {bo = false; break; }//반복문 탈출
							else if(qst.equals("1")) break;						
							else { System.out.println("잘못입력하였습니다.");}
							}//while
							
							
						}
						if(bo==false) break;
						}catch(NullPointerException ne) {
							System.out.println("잘못입력하셨습니다.");
						}
				}
			}else if(se1.equals("3")) { //도서삭제
				try {
					while(true) {
						String putBook = conInput("삭제할 도서명 : " );
						BookManagementVO vo = DataSet.bookData.get(putBook); //입력안할경우오류  
					
					if( putBook.equals(vo.getBookName())) {
						System.out.println("===["+putBook+"]정보==========================================================================================");
						System.out.printf("%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-10.10s\t\t%-5.5s\t\t\n","[도서명]","[저자]","[장르]","[출판사]","[재고량]");
						if(vo.getBookName().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookName());
						else if(vo.getBookName().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookName());
							
						if(vo.getBookAuthor().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookAuthor());
						else if(vo.getBookAuthor().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookAuthor());
							
						if(vo.getBookGenre().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getBookGenre());
						else if(vo.getBookGenre().length() >=5) System.out.printf(" %-10.10s\t", vo.getBookGenre());
							
						if(vo.getPublisher().length() <5 ) System.out.printf(" %-10.10s\t\t", vo.getPublisher());
						else if(vo.getPublisher().length() >=5) System.out.printf(" %-10.10s\t", vo.getPublisher());
							
						System.out.printf("%-10.10s\n", vo.getStock());
						System.out.println("===============================================================================================================\n");

						while(true) {
							String yn = conInput(putBook +"도서 삭제 확인 [Y/N]");
							if(yn.equalsIgnoreCase("y")) {
								System.out.println("삭제처리 되었습니다. ");
								DataSet.bookData.remove(putBook);
								break;
							}else if(yn.equalsIgnoreCase("n")) {
								break;
							}else {
								System.out.println("잘못입력하셨습니다.");
							}
						}//end while
						while(true) {
							bo1 = true;
							String qst = conInput("[1]추가 삭제 등록  [2]이전 메뉴 ");
							if(qst.equals("2")) {
								bo1 = false; 
								break; 
							}
							else if(qst.equals("1")) break ; 
							else System.out.println("잘못입력하였습니다."); 
						}//while
						if(bo1==false) break; 
					}//if
					}//while 도서삭제
				}catch(NullPointerException ne) {
					System.out.println("잘못입력하셨습니다.");
				}
			}else if(se1.equals("4")){ //이전메뉴 
				System.out.println("이전메뉴로 돌아갑니다.");
				break;
				}
		}else if(select.equals("3")) { //이전메뉴 
			System.out.println("이전 메뉴로 돌아갑니다.");	
			break;
		}else { // 잘못누름 
			System.out.println("메뉴에 맞는 숫자를 입력해주세요");
		}
		
		}
	} 
	public void user() {
		while(true) {
			select = conInput("\n[1.회원목록]  [2.회원삭제]  [3. 이전메뉴]");
			
			if(select.equals("1")) {
			System.out.println("\n===[회원목록]===============================================================");
			Set<String> rList = DataSet.userData.keySet();
			Iterator<String> ii = rList.iterator();
			System.out.printf("%-10s%-15s\t%-10s\t%-10s\n", "성함", "연락처", "아이디", "패스워드" );
			while(ii.hasNext()) {
				BookManagementVO vo = DataSet.userData.get(ii.next());
				System.out.printf("%-10s%-15s\t%-10s\t%-10s\n",vo.getUserName(), vo.getUserTel(), vo.getUserId(), vo.getUserPwd());
			}//end while
			System.out.println("============================================================================\n");
			}else if(select.equals("2")){//회원삭제
				while(true) {
					bo=true;
					boolean boo = true;
					String delId = conInput("삭제할 회원 아이디 : " );
					if(delId.length()<2){
						System.out.println("해당 아이디의 회원은 없습니다. ");
						while(true) {
							String select = conInput("[1. 다시입력]  [2.이전메뉴]" );
							if(select.equals("2")) {
								boo = false;
								break;
							}else if(select.equals("1")) {
								break;
							}else {
								System.out.println("잘못 입력 하였습니다.");
							}
						}
					if(boo==false) break;
					}else {
						try {
							BookManagementVO vo = DataSet.userData.get(delId);
							BookManagementVO vo1 = DataSet.rentData.get(delId);
							
							System.out.printf("===개인정보 ==================\n이름 : %s\n연락처 : %s\n아이디 : %s\n비밀번호 : %s\n"
									+ "===============================", 
									vo.getUserName(), vo.getUserTel(), vo.getUserId(), vo.getUserPwd());
	
							if(!(vo1.getRentDay().equals("")) ){
								System.out.println("\n" + vo1.getRentDay() + "에 대여한 " +vo1.getBookName() + "도서가 있어 탈퇴가 불가합니다.\n");
								continue;
							}
						}catch(NullPointerException nee) {	
							while(true) {
								String check = conInput("\n회원삭제를 하시겠습니까 Y / N");
								if(check.equalsIgnoreCase("y")) {
									System.out.println("회원삭제 되었습니다. 메인화면으로 돌아갑니다.");
									DataSet.userData.remove(delId);
									DataSet.rentData.remove(delId);
									break; //메인화면나가야함 
								}else if (check.toLowerCase().equals("n")) {
									System.out.println("이전화면으로 돌아갑니다.");
									break;
								}else System.out.println("잘못 입력 하였습니다.");
							}//end while
							//if(bo==false) break;
							break;
						}//end catch 
					}//end else
				}//end while
			}else if(select.equals("3")) {//3. 이전메뉴
				break;
			}else { //키 잘못누름 
				System.out.println("메뉴에 맞는 숫자를 입력해주세요");
			}
		}//while
	}
	
	public void logout() {
		
	}
	public String conInput(String msg) {
		System.out.print(msg);
		return scan.nextLine();
	}
}
