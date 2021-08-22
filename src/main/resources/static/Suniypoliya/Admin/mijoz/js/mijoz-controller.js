let tbody = document.getElementById("tbody")
/**
 * Yuklash boshlanadi
 */
function yuklash() {
    mijoz_service.getAll(printpost, console.log('Xato yuz berdi.'))
}
/**
 * Yuklash tugadi
 */
/**
 * Jadval boshlandi
 */
function printpost(json) {
    let txt = "";
    let xabarlar = JSON.parse(json);
    console.log(xabarlar);
    if(xabarlar.length!=0){
        for (let i = 0; i < xabarlar.length; i++) {
        if (xabarlar[i].status == 1) {
            txt += `<tr>
        <td>${xabarlar[i].ism}</td>
        <td>${xabarlar[i].nomer}</td>
        <td>${xabarlar[i].izoh}</td>
        <td><button type="button" class="btn btn-sm btn-danger" onclick="removePost(${xabarlar[i].id})">O'chirish</button></td>
    </tr>`;
        }
        if (xabarlar[i].status == 0) {
            txt += `<tr>
        <td>${xabarlar[i].ism}</td>
        <td>${xabarlar[i].nomer}</td>
        <td>${xabarlar[i].izoh}</td>
        <td><button type="button" class="btn btn-sm btn-black">O'chirilgan</button></td>
    </tr>`;
        }
    }
    }
    else{
        tbody.innerHTML="";
    }
    
    tbody.innerHTML = txt;
    yuklashlavozim();
}
/**
 * Jadval tugadi
 */
/**
 * Mijozni o'chirish boshlandi
 */
function removePost(id) {
    mijoz_service.getBekorQilish(id, location.reload(), console.log('ochmadi'));
}
/**
 * Mijoz o'chirish tugadi
 */
/**
 * Mijoz qo'shish boshlandi
 */
function add() {
    let newPost = {
        "ism": document.forms['createForm']['name'].value,
        "nomer": document.forms['createForm']['number'].value,
        "izoh": document.forms['createForm']['izoh'].value,
        "status": 1,
        "qushilganVaqt":null
    }
    mijoz_service.create(JSON.stringify(newPost), function () {
        location.reload();
    }, function (err) {
        console.log('xato yuz berdi');
    });
}
/**
 * Mijoz qo'shish tugadi
 */
/**
 * Yozish boshlandi
 */
function yozish() {
    let ism = document.forms['createForm']['name'].value;
    let number = document.forms['createForm']['number'].value;
    let izoh = document.forms['createForm']['izoh'].value;
    let tugma = document.getElementById('mijozQushish');
    if (izoh == "") { 
        document.forms['createForm']['izoh'].value = "";
    }
    if (ism == "" || number == "") {
        tugma.disabled = true;
    } else {
        tugma.disabled = false;
    }
}
/**
 * Yozish tugadi
 */
/**
 * Xabar sana buyicha boshlandi
 */
 function mijozsana(){
    let sana=document.getElementById("sana");
    mijoz_service.getAllSana(sana.value,printpost,console.log(""));
}
/**
 * Xabar sana buyicha tugadi
 */