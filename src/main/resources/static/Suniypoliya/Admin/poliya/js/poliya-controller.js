let tbody = document.getElementById("tbody");
let txt = "";
let i;
/**
 * Yuklash boshlandi
 */
function yuklash() {
  poliya_service.getAll(printpost, console.log(""));
  user_service.getAll(adminSelect, console.log(""));
}
/**
 * Yuklash tugadi
 */
/**
 * Jadval boshlandi
 */
function printpost(res) {
  let studentlar = JSON.parse(res);
  studentlar.forEach(xabarlar => {
    i = xabarlar.id;
    if (xabarlar.status == 1) {
      if (xabarlar.user != null) {
        txt += `
              <tr>
                       <td>${xabarlar.nomi}</td>
                       <td>${xabarlar.manzil}</td>
                       <td>${xabarlar.user.name}</td>
                       <td>${xabarlar.narxi}so'm</td>
                       <td><a href="poliyaKunTartibi.html?id=${xabarlar.id}"><button id="button1"  type="button" class="btn btn-primary" id="${i}">Buyurtma berish</button></td>
                       <td class="rollUpdate" style="display: none;"><button id="button1" type="button" class="btn btn-primary" id="${i}" data-target="#updateModal" data-toggle="modal" onclick="poliyaId(${xabarlar.id})">O'zgartirish</button><br></td>
                       <td class="rollRemove"  style="display: none;"><button type="button" class="btn btn-sm btn-danger" onclick="removePost(${xabarlar.id})">O'chirish</button></td>
                   </tr>`;
      } else {
        txt += `
             <tr>
                       <td>${xabarlar.nomi}</td>
                       <td>${xabarlar.manzil}</td>
                       <td>Yuq</td>
                       <td>${xabarlar.narxi}so'm</td>
                       <td>Buyurtma berilgan</td>
                       <td class="rollUpdate"  style="display: none;"><button id="button1" type="button" class="btn btn-primary" id="${i}" data-target="#updateModal" data-toggle="modal" onclick="poliyaId(${xabarlar.id})>O'zgartirish</button><br></td>
                       <td class="rollRemove"  style="display: none;"><button type="button" class="btn btn-sm btn-danger" onclick="removePost(${xabarlar.id})">O'chirish</button></td>
                   </tr>`;
      }
    }
  });
  tbody.innerHTML = txt;
  yuklashlavozim();
  poliyaRoll();
}
/**
 * Rollarga oid boshlandi
 */
function poliyaRoll() {
  let updateSon = document.querySelectorAll(".rollUpdate").length;
  if (aniqLavozim == "CREATOR" || aniqLavozim == "BOSS") {
    document.querySelector(".rollPoliyaQushish").style.display="";
    document.querySelector(".rollAdminQushish").style.display="";
    if (updateSon != 0) {
      for (let i = 0; i < updateSon; i++) {
        document.querySelectorAll(".rollUpdate")[i].style.display = "";
        document.querySelectorAll(".rollRemove")[i].style.display = "";
      }
    }
  }

}
/**
 * Rollarga oid tugadi
 */
/**
 * Jadval tugadi
 */
/*
Admin qushish boshlandi
*/
function adminQushish() {
  let form = document.forms['createAdminForm'];
  let check = {}
  check.name = form["name"].value;
  check.number = form["number"].value;
  check.password = form["password"].value;
  check.username = form["username"].value;
  check.qushilganVaqti = null;
  check.lavozimlar = ["ADMIN"];
  check.aktiv = 1;
  user_service.create(JSON.stringify(check), location.reload(), console.log("gg"));
}
/*
Admin qushish tugadi
*/
/**
 * Xato korsat boshlandi
 */
function xatoKorsat(res) {
  tbody.innerHTML = `<h1>Xatolik ro'y berdi</h1>`;
}
/**
 * Xato korsat tugadi
 */

