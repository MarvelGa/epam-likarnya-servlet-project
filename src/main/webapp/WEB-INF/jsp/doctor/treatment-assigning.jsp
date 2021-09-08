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
    <h2 style="color: cadetblue">${resources.Patient_for_diagnosis}</h2>

    <%--    <form action="./controller" method="GET">--%>
    <%--        <input type="hidden" name="command" value="patient-form">--%>
    <%--        <input type="submit" value="${resources.Add_new_patient}">--%>
    <%--    </form>--%>

    </br>
    <form action="/likarnya/controller?command=postTreatmentAssigning" method="POST">
        <table border="1">
            <tr>
                <th>${resources.First_name}</th>
                <th>${resources.Last_name}</th>
                <th>${resources.B_day}</th>
                <th>${resources.Gender}</th>
            </tr>
            <tr>
                <td>
                    ${onePatient.firstName}
                </td>

                <td>
                    ${onePatient.lastName}
                </td>

                <td>
                    ${onePatient.dateOfBirth}
                </td>

                <td>
                    ${onePatient.gender}
                </td>
            </tr>
        </table>


        <table>
            <tr>
                <th>
                    <label>Enter the Diagnosis of Patient</label>
                </th>
                <td>
                    <input type="text" name="diagnosis" id="diagnosis" required/>
                </td>
            </tr>
            </br>

            <tr>
                <th>
                    <label for="appointment">Choose an Appointment</label>
                </th>

                <td>
                    <select id="appointment" name="appointment" required>
                        <option value="">--Please choose an option--</option>
                        <option value="PROCEDURE">
                            PROCEDURE
                        </option>
                        <option value="DRUG">
                            DRUG
                        </option>
                        <option value="OPERATION">
                            OPERATION
                        </option>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="Confirm"/>
                </td>
            </tr>
        </table>
        <input type="hidden" name="patientId" value="${onePatient.id}">
    </form>
    <br>

</center>
<input type="hidden" name="referer" value="${pageContext.request.requestURI}"/>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
