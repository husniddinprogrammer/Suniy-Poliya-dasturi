/**
 * Yuklash boshlandi
 */
function yuklash() {
  poliya_service.getAll(poliyaQush, console.log("poliyalar qushilmadi"));
  buyurtma_service.getAllTodays(printPost, console.log('xatolik yuz berdi'));
}
/**
 * Yuklash tugadi
 */
/**
 * Poliya select boshlandi
 */
let jami = 0;

function poliyaQush(json) {
  let poliyalarJson = JSON.parse(json);
  let poliyaSelect = document.getElementById('poliyaselect');
  let txt = ""
  jami = poliyalarJson.length;
  for (let i = 0; i < poliyalarJson.length; i++) {
    if(poliyalarJson[i].status==1){
      txt += `
        <option value="${poliyalarJson[i].id}">${poliyalarJson[i].nomi}</option>`
    }
    }
  poliyaSelect.innerHTML = txt;
}
/**
 * Poliya select tugadi
 */
/**
 * Jadval qilish boshlandi
 */
function printPost(json) {
  let xabarlar = JSON.parse(json);
  let div = document.getElementById('tbody');
  let txt = "";
  let aktiv;
  let color;
  let tugma;
  jami = xabarlar.length;
  for (let i = 0; i < xabarlar.length; i++) {
    if (xabarlar[i].status == 0) {
      aktiv = "Aktivmas";
      color = "rgb(255, 0, 0)";
      tugma = `<button id="button1" type="button" style="background-color:rgb(255, 0, 0);" class="btn btn-primary" id="${i}" >Tulov bo'lmaydi</button>`
    }
    if (xabarlar[i].status == 1) {
      aktiv = "Aktiv";
      color = "rgb(99, 245, 55)";
      tugma = `<button id="button1" style="background-color:rgb(99, 245, 55);" type="button" class="btn btn-primary" id="${i}" data-target="#tulovModal" data-toggle="modal" onclick="tulovHaqida(${xabarlar[i].id},${xabarlar[i].narxi})">Tulov qilish</button>`
    }
    if (xabarlar[i].status == 2) {
      aktiv = "Tulandi";
      color = "blue";
      tugma = `<button id="button1" type="button" style="background-color:blue;" class="btn btn-primary" id="${i}">Tulov qilindi</button>`
    }
    txt += `<tr id="poliya${i}" name="poliyacha${xabarlar[i].poliya.id}">
                      <td>${xabarlar[i].mijoz.ism}</td>
                      <td>${xabarlar[i].poliya.nomi}</td>
                      <td>${xabarlar[i].sana}</td>
                      <td>${xabarlar[i].boshlanishVaqti}:00</td>
                      <td>${xabarlar[i].tugashVaqti}:00</td>
                      <td><span style="border-radius:5px;padding:5px;color:white;background-color:${color};">${aktiv}</span></td>
                      <td><button id="button1" style="background-color:rgb(99, 245, 55);" type="button" class="btn btn-primary" id="${i}" data-target="#aboutModal" data-toggle="modal" onclick="buyurtmaHaqida(${xabarlar[i].id})">Buyurtma haqida</button></td>
                      <td>${tugma}<br></td
                  </tr>`;
  }
  div.innerHTML = txt;
  yuklashlavozim();
}
/**
 * Jadval qilish tugadi
 */
/**
 * Poliya saralash boshlandi 
 */
function poliyaBuyichaSarala() {
  let select = parseInt(document.getElementById('poliyaselect').value);
  for (let i = 0; i < jami; i++) {
    if (document.getElementById('poliya' + i).attributes["name"].value != `poliyacha${select}`) {
      document.getElementById('poliya' + i).style.display = "none";
    } else {
      document.getElementById('poliya' + i).style.display = "";
    }
  }
}
/**
 * Poliya saralash tugadi
 */
/**
 * Vaqt boshlandi
 */
let nowtime = new Date();
let hozircha = new Date(nowtime.getFullYear(), nowtime.getMonth(), nowtime.getDate());
year = "" + hozircha.getFullYear();
let oy = hozircha.getMonth() + 1;
let sana = hozircha.getDate();
if (hozircha.getMonth() + 1 < 10) {
  month = "-0" + oy;
} else {
  month = "-" + oy;
}
if (hozircha.getDate() < 10) {
  sana = "-0" + hozircha.getDate();
} else {
  sana = "-" + hozircha.getDate();
}
tuliqvaqt = year + month + sana;
/**
 * Vaqt tugadi
 */
/**
 * Tulov haqida boshlandi
 */
let tulovBuyurtmaId = 0;
let tulovQiymatNarxi = 0;
let tulovHozirgiNaqd = 0;
let tulovHozirgiPlastik = 0;
let tulovJami = 0;

