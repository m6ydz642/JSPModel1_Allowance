package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


//오라클 DMBS의 데이터베이스와 연결하여 작업할 DAO클래스
public class MoneyDAO {

	//데이터베이스 관련 작업에 필요한 객체들을 저장할 변수 선언
//	private Connection con; //DB와의 연결정보를 담고 있는 객체를 저장할 변수선언
//	private PreparedStatement pstmt;//DB와 연결후 DB에 SQL문을 전달하여 실행할 객체를 저장할 변수 선언
//	private DataSource dataFactory;//커넥션풀 역할을 하는 객체를 저장할 변수 선언
//	private ResultSet rs;//검색한 결과 데이터를 임시로 저장할 테이블 형식의 구조를 갖는 객체를 저장할 변수 선언
//	
//public static final String DRIVER = "com.mysql.jdbc.Driver";
//public static final String DBURL = "jdbc:mysql://localhost:3306/2020money";
//public static final String DBID = "root";
//public static final String DBPW = "1234";
	
	
	// 전역변수로 선언
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";

		public void resourceClose() {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("자원해제 실패 : " + e);
			}
		}// resourceClose()

		private Connection getConnection() throws Exception {

			Connection con = null;
			Context init = new InitialContext();
			// 커넥션풀 얻기
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/2020money");
			// 커넥션풀로 부터 커넥션 객체 빌려와서 얻기
			con = ds.getConnection();

			return con; // 커넥션을 반환

		}
		
		
	//커넥션풀(DataSource)객체를 얻는 생성자
	public MoneyDAO() {
	
		
	}//DAO생성자 끝
	
	//DB에 새 정보를 추가시킬 메소드
		//MemberBean객체에 저장된 입력한 회원정보가 매개변수에 전달되어옴
	public void addMember(MoneyBean MoneyBean){
		try {
			//1.DB연결 : DataSource(커넥션풀)로부터 Connection(접속객체)얻기 
		//	con = dataFactory.getConnection();
			con= getConnection();
			
			//getter메소드들을 이용해 DB에 추가할 정보를 가져 옵니다.
			String usedetails = MoneyBean.getUsedetails();
			int amount = MoneyBean.getAmount();
			String usetype = MoneyBean.getUsetype();
		//	Date getDate = MoneyBean.getDate();
			
			
			//2.SQL구문 만들기 (INSERT)
			// String query = "insert into money(moneyid, usedetails,amount,usetype)values(moneyseq.nextval,?,?,?)";
			
			String query = "insert into 사용금액 (moneyid,사용내역,사용금액,사용구분)values(moneyid,?,?,?)";
			//3.위의 insert문장을 실행할 실행 객체 얻기
			//-> ?에 대응되는 값을 제외한 insert문장을 임시로 OraclePreparedStatementWrapper실행객체에담아
			//   OraclePreparedStatementWrapper실행객체 반환받기 
			pstmt = con.prepareStatement(query);
			//4. ?기호에 대응되는값을 OraclePreparedStatementWrapper실행객체에 설정
			pstmt.setString(1,usedetails);
			pstmt.setLong(2,amount);
			pstmt.setString(3,usetype);
			
			
			//5.OraclePreparedStatementWrapper실행객체를 이용하여 INSERT전체 문자을 DB에 전송하여 실행
			pstmt.executeUpdate();//insert
				
		} catch (Exception e) {
			System.out.println("addMember메소드 내부에서 SQL실행 오류 : " + e);
		} finally {			
			resourceClose();	
		}//finally
		
	}//addMember메소드 끝
	
	
	//DB의 모든 회원정보를 조회(검색)해서 반환 해주는 메소드
	public List listMembers(){
		
		//DB의 모든 회원정보들을 조회(검색)하여 얻어오는데..
		//한사람의 회원정보씩 MemberBean객체에 저장후.....
		//MemberBean객체들을? ArrayList배열에 추가하여 저장 하기 위해 ArryList배열 생성
		List list = new ArrayList();
		// List <MmeberBean> list = new ArrayList<MemberBean>();
		
		
		try {


			System.out.println("드라이버 로드 성공!");
			//1.DB연결 : DataSource(커넥션풀)로부터 Connection(접속객체)얻기 
		// con = dataFactory.getConnection();
			con = getConnection();

			//2.SQL문 (SELECT)
			//-> 회원정보를 최근 가입일순으로 내림차순 정렬 하여 조회(검색)할 SQL문 만들기
			String query = "select * from 사용금액 order by 날짜 desc";
			
			//3.select구문을 실행할 OraclePreparedStatmentWrapper실행객체 얻기
			pstmt = con.prepareStatement(query);
			
			//4.select구문 실행
			//위의 query변수에 저장된 select문장을 DB에 전송 하여 검색한 그결과를 MemberDAO.java페이지로
			//전달 받기 위해...
			//검색결과를 테이블 형식의 구조로 저장할 임시 저장소 역할을 하는 객체메모리가 필요하다.
			//그객체가 바로 OracleReusltSetImpl객체 인 것이다.
			//-> OracleResultSetImpl객체에 검색결과를 테이블 구조 형식으로 똑같이 저장하여
			//   OracleResultSetImpl객체 자체를 리턴(반환) 받는다.
			rs = pstmt.executeQuery();
			
			
			//OracleResultSetImpl객체의 구조는 테이블 구조로써 처음에는 커서가 테이블 구조의 
			//컬럼명이 있는 줄을 가리키고 있다.
			//rs.next()메소드를 호출하면 커서 위치가 한줄 아래로 내려오면서 그다음 줄에 검색한 레코드가 존재하는지 묻는다.
			//next()메소드는 그 다음 줄에 검색한 레코드가 존재하면 true를 반환 하고 존재하지 않으면 false를 반환 한다.
			while (rs.next()) {
				
				//오라클의 t_member테이블에 검색한 레코드의 각컬럼값을 OracleReusltSetImpl객체에서 꺼내와서
				//변수에 저장
				
				SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
				
				String usedetails = rs.getString("사용내역");
				int amount = rs.getInt("사용금액");
				String usetype = rs.getString("사용구분");
				// Date joinDate = rs.getDate("joinDate");
				Date joinDate = rs.getDate("날짜");


				
				
				//검색한 한사람의 회원 정보씩 -> MemberBean객체를 생성하여 각변수에 저장
				MoneyBean vo = new MoneyBean(usedetails, amount, usetype, joinDate);

				//ArrayList배열에 검색한 회원 한명의 정보를 담고 있는 MemberBean객체를 추가
				list.add(vo);
			}
			System.out.println("select 구문 정상실행");
					
		} catch (Exception e) {
			System.out.println("listMembers메소드 내부에서 SQL실행 오류 : " + e);
		} finally {
			resourceClose();
		}
		return list; //검색한 회원정보들(MemberBean객체들)을 저장하고 있는 ArrayList를 
				     //member.jsp로 반환
	}
	
}//DAO클래스 끝









