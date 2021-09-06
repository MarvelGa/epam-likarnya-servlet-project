<center>
    <link rel="stylesheet" href="static/style.css">
    <table border="0">
        <tr>
            <th><h3><a href="controller?command=home">${resources.Home_page}</a> |</h3></th>
            <th><h3><a href="controller?command=loginPage">${resources.Login}</a> |</h3></th>
            <th><h3><a href="controller?command=registration">${resources.Registration_page}</a> |</h3></th>
            <th><h3><a href="controller?command=aboutUs">${resources.About_us}</a> |</h3></th>
            <th><h3>
                <a href='changeLocale' class='langstyle'>${resources.EN}</a>
                <strong style='width:2px'>|</strong>
                <a href='changeLocale?locale=ru' class='langstyle'>${resources.RU}</a>
            </h3>
            </th>
        </tr>
    </table>

    <div style="position: absolute; top: 8px; right: 100px;font-size: 14px;">
        <table>
            <tr>
                <th>
                    <c:choose>
                    <c:when test="${sessionScope.user!=null}">
                    <h2><span style="color:forestgreen">${resources.Hello}, ${sessionScope.user.firstName}! </span></h2>
                    <h4><span style="color:forestgreen">${resources.You_ve_entered_as} ${sessionScope.userRole} </span></h4>
                    <c:choose>
                    <c:when test="${sessionScope.user.roleId==1}">
                        <h2><a href="/tct/controller?command=adminCabinet">${resources.Personal_cabinet}</a></h2>
                    </c:when>
                    <c:otherwise>
                    <h2><a href="/tct/controller?command=userCabinet">${resources.Personal_cabinet}</a>
                        <h3>
                            <h3><a href="/tct/controller?command=allUserDeliveries">${resources.All_your_delivery}</a>
                                <h3>
                                    </c:otherwise>
                                    </c:choose>
                                    <form action='' method="get">
                                        <input type='submit' value='${resources.Log_out}'/>
                                        <input type="hidden" name="command" value="logout">
                                    </form>
                                    </c:when>
                                    <c:otherwise>
                                    <h2><span style="color:forestgreen">${resources.Hello_guest}!</span></h2>
                                    <h3><span style="color:forestgreen">${resources.Please_login} </span></h3>
                                    </c:otherwise>
                                    </c:choose>
                </th>
            </tr>
        </table>
    </div>
</center>
