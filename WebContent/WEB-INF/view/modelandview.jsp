<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ModelAndView</title>
</head>
<body>
My JSP ModelAndView

<p/>

Session Attribute: <%= session.getAttribute("testAttribute") %>
NOTE: The session attribute is still persisted here although it is not explicitly set in the controller.
<p/>

Request Attribute: <%= request.getAttribute("nullModelKey") %>
NOTE: null will be displayed here.
<p/>

Request Attribute: <%= request.getAttribute("testModelKey") %>

<p/><p/>

Session using Express Language (EL): ${testAttribute}

<p/>

Request using Express Language (EL): ${nullModelKey}
NOTE: nothing will be displayed here.
<p/>

Request using Express Language (EL): ${testModelKey}
NOTE: request attribute has higher priority than session attribute with same name
<p/><p/>

Session using JSTL: <c:out value="${testAttribute}" />

<p/>

Request using JSTL: <c:out value="${nullModelKey}" />
NOTE: nothing will be displayed here.
<p/>

Request using JSTL: <c:out value="${testModelKey}" />

<p/>

<c:forEach var="row" items="${offers}">
ID: ${row.id} <br/>
Name: ${row.name} <br/>
Email: ${row.email} <br/>
</c:forEach>

</body>
</html>