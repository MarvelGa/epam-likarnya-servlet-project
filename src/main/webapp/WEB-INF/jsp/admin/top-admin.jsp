<div class="topnav">
<%--    <a class="active" href="controller?command=adminCabinet">${resources.Home_page}</a>--%>
    <a class="active" href="controller?command=nurses">${resources.Nurses}</a>
    <a class="active" href="controller?command=adminCabinet">${resources.Patients}</a>
    <a class="active" href="controller?command=doctors">${resources.Doctors}</a>
    <a class="active" href="controller?command=medical-form">${resources.Med_registration_page}</a>
    <a class="active" href="controller?command=patient-form">${resources.Patients_Registration_page}</a>

    <a class="active" href='changeLocale' class='langstyle'>${resources.EN}</a>
    <strong style='width:2px'>|</strong>
    <a class="active" href='changeLocale?locale=ru' class='langstyle'>${resources.RU}</a>

    <form action="./controller" method="get">
        <button style="position: absolute; right: 0px; top: 20px; width: 80px; height: 30px" type="submit">
            ${resources.Log_out}
        </button>
        <input type="hidden" name="command" value="logout">
    </form>

</div>

<h2><span
        style="color:#5cac8c">${resources.Admin_Cabinet_Of} ${sessionScope.user.firstName} ${sessionScope.user.lastName} </span>
</h2>
