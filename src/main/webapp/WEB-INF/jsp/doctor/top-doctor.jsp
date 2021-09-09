<div class="topnav">
    <a class="active" href="controller?command=doctorCabinet">Main</a>
    <a class="active" href="controller?command=doctorCabinet">Patients for Diagnosis</a>
    <a class="active" href="controller?command=doctor-treatment-patients">Patients for Treatment</a>
    <a class="active" href="controller?command=doctor-cabinet-history">Treated Patients/History</a>
    <form action="./controller" method="get">
        <button style="position: absolute; right: 0px; top: 20px; width: 80px; height: 30px" type="submit">
            ${resources.Log_out}
        </button>
        <input type="hidden" name="command" value="logout">
    </form>
</div>