function tulovHaqida(id, narxi) {
  tulovBuyurtmaId = 0;
  tulovQiymatNarxi = 0;
  tulovHozirgiNaqd = 0;
  tulovHozirgiPlastik = 0;
  tulovJami = 0;
  tulovBuyurtmaId = id;
  tulovQiymatNarxi = narxi;
  let tulovQanchaTulangan = document.getElementById("qanchaTulangan");
  let tulovJamiSumma = document.getElementById("jamiSumma");
  tulovQilish_service.getAllBuyurtmaId(id, function (json1) {
    let tulov = JSON.parse(json1);
    if (tulov.length != 0) {
      tulovHozirgiNaqd = tulov[0].naqd;
      tulovHozirgiPlastik = tulov[0].plastik;
      tulovJami = tulovHozirgiNaqd + tulovHozirgiPlastik;
      tulovQanchaTulangan.innerHTML = `Hozirda to'langan summa(<b>Naqd:</b>&nbsp;${tulov[0].naqd} so'm&nbsp;&nbsp;<b>Plastik:</b>&nbsp; ${tulov[0].plastik} so'm)`;
      tulovJamiSumma.innerHTML = `Jami:&nbsp;${tulovJami} so'm`;
    } else {
      tulovQanchaTulangan.innerHTML = `Hozirda to'langan summa(<b>Naqd:</b>&nbsp;0 so'm&nbsp;&nbsp;<b>Plastik:</b>&nbsp;0 so'm)`;
      tulovJamiSumma.innerHTML = `Jami:&nbsp;0 so'm`;
    }
  }, console.log("xato"));
}
/**
 * Tulov haqida tugadi
 */
/**
 * Yozilyapti boshlandi
 */
let plastik;
let naqd;

function yozilyapti() {
  let jami = 0;
  naqd = "" + 0;
  plastik = "" + 0;
  document.getElementById("ogohlantirish").innerHTML = "";
  if (document.forms['tulovForm']["naqd"].value.length != 0) {
    naqd = document.forms['tulovForm']["naqd"].value + "";
  }
  if (document.forms['tulovForm']["plastik"].value.length != 0) {
    plastik = document.forms['tulovForm']["plastik"].value + "";
  }
  jami = parseInt(naqd) + parseInt(plastik) + tulovJami;
  document.getElementById("jamiSumma").innerHTML = "Jami:&nbsp;" + jami + " so'm";
  if (jami > tulovQiymatNarxi) {
    document.getElementById("tulovTugma").disabled = true;
    document.getElementById("ogohlantirish").innerHTML = `<span style="color:red;">Mablag oshib keti buyurtma narxi ` + tulovQiymatNarxi + " so'm";
  } else {
    document.getElementById("tulovTugma").disabled = false;
  }
}
/**
 * Yozilyati tugadi
 */
/**
 * Tulov qilish boshlandi
 */
function tulovQilish() {
  if (document.forms['tulovForm']["plastik"].value != "" || document.forms['tulovForm']["naqd"].value != "") {
    let newPost = {
      "plastik": tulovHozirgiPlastik + parseInt(plastik),
      "naqd": tulovHozirgiNaqd + parseInt(naqd),
      "tulovVaqti": null,
      "buyurtma": {
        "id": tulovBuyurtmaId
      },
      "status": 1
    }
    tulovQilish_service.create(JSON.stringify(newPost), function () {
      location.reload();
    }, function (err) {
      console.log("Xatolik yuz berdi");
    });
  } else {
    document.getElementById("ogohlantirish").innerHTML = `<span style="color:red">Narxni kiriting</span>`;
  }
}
/**
 * Tulov qilish tugadi
 */
function buyurtmaHaqida(id){
  buyurtma_service.getById(id,function (json1){
    let buyurtma=JSON.parse(json1);
    document.forms['aboutForm']["id"].value=buyurtma.id;
    document.forms['aboutForm']["adminIsmi"].value=buyurtma.poliya.user.username;
    document.forms['aboutForm']["adminNomer"].value=buyurtma.poliya.user.number;
    document.forms['aboutForm']["mijozIsmi"].value=buyurtma.mijoz.ism;
    document.forms['aboutForm']["mijozNomer"].value=buyurtma.mijoz.nomer;
    document.forms['aboutForm']["buyurtmaIzoh"].value=buyurtma.izoh;
    document.forms['aboutForm']["buyurtmaNarxi"].value=buyurtma.narxi;
    document.forms['aboutForm']["buyurtmaBerilganVaqt"].value=buyurtma.buyurtmaBerilganVaqti;
  },console.log("xato"));
}
/**
 * Xabar sana buyicha boshlandi
 */
 function xabarsana(){
  let sana=document.getElementById("sana");
  buyurtma_service.getAllSana(sana.value,printPost,console.log(""));
}
/**
* Xabar sana buyicha tugadi
*/
