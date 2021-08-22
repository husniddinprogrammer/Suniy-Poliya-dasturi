let params = new URLSearchParams(location.search);
let id = params.get('id');
let nowtime = new Date();
let tbody = document.getElementById('tbody');
let tuliqvaqt;
let tulovQilishUmumiySumma;
/**
 * Jadval th yaratishni boshlash
 */
let boshvaqtiselect = document.getElementById('boshvaqtiselect');
let tugvaqtiselect = document.getElementById('tugvaqtiselect');
let vaqtOraligi = document.getElementById('vaqtOraligi');
let txt1 = "";
txt2 = "";
txt2 += `<th scope="col" id="sanarang" style="font-size: 21px;">Sanalar</th>`;
for (let i = 0; i < 24; i++) {
  if (i == 23) {
    txt2 += `<th scope="col" id="soat" name="soat${i}">${i}:00<br>00:00</th>`;
  }
  if (i >= 0 && i < 9) {
    txt1 += `<option value="${i}">${i}</option>`;
    txt2 += `<th scope="col" id="soat" name="soat${i}">0${i}:00<br>0${i+1}:00</th>`;
  }
  if (i == 9) {
    txt1 += `<option value="${i}">${i}</option>`;
    txt2 += `<th scope="col" id="soat" name="soat${i}">0${i}:00<br>${i+1}:00</th>`;
  }
  if (i >= 10 && i < 23) {
    txt1 += `<option value="${i}">${i}</option>`;
    txt2 += `<th scope="col" id="soat" name="soat${i}">${i}:00<br>${i+1}:00</th>`;
  }
  boshvaqtiselect.innerHTML = txt1;
  tugvaqtiselect.innerHTML = txt1;
  vaqtOraligi.innerHTML = txt2;
}
/**
 * Jadval th yaratishni tugadi
 */
/**
 * Yuklash boshlandi
 */
function yuklash() {
  poliya_service.getById(id, oddiy, console.log("xato"));
}
/**
 * Yuklash tugadi
 */
/**
 * Jadval qilish boshlandi
 */
function oddiy(res) {

  let poliya = JSON.parse(res);
  let txt = "";
  document.forms['createForm']["narxi"].value = poliya.narxi;
  for (let i = 0; i < 7; i++) {
    let hozircha = new Date(nowtime.getFullYear(), nowtime.getMonth(), nowtime.getDate() + i);
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
    let tug;
    txt += `<tr><td id="tuliqvaqtrasm${i}" style="font-size:"12px">${tuliqvaqt}</td>`;
    for (let j = 1; j <= 24; j++) {
      if (j == 24) {
        tug = 0;
        txt += `<td id="${tuliqvaqt}${j - 1}${tug}" name="${i}boshlan${j - 1}"><button  id="buttonstyle" data-target="#createModal" data-toggle="modal" onclick="create(${j - 1},${tug},'${poliya.nomi}',${i})">Bo'sh</button></td>`;
      } else {
        tug = j;
        txt += `<td id="${tuliqvaqt}${j - 1}${tug}" name="${i}boshlan${j - 1}"><button  id="buttonstyle" data-target="#createModal" data-toggle="modal" onclick="create(${j - 1},${tug},'${poliya.nomi}',${i})">Bo'sh</button></td>`;
      }
    }
    txt += `</tr>`;
  }
  tbody.innerHTML = txt;
  yuklashlavozim();
  buyurtma_service.getAllKuntartibi(id, boshla, console.log("ishlamayapdi"));
}

function boshla(json) {

  let studentlar = JSON.parse(json);
  for (let i = 0; i < studentlar.length; i++) {
      s = document.getElementById(studentlar[i].sana + studentlar[i].boshlanishVaqti + studentlar[i].tugashVaqti);
    s.innerHTML = `
    <div class='buy'>
    <button style="background-color:red;color:white;" 
    data-target="#aboutModal" data-toggle="modal" 
    onclick="haqida(${studentlar[i].id})" id="buttonstyle">Band</button>
    <br>
      <div class="malumot">
        Mijoz ismi: ${studentlar[i].mijoz.ism} <br>
        Mijoz raqami: ${studentlar[i].mijoz.nomer} <br>
      </div>
 </div>`;
  }
  /**
   * Jadval qilish tugadi
   */
}
/*
 Vaqt select boshlandi
*/
/**
 * Vaqt buyicha saralash boshlandi
 */
function vaqtsaralash() {
  let shundan = parseInt(document.getElementById('boshvaqtiselect').value);
  let shungacha = document.getElementById('tugvaqtiselect').value;
  if (shundan > shungacha) {
    let sh = shundan;
    shundan = shungacha;
    shungacha = sh;
  }
  for (let i = 0; i < 7; i++) {
    for (let j = 0; j < 24; j++) {
      if (i == 0 && j != 24) {
        document.getElementsByName('soat' + j)[0].style.display = 'none';
      }
      document.getElementsByName(i + 'boshlan' + j)[0].style.display = 'none';
    }
  }
  for (let i = 0; i < 7; i++) {
    for (let j = shundan; j <= shungacha; j++) {
      if (i == 0 && j != 24) {
        document.getElementsByName('soat' + j)[0].style.display = '';
      }
      document.getElementsByName(i + 'boshlan' + j)[0].style.display = '';

    }
  }
}
/** 
 * Vaqt buyicha saralash tugadi
 */
