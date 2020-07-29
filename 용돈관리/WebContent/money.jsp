<%@page import="oracle.net.aso.b"%>
<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MoneyDAO"%>
<%@page import="sec01.ex01.MoneyBean"%>
<%@page import="java.sql.DriverManager"%>
   <%@page import="java.sql.Connection"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

    
<%
		//한글 처리
		request.setCharacterEncoding("UTF-8");

%>		

		<jsp:useBean id="m" class="sec01.ex01.MoneyBean" scope="page" />
		<jsp:setProperty property="*" name="m" /><!-- 모든 setter호출 "*" 로 -->
		<jsp:useBean id="MoneyDAO" class="sec01.ex01.MoneyDAO"/>	
<%
	MoneyDAO.addMember(m);
		
		//3.DB작업(SELECT)
		List<MoneyBean> MoneyList = MoneyDAO.listMembers();//DB에 저장된 모든 회원정보 조회 요청
	
		//out.print(membersList.size());//검색한 회원 정보들(MemberBean객체들)의 갯수 출력
%>    

   

  
	<table align="center" width="100%">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">사용내역</td>
			<td width="7%">금액</td>
			<td width="5%">사용구분</td>
			<td width="11%">작성일</td>
			
		</tr>
				
		<%
	//DB테이블에  조회한 회원이 없으면? 
	if(MoneyList.size() == 0){
%>		
		<tr>
			<td colspan="5">
				<b><span style="font-size:9pt">등록된 자료가 없습니다.</span></b>
			</td>
		</tr>
<%
	}else{//DB테이블에서 조회한 회원정보가 존재 하면?
	 //for반복문을 이용해  ArrayList배열에 저장되어 있는 MemberBean객체를 하나씩 가져온후..
	 //MemberBean객체의 각변수에 저장된 회원정보를 다시 getter메소드를 호출해서 얻은후 출력~
	 for(int i=0;  i<MoneyList.size();  i++){//ArrayList크기 만큼 반복
		 
		 
		 	MoneyBean bean = MoneyList.get(i);
%>	 

	
<!-- DB연결없이 사용하려고 했던 실습같음 (기억이 안남) -->
<%-- 			<td> <jsp:getProperty property="usedetails" name="m"/> </td> --%>
<%-- 			<td> <jsp:getProperty property="amount" name="m"/> </td>   --%>
<%-- 			<td> <jsp:getProperty property="usetype" name="m"/> </td> --%>
			
			<tr align="center"> 
			<td width="7%"><%=bean.getUsedetails() %></td>
			<td width="7%"><%=bean.getAmount() %></td>
			<td width="5%"><%=bean.getUsetype() %></td>
			<td width="5%"><%=bean.getJoinDate() %></td>
<!-- 		</tr>	   -->
<%		 
	 } //end for
	}// end if
%>

		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr>
	</table>
	
	








