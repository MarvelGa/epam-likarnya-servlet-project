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


    </br>
    <table>
        <tr>
            <td>
                <table>
                    <tr>
                        <th>
                            <label for="category">${resources.Choose_category_of_doctor}</label>
                        </th>

                        <td>
                            <form action="./controller" method="get">
                                <select name="category" id="category" required onchange="this.form.submit();">
                                    <option value="">--${resources.Default_or_choose}--</option>
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.id}" ${catValue!=null?(catValue.equals(category.id)?"selected": ""): ""}> ${category.title}
                                        </option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="command" value="doctors">
                        </td>
                    </tr>
                </table>

            </td>

            <td>
                <table>
                    <tr>
                        <th>
                            <label for="sorting">${resources.Sort_doctors_by}</label>
                        </th>

                        <td>
                            <select name="sorting" id="sorting" required onchange="this.form.submit();">
                                <option value="">--${resources.Choose_an_option_or_default}--</option>
                                <option value="ASC-NAME"${sort!=null?(sort.equals("ASC-NAME")?"selected": ""): ""}>
                                    ${resources.Firstname_alphabetically}
                                </option>
                                <option value="DESC-NAME"${sort!=null?(sort.equals("DESC-NAME")?"selected":""):""}>
                                    ${resources.Firstname_reverse}
                                </option>
                                <option value="ASC"${sort!=null?(sort.equals("ASC")?"selected": ""): ""}>
                                    ${resources.Lastname_alphabetically}
                                </option>
                                <option value="DESC"${sort!=null?(sort.equals("DESC")?"selected":""):""}>
                                    ${resources.Lastname_reverse}
                                </option>
                                <option value="CAT-ASC"${sort!=null?(sort.equals("CAT-ASC")?"selected": ""): ""}>
                                    ${resources.Category_alphabetically}
                                </option>
                                <option value="CAT-DESC"${sort!=null?(sort.equals("CAT-DESC")?"selected":""):""}>
                                    ${resources.Category_reverse}
                                </option>
                                <option value="INCREASE"${sort!=null?(sort.equals("INCREASE")?"selected": ""): ""}>
                                    ${resources.Number_of_patients_increase}
                                </option>
                                <option value="DECREASE"${sort!=null?(sort.equals("DECREASE")?"selected": ""): ""}>
                                    ${resources.Number_of_patients_decrease}
                                </option>
                            </select>
                            <input type="hidden" name="command" value="doctors">
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    </br>
    <c:choose>
        <c:when test="${requestScope.doctorsList.size()!=0}">
            <%
                int counter = 1;
            %>

            <table border="1">
                <tr>
                    <th>No.</th>
                    <th>${resources.First_name}</th>
                    <th>${resources.Last_name}</th>
                    <th>${resources.Category}</th>
                    <th>${resources.Count_of_patients}</th>
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

                        <td>
                                ${record.countOfPatient}
                        </td>

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
<input type="hidden" name="referer" value="${pageContext.request.requestURI}"/>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
