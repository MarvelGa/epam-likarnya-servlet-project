<center>
    <link rel="stylesheet" href="static/style.css">
    <table border="0">
        <tr>
            <th><h3><a href="controller?command=adminCabinet">${resources.Home_page}</a> |</h3></th>
            <th><h3><a href="controller?command=nurses">${resources.Nurses}</a> |</h3></th>
            <th><h3><a href="controller?command=patients">${resources.Patients}</a> |</h3></th>
            <th><h3><a href="controller?command=doctors">${resources.Doctors}</a> |</h3></th>
            <th><h3><a href="controller?command=medical-form">${resources.Med_registration_page}</a> |</h3></th>
            <th><h3><a href="controller?command=patient-form">${resources.Patients_Registration_page}</a> |</h3></th>
            <th><h3>
                <a href='changeLocale' class='langstyle'>${resources.EN}</a>
                <strong style='width:2px'>|</strong>
                <a href='changeLocale?locale=ru' class='langstyle'>${resources.RU}</a>
            </h3>
            </th>
            <th>
                <form action="./controller" method="get">
                    <button style="position: absolute; right: 0px; top: 20px; width: 80px; height: 30px" type="submit">
                        ${resources.Log_out}
                    </button>
                    <input type="hidden" name="command" value="logout">
                </form>
            </th>
        </tr>
    </table>
    <h3><span style="color:blue">${resources.Admin_Cabinet_Of} </span></h3>
    <h3><span style="color:blue">${session.user.firstName} </span></h3>
<%--    <h3><span style="color:blue">${session.user.firstName+ ' '+ session.user.lastName} </span></h3>--%>
</center>

