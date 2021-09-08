<div class="topnav">
    <a class="active" href="/doctor-cabinet">Main</a>
    <a class="active" href="/doctor-cabinet">Patients for Diagnosis</a>
    <a class="active" href="/doctor-cabinet/treatment-patients">Patients for Treatment</a>
    <a class="active" href="/doctor-cabinet/history">Treated Patients/History</a>
    <form th:action="@{/perform-logout}" method="post">
        <button style="position: absolute; right: 0px; top: 20px; width: 80px; height: 30px" type="submit">
            Log Out
        </button>
    </form>
</div>