let tbody = document.querySelector('tbody');

//bo'sh modal soz boshlandi
let params = new URLSearchParams(location.search);
let id = params.get('id');
let poliyaNomi;
let adminIsm;
let admimNomer;
poliya_service.getById(id, oddiy, console.log("xato"));
function oddiy(json){
    poliya=JSON.parse(json);    
    poliyaNomi=poliya.nomi;
    adminIsm=poliya.user.name;
    admimNomer=poliya.user.number;
}
//bo'sh modal soz boshlandi
//sana tartibilash boshlandi
sanalar = document.getElementById("sanalar");
let nowtime = new Date(); let txt2 = "";
let txt = ""; let month; let year;
txt += `<th>Vaqt</th>`;
for (let i = 0; i < 7; i++) {
    let hozircha = new Date(nowtime.getFullYear(), nowtime.getMonth(), nowtime.getDate() + i);
    year = "." + hozircha.getFullYear();
    let oy = hozircha.getMonth() + 1;
    let sana = hozircha.getDate();
    if (hozircha.getMonth() + 1 < 10) {
        month = ".0" + oy;
    } else {
        month = "." + oy;
    }
    if (hozircha.getDate() < 10) {
        sana = "0" + hozircha.getDate();
    } else {
        sana = "" + hozircha.getDate();
        sana1 = "-" + hozircha.getDate();
    }
    tuliqvaqt = sana + "" + month + "" + year;
    txt += `<th><span>${tuliqvaqt}</span></th>`;
    sanalar.innerHTML = txt;
}
soat();
function soat() {
    let txt2 = "";
    let tug;

    //soatlarni bohlash
    for (let j = 1; j <= 24; j++) {
        if (j == 24) {
            tug = 0;
        } else {
            tug = j;
        }
        txt2 += `<tr><td class='headcol'">${j-1}:00-${tug}:00</td>`;
        for (let i = 0; i < 7; i++) {
            let sana1="";let month1;
            let hozircha = new Date(nowtime.getFullYear(), nowtime.getMonth(), nowtime.getDate() + i);
            let year1 = hozircha.getFullYear();
            let oy  = hozircha.getMonth() + 1;
            if (hozircha.getMonth() + 1 < 10) {
                month1 = "-0" + oy;
            } else {
                month1 = "-" + oy;
            }
            if (hozircha.getDate() < 10) {
                sana1 = "-0" + hozircha.getDate();
            } else {
                sana1 = "-" + hozircha.getDate();
            }
            let tuliqvaqt2 = year1 + "" + month1 + "" + sana1+"";
            txt2 += `<td class="bosh" id="${tuliqvaqt2}vaqt${j - 1}${tug}">
            <button class="bosh" data-target="#boshModal" data-toggle="modal" onclick="boshmalumot(${year1},${month1},${sana1},${j-1},${tug})">Bo'sh</button></td>`;
        }
        txt2 += "</tr>";
    }
    tbody.innerHTML += txt2;   
    //soatlarni tugadi
}
//sana tartiblash tugadi

buyurtma_service.getAllKuntartibi(id, boshla, console.log("ishlamayapdi"));

function boshla(json) {
    let studentlar = JSON.parse(json);
    for (let i = 0; i < studentlar.length; i++) {
      s = document.getElementById(studentlar[i].sana + "vaqt"+studentlar[i].boshlanishVaqti + studentlar[i].tugashVaqti);
      s.innerHTML = `
      <button class="band" data-target="#bandModal" data-toggle="modal" onclick="malumotlar('${studentlar[i].mijoz.ism}')">Band</button>
      `;
    }
}
function malumotlar(ism){
    bandSoz=document.getElementById("bandModalSoz");
    bandSoz.innerHTML=`
    <p>${poliyaNomi}  bu vaqt orasi band</p>
    <p>Bu poliyani ${ism} ismli mijoz band qilganlar.</p>
    <p>Mobodo bekor qilsala buyurtma berishingiz mumkin</p>
    `
}
function boshmalumot(year,month,sana,bosh,tug){
    boshSoz=document.getElementById("boshModalSoz");
    boshSoz.innerHTML=`
    <p>${poliyaNomi} ${year}${month}${sana} kuni ${bosh}:00 dan ${tug}:00 gacha bo'sh</p>
    <p>Bemalol shu nomerga telefon qilib buyurtma berishingiz mumkin:</p>
    <p>Nomer: <b>${admimNomer}</b></p>
    `;
}