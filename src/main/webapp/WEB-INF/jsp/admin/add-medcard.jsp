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
    <h2 style="color: cadetblue">${resources.New_patients}</h2>
    <p><a href="/likarnya/controller?command=adminCabinet"> ${resources.Go_to_main_admin_page}!</a></p>

    </br>

    <table border="1">
        <tr>
            <th>${resources.First_name}</th>
            <th>${resources.Last_name}</th>
            <th>${resources.B_day}</th>
            <th>${resources.Gender}</th>
        </tr>
        <tr>
            <td>
                ${requestScope.patient.firstName}
            </td>

            <td>
                ${requestScope.patient.lastName}
            </td>

            <td>
                ${requestScope.patient.dateOfBirth}
            </td>

            <td>
                ${requestScope.patient.gender}
            </td>
        </tr>
    </table>
    </br>
    <table>
        <tr>
            <th>
                <label for="category">Choose Category of Doctor</label>
            </th>

            <td>
                <form action="./controller" method="get">
                    <select name="category" required onchange="this.form.submit();">
                        <option value="">--Please choose an option--</option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" ${catValue!=null?(catValue.equals(category.id)?"selected": ""): ""}> ${category.title}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="patientId" value=${requestScope.patient.id}>
                    <input type="hidden" name="command" value="add-medical-card">
                </form>
            </td>
        </tr>
    </table>

    <c:if test="${requestScope.doctors!=null}">
        <c:choose>
            <c:when test="${requestScope.doctors.size()!=0}">
                </br>
                <form action="/likarnya/controller?command=postCreateMedicCard" method="POST">
                    <table>
                        <tr>
                            <th>
                                <label for="doctor">Choose Doctor</label>
                            </th>
                            <td>
                                <select name="doctor" required>
                                    <option value="">--Please choose an option--</option>
                                    <c:forEach items="${requestScope.doctors}" var="doctor" varStatus="doctorId">
                                        <option value=${doctor.id}>${doctor.firstName} ${doctor.lastName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <label>Enter the Complaints of Patient</label>
                            </th>
                            <td>
                                <input type="text" name="complaints" id="complaints" required/>
                            </td>
                        </tr>
                    </table>
                    </br>

                    <th style="'background-color: #228b22'">
                        <input type="submit" value="${resources.Confirm_selected}"/>
                    </th>
                    <input type="hidden" name="patientId" value="${requestScope.patient.id}">
                </form>

            </c:when>
            <c:otherwise>
                <center><h2>${resources.No_doctors_by_this_category}!</h2></center>
            </c:otherwise>
        </c:choose>
    </c:if>
    </br>
</center>
<input type="hidden" name="referer" value="${pageContext.request.requestURI}"/>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
