<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>404 error</title>
    <meta charset="UTF-8"/>
</head>
<body style="background-color:whitesmoke;">
<center>
    <%@include file="/WEB-INF/jsp/top-login.jsp" %>
    </br></br></br></br></br></br></br>
    <h2><span style="color: tomato">${resources.SORRY}</span></h2>
    <h1 style="color: tomato">${resources.The_Page_Youre_Looking_for_Was_Not_Found}</h1>
    <input type="hidden" name="referer" value="${pageContext.request.requestURI}"/>
    </br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
</center>
</body>
</html>