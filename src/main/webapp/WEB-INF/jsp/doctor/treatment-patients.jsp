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
    <%--    <center><h1>${resources.Admin_cabinet}</h1></center>--%>
    <h2 style="color: cadetblue">${resources.Patients_for_treatment}</h2>

    <%--    <form action="./controller" method="GET">--%>
    <%--        <input type="hidden" name="command" value="patient-form">--%>
    <%--        <input type="submit" value="${resources.Add_new_patient}">--%>
    <%--    </form>--%>

    <c:choose>
        <c:when test="${patientsForTreating.size()!=0}">
            </br>
            <%
                int counter = 1;
            %>
            <table border="1">
                <tr>
                    <th>No.</th>
                    <th>${resources.Patient}</th>
                    <th>${resources.B_day}</th>
                    <th>${resources.Gender}</th>
                    <th>${resources.Complaints}</th>
                    <th>${resources.Diagnosis}</th>
                    <th>${resources.Treatment}</th>
                    <th>${resources.Treatment_status}</th>
                    <th>${resources.Patients_doctor}</th>
                    <th colspan="5">${resources.Operation}</th>
                </tr>
                <c:forEach var="record" items="${patientsForTreating}">
                    <tr>
                        <td>
                            <%=counter++%>
                        </td>

                        <td>
                                ${record.firstName} ${record.lastName}
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
                                ${record.diagnosis}
                        </td>

                        <td>
                                ${record.appointment}
                        </td>

                        <td>
                                ${record.appointmentStatus}
                        </td>

                        <td>
                                ${record.doctorCategory}  -  ${record.doctorFirstName } ${record.doctorLastName}
                        </td>

                        <td>
                            <form action="./controller" method="GET">
                                <input type="hidden" name="command" value="execute-treatment">
                                <input type="submit" value="${resources.Execute_treatment}">
                                <input type="hidden" name="treatmentId" value="${record.treatmentId}">
                                <input type="hidden" name="statementId" value="${record.statementId}">
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