/*
Admin select boshlandi
*/
function adminSelect(json) {
  let xabarlar = JSON.parse(json);
  let divAdmin = document.getElementById("adminSelect");
  let divAdmin2 = document.getElementById("adminSelectUzgar");
  let txtAdmin = ""
  for (let i = 0; i < xabarlar.length; i++) {
    if (xabarlar[i].aktiv == 1 && xabarlar[i].lavozimlar[0] == "ADMIN") {
      txtAdmin += `
      <option value="${xabarlar[i].id}">${xabarlar[i].username}</option>`;
    }
  }
  divAdmin.innerHTML = txtAdmin;
  divAdmin2.innerHTML = txtAdmin;
}
/*
Admin select tugadi
*/
/*
Poliya qushish boshlandi
*/
function add() {
  let formMalumot = new FormData(document.forms['createForm']);
  let e = document.getElementById('adminSelectUzgar');
  let newPost = {
    "nomi": formMalumot.get('name'),
    "narxi": formMalumot.get('cost'),
    "user": {
      "id": e.value
    },
    "manzil": formMalumot.get('manzil'),
    "status": 1,
    "googleMap":formMalumot.get('googleMap'),
    "koordinata": null
  }

  poliya_service.create(JSON.stringify(newPost), function () {
    location.reload();
  }, function (err) {
    console.log("Xatolik yuz berdi");
  });
}
/*
Poliya qushish tugadi
*/
/*
Poliya o'zgartirish boshlandi
*/
var belgilanganPoliyaId;

function poliyaId(id) {
  belgilanganPoliyaId = id;
  poliya_service.getById(belgilanganPoliyaId, postPut, console.log("xato"));

  function postPut(jsonText) {
    let xabarlar = JSON.parse(jsonText);
    console.log("keldi");
    document.forms['updateForm']["id"].value = belgilanganPoliyaId;
    document.forms['updateForm']["name"].value = xabarlar.nomi;
    document.forms['updateForm']["cost"].value = xabarlar.narxi;
    document.forms['updateForm']["manzil"].value = xabarlar.manzil;
    document.getElementById("adminSelectUzgar").value = xabarlar.user.id;
    document.forms['updateForm']["id"].value = belgilanganPoliyaId;
    document.forms['updateForm']["googleMap"].value = xabarlar.googleMap;
  }


}

function update() {
  let e = document.getElementById('adminSelectUzgar');
  let updatePost = {
    "nomi": document.forms['updateForm']["name"].value,
    "narxi": document.forms['updateForm']["cost"].value,
    "user": {
      "id": e.value
    },
    "manzil": document.forms['updateForm']["manzil"].value,
    "status": 1,
    "googleMap":document.forms['updateForm']["googleMap"].value,
    "koordinata": null
  }
  poliya_service.update(JSON.stringify(updatePost), belgilanganPoliyaId, function () {
   location.reload(); 
  }, function () {
    console.log("Xatolik yuz berdi");
  })
}
/*
Poliya o'zgartirish tugadi
*/
/**
 * Poliyaga yozganda boshlandi
 */
function poliyaQushishYozish() {
  let nomi = document.forms['createForm']['name'].value;
  let cost = document.forms['createForm']['cost'].value;
  let manzil = document.forms['createForm']['manzil'].value;
  let googleMap = document.forms['createForm']['googleMap'].value;
  let tugma = document.getElementById('poliyaQushish');
  if (nomi == "" || cost == "" || manzil == "" || googleMap == "") {
    tugma.disabled = true;
  } else {
    tugma.disabled = false;
  }
}
/**
 * Poliya yozganda tugadi
 */
/**
 * Poliyaga uzgartirish yozganda boshlandi
 */
function poliyaUzgartirishYozish() {
  let nomi = document.forms['updateForm']['name'].value;
  let cost = document.forms['updateForm']['cost'].value;
  let manzil = document.forms['updateForm']['manzil'].value;
  let googleMap = document.forms['updateForm']['googleMap'].value;
  let tugma = document.getElementById('poliyaUzgartirish');
  if (nomi == "" || cost == "" || manzil == "" || googleMap == "") {
    tugma.disabled = true;
  } else {
    tugma.disabled = false;
  }
}
/**
 * Poliya uzgartirish yozganda tugadi
 */
/**
 * Admin qo'shish yozganda boshlandi
 */
function adminYozish() {
  let username = document.forms['createAdminForm']['username'].value;
  let name = document.forms['createAdminForm']['name'].value;
  let number = document.forms['createAdminForm']['number'].value;
  let password = document.forms['createAdminForm']['password'].value;
  let tugma = document.getElementById('adminQushish');
  if (username == "" || name == "" || number == "" || password == "") {
    tugma.disabled = true;
  } else {
    tugma.disabled = false;
  }
}
/**
 * Admin qo'shish yozganda tugadi
 */
/**
 * Poliyani o'chirish boshlandi
 */
function removePost(id) {
  poliya_service.getBekorQil(id, location.reload(), console.log('ochmadi'))
}
/**
 * Poliya o'chirish tugadi
 */