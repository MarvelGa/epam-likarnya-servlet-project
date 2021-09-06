<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>${resources.Medic_registration_form}</title>
    <link href="/static/style.css" rel="stylesheet"/>
</head>
<body>
<center>
    <%@include file="/WEB-INF/jsp/admin/top-admin.jsp" %>
    <h2>${resources.Medic_registration_form}</h2> <br>
    <div id="content">

        <table>
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.user==null}">

                            <c:if test="${not empty errorMessage and empty exception and empty code}">
                                <h4 style="color:tomato">${errorMessage}</h4>
                            </c:if>
                            <form id="formRegistration" action="/likarnya/controller?command=medical-registration"
                                  method="post">
                                <table>

                                    <tr>
                                        <th>
                                            <label>First Name</label>
                                        </th>
                                        <td>
                                            <input type="text" name="firstName" field="{firstName}"
                                                   id="firstName"
                                                   value="${registrationUser.firstName}"/>
                                        </td>
                                    </tr>


                                    <tr>
                                        <th>
                                            <label>Last Name</label>
                                        </th>
                                        <td>
                                            <input type="text" name="lastName" field="{lastName}" id="lastName"
                                                   value="${registrationUser.lastName}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>
                                            <label>Email</label>
                                        </th>
                                        <td>
                                            <input type="text" name="email" field="{email}" id="email"
                                                   value="${registrationUser.email}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>
                                            <label>Password</label>
                                        </th>
                                        <td>
                                            <input type="password" name="password" field="{password}"
                                                   id="password"
                                                   value="${registrationUser.password}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>
                                            <label for="role">Role</label>
                                        </th>
                                        <td>
                                            <select field="{role}" id="role" required>
                                                <option value="DOCTOR">DOCTOR</option>
                                                <option value="NURSE">NURSE</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>
                                            <label for="category">Category</label>
                                        </th>
                                        <td>
                                            <select field="{category}" id="category" required>
                                                <option value=0>NONE</option>
                                                <option value=1>THERAPIST</option>
                                                <option value=2>SURGEON</option>
                                                <option value=3>TRAUMATOLOGIST</option>
                                                <option value=4>DERMATOLOGIST</option>
                                                <option value=5>NEUROPATHOLOGIST</option>
                                                <option value=6>OTOLARYNGOLOGIST</option>
                                                <option value=7>OPHTHALMOLOGIST</option>
                                            </select>
                                        </td>
                                    </tr>


                                    <tr>
                                        <th>
                                        </th>
                                        <td colspan="0">
                                            <input type="submit" value="Registration"/>
                                            <input type="reset" value="Clear">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </c:when>
                    </c:choose>
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