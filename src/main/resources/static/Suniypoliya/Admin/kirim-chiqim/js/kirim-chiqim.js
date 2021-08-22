/**
 * Xabarlar boshlandi
 */
xabar_service.getAllToday(xabarlarQuyish,console.log('xato'));
function xabarlarQuyish(json1){
    xabarlar=JSON.parse(json1);
    let div=document.getElementById('accordion');
    let txt="";
    for(let i=0;i<xabarlar.length;i++){
        let xabar1=(xabarlar[i].tuliqXabar1).replaceAll("*+","&nbsp;&nbsp;&nbsp;");
        let xabar2=(xabarlar[i].tuliqXabar2).replaceAll("*+","&nbsp;&nbsp;&nbsp;");
        let xabarTur="";
        let rang;
        switch(xabarlar[i].xabarTuri.id) {
            case 6:xabarTur="Maosh";rang="red";break;
            case 7:xabarTur="Soliq";rang="red";break;
            case 8:xabarTur="Boshqa harajatlar";rang="red";break;
            case 9:xabarTur="To'liq to'landi";rang="green";break;
            case 10:xabarTur="Bir qismi tulandi";rang="green";break;
         }
         if(xabarlar[i].xabarTuri.id==6 || xabarlar[i].xabarTuri.id==7 || xabarlar[i].xabarTuri.id==8 || xabarlar[i].xabarTuri.id==9 || xabarlar[i].xabarTuri.id==10){
             if(xabarlar[i].status==1){
            txt+=`
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="heading${xabarlar[i].id}" >
                    <h4 class="panel-title" >
                        <a onclick="korildi(${xabarlar[i].id})" id="korildi${xabarlar[i].id}" role="button" data-toggle="collapse" data-parent="#accordion" style="color: black;"  href="#collapse${xabarlar[i].id}" aria-expanded="false" aria-controls="collapse${xabarlar[i].id}">
                        <i class="more-less glyphicon glyphicon-plus"> <i class="fas fa-bell" id="qongiroq${xabarlar[i].id}" style="color: ${rang};font-size: medium;"></i> </i>
                            ${xabarlar[i].qisqaXabar}<span id="xabarTuriAjratilgan" style="background-color:${rang}">${xabarTur}</span>
                            ${xabarlar[i].xabarVaqti} </a>
                    </h4>
                </div>
                <div id="collapse${xabarlar[i].id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${xabarlar[i].id}">
                    <div class="panel-body">
                    ${xabar1}${xabar2}    
                    </div>
                </div>
            </div>`;
        }//style="display: none;"
        if(xabarlar[i].status==2){
            txt+=`
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="heading${xabarlar[i].id}" >
                    <h4 class="panel-title" >
                        <a onclick="korildi(${xabarlar[i].id})" id="korildi${xabarlar[i].id}" role="button" data-toggle="collapse" data-parent="#accordion"  style="color:rgb(126, 126, 126)" href="#collapse${xabarlar[i].id}" aria-expanded="false" aria-controls="collapse${xabarlar[i].id}">
                        <i class="more-less glyphicon glyphicon-plus"> <i  style="display: none;color: ${rang};" class="fas fa-bell" id="qongiroq${xabarlar[i].id}" style="color: ${rang};font-size: medium;"></i> </i>
                            ${xabarlar[i].qisqaXabar}<span id="xabarTuriAjratilgan" style="background-color:${rang}">${xabarTur}</span>
                            ${xabarlar[i].xabarVaqti} </a>
                    </h4>
                </div>
                <div id="collapse${xabarlar[i].id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${xabarlar[i].id}">
                    <div class="panel-body">
                    ${xabar1}${xabar2}    
                    </div>
                </div>
            </div>`;
        }
         }
        

    }
    div.innerHTML=txt;
}
/**
 * Xabarlar tugadi
 */
/**
 * Xabarlar korildi boshlandi
 */
 function korildi(id){
    let qongiroq=document.getElementById("qongiroq"+id);
    let korildi=document.getElementById("korildi"+id);
    if(qongiroq.style.display!='none'){
        qongiroq.style.display='none';
        korildi.style.color="rgb(126, 126, 126)";
        xabar_service.getAllOqildi(id,console.log("ishladi"),console.log("xato"));    
    }
}
/**
 * Xabarlar korildi tugadi
 */


/**
 * TulovTuri select boshlandi
 */
tulov_turi_service.getAll(function (json1) {
    poliya_service.getAll(function (json2) {
        let txt = "";
        let tulovTuri = JSON.parse(json1);
        for (let i = 0; i < tulovTuri.length; i++) {
            txt += `<option value="${tulovTuri[i].id}">${tulovTuri[i].nomi}</option>`;
        }
        document.getElementById('tulovTuriSelect').innerHTML = txt;
        let txt1 = "";
        let poliya = JSON.parse(json2);
        for (let i = 0; i < poliya.length; i++) {
            txt1 += `<option value="${poliya[i].id}">${poliya[i].nomi}</option>`;
        }
        document.getElementById('poliyaSelect').innerHTML = txt1;
        yuklashlavozim();
    if (aniqLavozim == "CREATOR" || aniqLavozim == "BOSS") {
        document.getElementById("rollBody").style.display="";
    }
    else{
        window.history.back();
    }
    }, console.log('xato'));
}, console.log('xato'));
/**
 * TulovTuri select tugadi
 */
/**
 * Tulov qushish boshlandi
 */
function addChiqim() {
    let formMalumot = new FormData(document.forms['createChiqimForm']);
    let e = document.getElementById('tulovTuriSelect');
    let e1 = document.getElementById('poliyaSelect');
    let newPost = {
        "user": {
            "id": 1
        },
        "poliya": {
            "id": e1.value
        },
        "tulovTuri": {
            "id": e.value
        },
        "summa": formMalumot.get('summa'),
        "sana": null,
        "izoh": formMalumot.get('izoh'),
        "status": 1
    }

    chiqim_service.create(JSON.stringify(newPost), function () {
        location.reload();
    }, function (err) {
        console.log("Xatolik yuz berdi");
    });
}
/**
 * Tulov qushish tugadi
 */
/**
 * collapse boshlandi
 */
/*******************************
* ACCORDION WITH TOGGLE ICONS
*******************************/
function toggleIcon(e) {
    $(e.target)
        .prev('.panel-heading')
        .find(".more-less")
        .toggleClass('glyphicon-plus glyphicon-minus');
}
$('.panel-group').on('hidden.bs.collapse', toggleIcon);
$('.panel-group').on('shown.bs.collapse', toggleIcon);
/**
 * collapse tugadi
 */
/**
 * Yozilganda boshlandi
 */
function yozilganda(){
    summa=document.forms['createChiqimForm']["summa"].value;
    izoh=document.forms['createChiqimForm']["izoh"].value;
    tugma=document.getElementById('addChiqim');
    if(summa=="" || izoh==""){
        tugma.disabled=true;
    }
    else{
        tugma.disabled=false;
    }
}
/**
 * Yozilganda tugadi
 */
/**
 * Xabar sana buyicha boshlandi
 */
 function xabarsana(){
    let sana=document.getElementById("sana");
    xabar_service.getAllSana(sana.value,xabarlarQuyish,console.log(""));
}
/**
 * Xabar sana buyicha tugadi
 */