/*
Buyurtma qushish js 
*/

function create(boshvaqti, tugashvaqti, poliyanomi, i) {
  document.forms['createForm']["poliyanomi"].value = `${poliyanomi}`;
  document.forms['createForm']["sana"].value = document.getElementById('tuliqvaqtrasm' + i).innerText;
  document.forms['createForm']["boshvaqti"].value = boshvaqti;
  document.forms['createForm']["tugvaqti"].value = tugashvaqti;
  mijoz_service.getAll(buyurtmaBerish, console.log('Xato'));
}

function buyurtmaBerish(json) {
  let xabarlar = JSON.parse(json);
  document.getElementById("selectMijoz").style.maxHeight = (document.getElementById("createMijozHeight").offsetHeight + "px");
  let div = document.querySelector("#selectMijoz tbody");
  let txt = "";
  for (let i = 0; i < xabarlar.length; i++) {
    if (xabarlar[i].status == 1) {
      txt += `
    <tr onclick="checkbox(this, ${xabarlar[i].id})">
    <td id="ism" >${xabarlar[i].ism}</td>
    <td id="nomer">${xabarlar[i].nomer}</td>
    <td><input type="checkbox" disabled  class="selectTanlangan"></td>
</tr>
`;
    }
  }
  div.innerHTML = txt;
}
let addBuyurtmaBerilganVaqt;
function add() {
  let newPost = {
    "mijoz": {
      "id": tanlanganMijozId,
    },
    "sana": document.forms['createForm']["sana"].value,
    "status": 1,
    "poliya": {
      "id": id
    },
    "boshlanishVaqti": document.forms['createForm']["boshvaqti"].value,
    "tugashVaqti": document.forms['createForm']["tugvaqti"].value,
    "buyurtmaBerilganVaqti": null,
    "narxi": document.forms['createForm']["narxi"].value,
    "izoh": document.forms['createForm']["izoh"].value,
  }
  buyurtma_service.create(JSON.stringify(newPost), function () {
    location.reload();
  }, function (err) {
    console.log("Xatolik yuz berdi");
  });

}/*
Buyurtma qushish tugadi
*/
/*
Buyurtma haqida boshlandi
*/
let bekorId;

