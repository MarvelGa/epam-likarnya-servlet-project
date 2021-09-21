<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Error page</title>
    <meta charset="UTF-8"/>
</head>
<body style="background-color:whitesmoke;" >
<center>
    <%@include file="/WEB-INF/jsp/top-login.jsp" %>
    </br></br></br></br></br></br></br></br></br></br></br>
    <c:if test="${not empty requestScope.errorMessage}">
        <h1><span style="color:tomato">${requestScope.errorMessage} </span></h1>
    </c:if>
</center>
<input type="hidden" name="referer" value="${pageContext.request.requestURI}"/>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>