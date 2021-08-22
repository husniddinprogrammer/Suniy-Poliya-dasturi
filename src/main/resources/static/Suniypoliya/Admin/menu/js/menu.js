/**
 * Menu tanlash boshlandi
 */
function menu(){
if(aniqLavozim=="CREATOR" || aniqLavozim=="BOSS"){
    document.getElementById("menuDashboard").style.display="";
    document.getElementById("menuProfile").style.display="";
    document.getElementById("menuMijozlarTable").style.display="";
    document.getElementById("menuPoliyaTable").style.display="";
    document.getElementById("menuBuyurtmaTable").style.display="";
    document.getElementById("menuKirimChiqim").style.display="";
    document.getElementById("menuXabarTable").style.display="";
    document.getElementById("menuMapGoogle").style.display=""; 
}
if(aniqLavozim=="ADMIN"){
    document.getElementById("menuDashboard").style.display="";
    document.getElementById("menuProfile").style.display="";
    document.getElementById("menuMijozlarTable").style.display="";
    document.getElementById("menuPoliyaTable").style.display="";
    document.getElementById("menuBuyurtmaTable").style.display="";
    document.getElementById("menuMapGoogle").style.display=""; 
}  
if(aniqLavozim=="PEOPLE"){
    window.history.back();
}
}
/**
 * Menu tanlash tugadi
 */