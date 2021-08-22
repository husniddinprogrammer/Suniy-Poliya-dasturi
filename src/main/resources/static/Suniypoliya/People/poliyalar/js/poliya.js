auth_service.people(console.log("keldi"),console.log("xato"));

poliya_service.getAllPeople(printpost, console.log("xato"));
let tbody=document.getElementById("poliyalar");
function printpost(res) {
    let txt="";
    let poliyalar = JSON.parse(res);
    poliyalar.forEach(xabarlar => {
      i = xabarlar.id;
      if (xabarlar.status == 1) {
        if (xabarlar.user != null) {
          txt += `
          <div class="poliya col-md-4 col-xl-3 col-sm-6" id="poliya">
          <div class="poliyaCard">
              <img src="../image/poliyalarga2.jpg" alt="poliya" id="poliyaCardImage">
              <h5>${xabarlar.nomi}</h5>
              <p class="malumotlar">
                  <b>Admin:</b> ${xabarlar.user.name}<br>
                  <b>Nomer:</b> ${xabarlar.user.number}<br>
                  <b>Narxi:</b> ${xabarlar.narxi} so'm<br>
                  <b>Manzil:</b> ${xabarlar.manzil}<br>
                  <a href="${xabarlar.googleMap}">
                    <button class="xarita">Xaritadan ko'rish</button>
                  </a><br>
                  <a href="../kuntartibi/kuntartibi.html?id=${xabarlar.id}">
                    <button class="buyurtma">Buyurtma berish</button>
                  </a>
              </p>
          </div>
      </div>
                `;
        } 
    }
    });
    tbody.innerHTML = txt;
let poliyaulcham=document.getElementById("poliya").offsetWidth;
for(let i=0;i<document.querySelectorAll("#poliya").length;i++){
document.querySelectorAll("#poliya")[i].style.height = ( poliyaulcham*3/2-poliyaulcham/19+ "px");
document.querySelectorAll("#poliya")[i].style.marginTop=( poliyaulcham/8+"px");
}
}
