<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>${resources.Registration_form}</title>
<%--    <link href="/static/style.css" rel="stylesheet"/>--%>
    <link rel="stylesheet" href="static/topstyle.css">
</head>
<body>
<center>
    <%@include file="/WEB-INF/jsp/admin/top-admin.jsp" %>
    <h2>${resources.Patient_registration_form}</h2> <br>
    <div id="content">

        <table>
            <tr>
                <td>

                    <c:if test="${not empty errorMessage and empty exception and empty code}">
                        <h4 style="color:tomato">${errorMessage}</h4>
                    </c:if>
                    <form id="formRegistration" action="/likarnya/controller?command=patient-registration"
                          method="post">


                        <div class="field">
                            <label>${resources.Enter_your_name}:</label>
                            <div class="input"><input type="text" name="name"
                                                      value='${requestScope.patient.firstName}' id="name" required/>
                            </div>
                        </div>

                        <div class="field">
                            <label>${resources.Enter_your_lastName}:</label>
                            <div class="input"><input type="text" name="lastName"
                                                      value='${requestScope.patient.firstName}'
                                                      id="lastName" required/>
                            </div>
                        </div>

                        <div class="field">
                            <label>${resources.Enter_your_b_day}:</label>
                            <div class="input"><input type="date" min="1900-01-01" max="${dateOfToday}"
                                                      name="dateOfBirth"
                                                      value="${requestScope.patient.birhtDay}"
                                                      id="dateOfBirth" required/></div>
                        </div>


                        <div class="field">
                            <label>${resources.Choose_your_gender}:</label>
                            <div class="input">
                                <select name="gender" required id="gender">
                                    <option selected disabled>${resources.Choose_your_gender}:</option>
                                    <option value="MALE">
                                        Male
                                    </option>
                                    <option value="FEMALE">
                                        Female
                                    </option>
                                </select>
                            </div>
                        </div>
                        </br>
                        <div class="submit">
                            <button type="submit">${resources.Register}</button>
                        </div>

                    </form>

                </td>

                <td>
                    </br></br>
                    <c:choose>
                        <c:when test="${errorMessages!=null}">
                            <ul style="color:tomato">
                                Error list: <br/>
                                <c:forEach items="${errorMessages}" var="i">
                                    <li><c:out value="${i}"/></li>
                                    <br/>
                                </c:forEach>
                            </ul>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </table>
    </div>
</center>
</div>

<input type="hidden" name="referer" value="${pageContext.request.requestURI}"/>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>