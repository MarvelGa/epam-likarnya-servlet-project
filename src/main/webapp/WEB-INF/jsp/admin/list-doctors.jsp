<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${resources.Admin_personal_cabinet}</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="static/topstyle.css">
</head>
<body>
<center>
    <%@include file="/WEB-INF/jsp/admin/top-admin.jsp" %>
    <h2 style="color: cadetblue">${resources.Doctors_list}</h2>

    <form action="./controller" method="GET">
        <input type="hidden" name="command" value="medical-form">
        <input type="submit" value="${resources.Add_a_new_doctor}">
    </form>

    <c:choose>
        <c:when test="${requestScope.doctorsList.size()!=0}">
            </br>
            <%
                int counter = 1;
            %>


            <table border="1">
                <tr>
                    <th>No.</th>
                    <th>${resources.First_name}</th>
                    <th>${resources.Last_name}</th>
                    <th>${resources.Category}</th>
<%--                    <th>${resources.Gender}</th>--%>
                </tr>
                <c:forEach var="record" items="${requestScope.doctorsList}">
                    <tr>
                        <td>
                            <%=counter++%>
                        </td>

                        <td>
                                ${record.firstName}
                        </td>

                        <td>
                                ${record.lastName}
                        </td>

                        <td>
                                ${record.category}
                        </td>

<%--                        <td>--%>
<%--                                ${record.gender}--%>
<%--                        </td>--%>

                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <center><h2>${resources.No_doctors_yet}!</h2></center>
        </c:otherwise>
    </c:choose>
    <br>

</center>
<input type="hidden" name="referer" value="${pageContext.request.requestURI}" />
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
