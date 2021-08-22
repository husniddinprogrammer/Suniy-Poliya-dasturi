var aniqLavozim="1";
function yuklashlavozim(){
    checkDirektor();
}
function checkDirektor() {
    const roles = auth_service.getRoles();
    if (roles && typeof roles == 'object') {
        if (roles.includes("ROLE_CREATOR")) {
            aniqLavozim="CREATOR";
            menu();
             return true;
         }if (roles.includes("ROLE_BOSS")) {
            aniqLavozim="BOSS";
            menu();
             return true;
         }if (roles.includes("ROLE_ADMIN")) {
            aniqLavozim="ADMIN";
            menu();
             return true;
         }
        if (roles.includes("ROLE_PEOPLE")) {
            aniqLavozim="PEOPLE";
            menu();
             return true;
         } else {
            return false;
        }
    }
    return false;
}
