/**
 * Xabarlar boshlandi
 */
 xabar_service.getAllToday(xabarlarQuyish,console.log('xato'));
 function xabarlarQuyish(json1){
     xabarlar=JSON.parse(json1);
     let div=document.getElementById('accordion');
     let txt="";
     document.getElementById("xabarTuriSelect").value=0;
     let turi=0;
     for(let i=0;i<xabarlar.length;i++){
         let rang="";
         let xabar1=(xabarlar[i].tuliqXabar1).replaceAll("*+","&nbsp;&nbsp;&nbsp;");
         let xabar2=(xabarlar[i].tuliqXabar2).replaceAll("*+","&nbsp;&nbsp;&nbsp;");
         let xabar3=(xabarlar[i].tuliqXabar3).replaceAll("*+","&nbsp;&nbsp;&nbsp;");
         let xabar4=(xabarlar[i].tuliqXabar4).replaceAll("*+","&nbsp;&nbsp;&nbsp;");
         switch(xabarlar[i].xabarTuri.id) {
            case 2:rang="blue";turi=1;break;
            case 3:rang="blue";turi=1;break;
            case 4:rang="blue";turi=1;break;
            case 5:rang="blue";turi=1;break;
            case 6:rang="red";turi=5;break;
            case 7:rang="red";turi=5;break;
            case 8:rang="red";turi=5;break;
            case 9:rang="green";turi=2;break;
            case 10:rang="green";turi=2;break;
            case 14:rang="rgb(255, 174, 0);";turi=4;break;
            case 15:rang="rgb(255, 174, 0);";turi=4;break;
            case 16:rang="blue";turi=1;break;
            case 17:rang="rgb(255, 174, 0);";turi=3;break;
            case 18:rang="rgb(255, 174, 0);";turi=3;break;
            case 19:rang="rgb(255, 174, 0);";turi=3;break;
            }
         if(xabarlar[i].status==1){
            txt+=`
            <div class="panel panel-default turi${turi}">
                <div class="panel-heading" role="tab" id="heading${xabarlar[i].id}" >
                    <h4 class="panel-title" >
                        <a onclick="korildi(${xabarlar[i].id})" id="korildi${xabarlar[i].id}" role="button" data-toggle="collapse" data-parent="#accordion" style="color: black;"  href="#collapse${xabarlar[i].id}" aria-expanded="false" aria-controls="collapse${xabarlar[i].id}">
                            <i class="more-less glyphicon glyphicon-plus"><i class="fas fa-bell" id="qongiroq${xabarlar[i].id}" style="color: ${rang};font-size: medium;"></i> </i>
                            ${xabarlar[i].qisqaXabar}<span id="xabarTuriAjratilgan" style="background-color: ${rang};">${xabarlar[i].xabarTuri.nomi}</span>
                            ${xabarlar[i].xabarVaqti} </a>
                    </h4>
                </div>
                <div id="collapse${xabarlar[i].id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${xabarlar[i].id}">
                    <div class="panel-body">
                    ${xabar1}${xabar2}${xabar3}${xabar4}    
                    </div>
                </div>
            </div>`;
        }
        if(xabarlar[i].status==2){
            txt+=`
            <div class="panel panel-default turi${turi}">
                <div class="panel-heading" role="tab" id="heading${xabarlar[i].id}" >
                    <h4 class="panel-title" >
                        <a onclick="korildi(${xabarlar[i].id})" id="korildi${xabarlar[i].id}" role="button" data-toggle="collapse" data-parent="#accordion"  style="color:rgb(126, 126, 126)" href="#collapse${xabarlar[i].id}" aria-expanded="false" aria-controls="collapse${xabarlar[i].id}">
                            <i class="more-less glyphicon glyphicon-plus"> <i  style="display: none;" class="fas fa-bell" id="qongiroq${xabarlar[i].id}" style="color: ${rang};font-size: medium;"></i> </i>
                            ${xabarlar[i].qisqaXabar}<span id="xabarTuriAjratilgan" style="background-color: ${rang};">${xabarlar[i].xabarTuri.nomi}</span>
                            ${xabarlar[i].xabarVaqti}</a>
                    </h4>
                </div>
                <div id="collapse${xabarlar[i].id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${xabarlar[i].id}">
                    <div class="panel-body">
                    ${xabar1}${xabar2}${xabar3}${xabar4}    
                    </div>
                </div>
            </div>`;
        }
 
     }
     div.innerHTML=txt;
     yuklashlavozim();
    if (aniqLavozim == "CREATOR" || aniqLavozim == "BOSS") {
        document.getElementById("rollBody").style.display="";
    }
    else{
        window.history.back();
    }
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
        xabar_service.getAllOqildi(id,console.log("ishladi"),console.log(""));    
    }
}
 /**
  * Xabarlar korildi tugadi
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
 * Xabar turi boshlandi
 */
function xabarTuri(){
    let qiymati=document.getElementById('xabarTuriSelect').value;
    if(qiymati!=0){
        for(j=0;j<document.querySelectorAll("#accordion .panel-default").length;j++){
            document.querySelectorAll("#accordion .panel-default")[j].style.display="none";
        }  
        for(let i=0;i<document.querySelectorAll(".turi"+qiymati).length;i++){
            document.querySelectorAll(".turi"+qiymati)[i].style.display="";
        };
    }
    else{
        for(j=0;j<document.querySelectorAll("#accordion .panel-default").length;j++){
            document.querySelectorAll("#accordion .panel-default")[j].style.display="";
        }   
    }
}
/**
 * Xabar turi tugadi 
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
