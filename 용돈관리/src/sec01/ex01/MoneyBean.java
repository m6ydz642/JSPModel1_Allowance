package sec01.ex01;

import java.sql.Date;

//자바빈 클래스 종류
// DAO클래스
// VO클래스 


//VO클래스의 역할을 하는 클래스    // ->  멤버변수,  생성자 생략가능  ,  getter setter메소드  생성
//ValueObject
//1. DB로부터 하나의 회원정보를 검색해서 가져와서 각각의 변수에 저장할 용도의 클래스 
//2. 회원가입 디자인 페이지에서 입력한 회원정보들을  DB에 INSERT하기전에 
//   임시로 각각의 변수에 저장해 놓을수 있는 공간인 클래스로!!
//   DB에 가입할 회원정보를  객체 단위로  INSERT 할 용도의 클래스 
public class MoneyBean {  
	//멤버변수 선언
	//t_member테이블의 구조와 똑같은 타입의 변수명으로  변수를 선언 합니다.
   private String usedetails;
   private int amount; // 사용금액
   private String usetype; // 사용구분
   private Date joinDate; // 입력시 날짜 되게
		
   //아무일도 하지 않는 생성자 
   public MoneyBean() {}

	

public MoneyBean(String usedetails, int amount, String usetype, Date joinDate) {
	super();
	this.usedetails = usedetails;
	this.amount = amount;
	this.usetype = usetype;
	this.joinDate = joinDate;
}



public String getUsedetails() {
	return usedetails;
}





public void setUsedetails(String usedetails) {
	this.usedetails = usedetails;
}





public int getAmount() {
	return amount;
}





public void setAmount(int amount) {
	this.amount = amount;
}





public String getUsetype() {
	return usetype;
}





public void setUsetype(String usetype) {
	this.usetype = usetype;
}





public Date getJoinDate() {
	return joinDate;
}





public void setJoinDate(Date joinDate) {
	this.joinDate = joinDate;
}








  
   //위 변수값을 초기화 시킬 생성자
 
	
    
   
   
     
    //alt + shift  +   s   r
    //getter , setter메소드 만드는 단축키
   
    //이클립스 메뉴 중에 Source메뉴를 클릭해  getter and setters..클릭
   
    //이클립스 빈공간에 마우스 오른쪽 클릭 -> Source메뉴 클릭 ->   getter and setters..클릭
    
   
   
   
  //get메소드들.... set메소드들....
  //getter, setter메소드들
  
  //get메소드 만드는 규칙
  //1. get접두사를 붙인다
  //2. getId(){}
  //3. String  getId(){ return  id }  <-- 리턴타입을 지정해 줘야 하며 멤버변수값을 반환시 사용
  //4. 매개변수는 X
   
  //set메소드를 만드는 규칙
  //1. set이라는 접두사를 붙인다
  //   set
  //2. set다음에 변수명을 붙이는데 변수명의 첫번째 글자를 대문자로 작성해서 붙인다
  //   setId(){}
  //3. 리턴값이 없으므로  void형으로 설정 해야한다
  //   void setId(){}
  //4. 매개변수가 있어야 한다
  //   void setId(String id){ 
  //   }
  //5. 매개변수로 전달받은 값을 클래스의 멤버변수에 저장할 용도이다
   //   void setId(String id){    
   //     this.id = id; 
   //   }  
   
   

}






