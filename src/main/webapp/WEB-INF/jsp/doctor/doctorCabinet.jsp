<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${resources.Doctor_personal_cabinet}</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="static/topstyle.css">
</head>
<body>
<center>
    <%@include file="/WEB-INF/jsp/doctor/top-doctor.jsp" %>

    <c:choose>
        <c:when test="${requestScope.doctorPatients.size()!=0}">
            </br>
            <%
                int counter = 1;
            %>
            <h2 style="color: cadetblue">${resources.Patient_for_diagnosis}</h2>
            <table border="1">
                <tr>
                    <th>No.</th>
                    <th>${resources.First_name}</th>
                    <th>${resources.Last_name}</th>
                    <th>${resources.B_day}</th>
                    <th>${resources.Gender}</th>
                    <th>${resources.Complaints}</th>
                    <th colspan="2">${resources.Operation}</th>
                </tr>
                <c:forEach var="record" items="${requestScope.doctorPatients}">
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
                                ${record.dateOfBirth}
                        </td>

                        <td>
                                ${record.gender}
                        </td>

                        <td>
                                ${record.complaints}
                        </td>

                        <td>
                            <form action="./controller" method="GET">
                                <input type="hidden" name="command" value="add-treatment">
                                <input type="submit" value="${resources.Open_medical_card}">
                                <input type="hidden" name="patientId" value="${record.id}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <center><h2>${resources.No_patient_yet}!</h2></center>
        </c:otherwise>
    </c:choose>
    <br>

</center>
<input type="hidden" name="referer" value="${pageContext.request.requestURI}"/>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