function haqida(id) {
  bekorId = id;
  buyurtma_service.getById(id,function (json1){
    let buyurtma=JSON.parse(json1);
    document.forms['aboutForm']["ism"].value = buyurtma.mijoz.ism;
    document.forms['aboutForm']["nomer"].value = buyurtma.mijoz.nomer;
    document.forms['aboutForm']["sana"].value = buyurtma.sana;
    document.forms['aboutForm']["tugvaqti"].value = buyurtma.tugashVaqti;
    document.forms['aboutForm']["boshvaqti"].value = buyurtma.boshlanishVaqti;
    document.forms['aboutForm']["narxi"].value = buyurtma.narxi;
    document.forms['aboutForm']["izoh"].value = buyurtma.izoh;
    if(buyurtma.status==1){
      document.getElementById("buyurtmaTulovQilish").innerHTML=`
      <button type="button"  class="btn btn-primary" data-dismiss="modal" data-target="#tulovModal" data-toggle="modal" onclick="tulovHaqida(${buyurtma.id},${buyurtma.narxi})">
      <i class="fa fa-plus" aria-hidden="true"></i> Tulov qilish
      </button>
      `;
    }
    if(buyurtma.status==2){
      document.getElementById("buyurtmaTulovQilish").innerHTML=`
      <button id="button1" type="button" style="background-color:blue;" class="btn btn-primary">Tulov qilindi</button>
      `;
    }
  },console.log("xato"));
}
/*
Buyurtmani haqida tugadi 
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
/*
Buyurtmani bekor qilish boshlandi
*/
function bekorqil() {
  buyurtma_service.getBekorQilish(bekorId, function () {
    location.reload();
  }, function (err) {
    console.log("Xatolik yuz berdi");
  });
}
/*
Buyurtmani bekor qilish tugadi
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
/*
 Mijoz qushish boshlandi
*/
function mijozadd() {
  let formMalumot = new FormData(document.forms['createMijozForm']);
  let newPost = {
    "ism": formMalumot.get('name'),
    "nomer": formMalumot.get('number'),
    "izoh": formMalumot.get('izoh'),
    "status": 1,
    "qushilganVaqt":null
  }
  mijoz_service.create(JSON.stringify(newPost), function () {
    mijoz_service.getAll(buyurtmaBerish, console.log('Xato'));
  }, function (err) {
    console.log('xato yuz berdi');
  });
  document.forms['createMijozForm'].reset();
  mijoz_service.getAll(buyurtmaBerish, console.log('Xato'));
}
/*
Mijoz qushish tugadi
*/
/* 
Mijoz qidirish boshlandi
*/
function mijozqidir() {
  var nomerBoolean = 0;
  var ismBoolean = 0;
  var searchInput = document.querySelector("#searchInput"),
    searchNumber = document.querySelector("#searchNumber"),
    rows = document.querySelectorAll("#selectMijoz tbody tr"),
    timer;
  document.getElementById("mijozAdd").disabled = true;
  if (searchInput.value !== "" && searchNumber.value !== "") {
    [].forEach.call(rows, function (row) {
      let ism = row.querySelectorAll("#ism");
      let nomer = row.querySelectorAll("#nomer");

      [].forEach.call(ism, function (ism) {
        if (ism.textContent == searchInput.value) {
          ismBoolean = 1;
        }
      });
      [].forEach.call(nomer, function (nomer) {
        if (nomer.textContent == searchNumber.value) {
          nomerBoolean = 1;
        }
      });
      if (ismBoolean == 1 && nomerBoolean == 1) {
        document.getElementById("mijozAdd").disabled = true;
      } else {
        document.getElementById("mijozAdd").disabled = false;
      }
    });

  } else {
    document.getElementById("mijozAdd").disabled = true;
  }
  if (!(window.history && history.pushState)) return;


  function filterRows() {

    [].forEach.call(rows, function (row) {

      var cells = row.querySelectorAll("#ism"),
        containsText = false;
      var kells = row.querySelectorAll("#nomer"),
        kontainsText = false;

      [].forEach.call(cells, function (cell) {
        var text = cell.textContent.toLowerCase(),
          search = searchInput.value.toLowerCase();

        if (text.indexOf(search) != -1)
          containsText = true;
      });
      [].forEach.call(kells, function (cell) {
        var text1 = cell.textContent.toLowerCase(),
          search1 = searchNumber.value.toLowerCase();

        if (text1.indexOf(search1) != -1)
          kontainsText = true;
      });

      if (containsText && kontainsText)
        row.style.display = "";
      else
        row.style.display = "none";

    });

  }

  searchInput.onkeyup = function () {

    clearTimeout(timer);

    timer = setTimeout(function () {

      if (searchInput.value != "")
        window.history.pushState(searchInput.value, "", "#search=" + encodeURI(searchInput.value) + "&&" + "#number=" + encodeURI(searchNumber.value));

    }, 1000);

    filterRows();

  }
  searchNumber.onkeyup = function () {

    clearTimeout(timer);

    timer = setTimeout(function () {

      if (searchNumber.value != "")
        window.history.pushState(searchNumber.value, "", "#search=" + encodeURI(searchInput.value) + "&&" + "#number=" + encodeURI(searchNumber.value));

    }, 1000);

    filterRows();
  }

  window.onpopstate = function (e) {

    if (e.state !== null) {
      searchInput.value = e.state;


      filterRows();
    } else {
      var searchValue = window.location.hash.split("=").pop();

      searchInput.value = decodeURI(searchValue);

      filterRows();
    }

  }
  inputyoz()

};
/*
Mijoz qidirish tugadi
 */
/*
Input yozganda boshlandi
*/
let tanlanganMijozId = null;

function inputyoz() {
  let narxi = document.getElementById("inputNarxi");
  let izoh = document.getElementById("inputIzoh");
  if (narxi.value != "" && izoh.value != "" && tanlanganMijozId != null) {
    document.getElementById("buyurtmaQushish").disabled = false;
  } else {
    document.getElementById("buyurtmaQushish").disabled = true;
  }

}
/*
Input yozganda tugadi
*/
/*
Checked boshlandi
*/
let tanlanganCheckbox = document.createElement('checkbox');
let eskiTr = document.createElement('tr');
eskiTr.style.background = 'white';
eskiTr.style.color = '#797979';
function checkbox(tr, id) {
  ch = tr.querySelector('input');
  if (tr == eskiTr) {
    if (tr.style.background == 'dodgerblue') {
      tr.style.background = 'white';
      tr.style.color = '#797979';
      eskiTr = tr;
    } else {
      tr.style.background = 'dodgerblue';
      tr.style.color = 'white';
      eskiTr = tr;
    }
  }
   else {
    eskiTr.style.background = 'white';
    eskiTr.style.color = '#797979';
    tr.style.background = 'dodgerblue';
    tr.style.color = 'white';

    eskiTr = tr;
  }
  ch.checked = !ch.checked;
  if (ch && id) {
    if (tanlanganCheckbox != ch) {
      tanlanganCheckbox.checked = false;
      tanlanganCheckbox = ch;
      tanlanganMijozId = id;
    } else {
      if (ch.checked) {
        tanlanganMijozId = id;
      } else {
        tanlanganMijozId = null;
      }
    }
  }

  inputyoz();
}
/*
Checked tugadi
*/
