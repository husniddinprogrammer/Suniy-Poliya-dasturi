/**
 * yuklash boshlandi
 */
function yuklash(){
user_service.getProfile(function (json){
   let profile = JSON.parse(json);
   yuklashlavozim();
   chiqar(profile);
},console.log("xato"))
}
/**
 * yuklash tugadi
 */
/** 
 * malumotlarni chiqarish boshlandi
*/
function chiqar(profile){
    document.getElementById("username").value=profile.username;
    document.getElementById("ism").value=profile.name;
    document.getElementById("nomer").value=profile.number;
    document.getElementById("sana").value=profile.qushilganVaqti;
    document.getElementById("lavozim").value=aniqLavozim;
}
/**
 * malumotlarni chiqarish tugadi
 */
/**
 * Tahrirlash boshlandi
 */
function bosildi(){
    document.getElementById("tahrirlash").style.display="none";
    document.getElementById("add").style.display="";
    document.getElementById("ism").disabled=false;
    document.getElementById("nomer").disabled=false;
    document.getElementById("paroldiv").style.display="";
}
/**
 * Tahrirlash tugadi
 */
/**
 * yozilyapti boshlandi
 */
function yozilyapti(){
    if(document.getElementById("ism").value=="" || document.getElementById("nomer").value=="" || document.getElementById("parol").value==""){       
        document.getElementById("add").disabled=true;
    }
    else{
        document.getElementById("add").disabled=false;
    }
}
/**
 * yozilyapti tugadi
 */
/**
 * add boshlandi
 */
function qushish(){
    let check = {}
    check.name = document.getElementById("ism").value;
    check.number = document.getElementById("nomer").value;
    check.password = document.getElementById("parol").value;
    check.username = document.getElementById("username").value;
    check.qushilganVaqti = null;
    check.lavozimlar = null;
    check.aktiv = 1;
    user_service.getProfileUpdate(JSON.stringify(check),location.reload(), console.log("gg"));
}
/**
 * add tugadi
 */