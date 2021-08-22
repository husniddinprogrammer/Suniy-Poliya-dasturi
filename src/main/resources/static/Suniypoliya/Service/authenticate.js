function register() {
    let hozirtime = new Date(); 
    let vaqt = "";
    if (hozirtime.getMonth() < 10) {
        vaqt = hozirtime.getDate() + ".0" + hozirtime.getMonth() + "." + hozirtime.getFullYear();
    }
    else {
        vaqt = hozirtime.getDate() + "." + hozirtime.getMonth() + "." + hozirtime.getFullYear();
    }
    let form = document.forms['registerForm'];
    let check = {}
    check.name = form["name"].value;
    check.number = form["number"].value;
    check.password = form["password"].value;
    check.username = form["login"].value;
    check.qushilganVaqti = vaqt;
    check.managerTur="boss";
    check.aktiv=1;

    if (localStorage.getItem("token")) {
        localStorage.removeItem("token")
    }
    let checkbox = document.getElementById("checkbox")

    if (form["password"].value == form["comfirm_password"].value && checkbox.checked == true) {
        http.post("/api/register", JSON.stringify(check), function (responce) {
            document.getElementById("span").innerHTML = `
                <div class="alert alert-success">
                    <strong>Success!</strong> Yangi akkaunt yaratildi.
                </div>`
        }, function (error) {
            console.log("xato");
            console.log(error);
        })
    } else {
        document.getElementById("span").innerHTML = `
        <div class="alert alert-danger">
            <strong>Wrong!</strong> parolni tasdiqlashda xatolik yoki oferta shartlariga rozi bo'lish shart
        </div>`
    }
}