<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
My JSP Home
<p/>


<a href="${pageContext.request.contextPath}/offers">Show current offers</a>
<p/>
<a href="${pageContext.request.contextPath}/createoffer">Add a new offer</a>
<p/>


<sql:query var="rs" dataSource="jdbc/TestDB">
select id, name, email, text from offers
</sql:query>
<c:forEach var="row" items="${rs.rows}">
ID: ${row.id} <br/>
Name: ${row.name} <br/>
</c:forEach>


</body>
</html>
