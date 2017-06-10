<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Offer</title>
</head>
<body>
My JSP Create Offer

<p/>


    <form action="${pageContext.request.contextPath}/docreateoffer" method="get">
      Name:&nbsp;
      <input type="text" name="name" size="10" />
      <br><br>

      Email:&nbsp;
      <input type="text" name="email" size="10" />
      <br><br>

      Offer:&nbsp;
      <input type="text" name="offer" size="100" />
      <br><br>

      <input type="submit" value="Create Offer">
    </form><br>


</body>
</html>