var auth_service = {
    login: function(user, success, error){
        return http.post("/authenticate", JSON.stringify(user), function(res1){
            console.log("keldi");

            let res = JSON.parse(res1);
    
            localStorage.setItem('token', res.token);
            location.href = "../Admin/dashboard/dashboard.html";
    
        }, error);
    },
    people: function(success, error){
        let kun=new Date();
        let tuliqKun=kun.getDate()+kun.getMonth()+kun.getFullYear();
        let user = {}
        user.username = "people1234";
        user.password = "people1234";
        if(localStorage.getItem("poliyaVaqt")==null || localStorage.getItem("token")==null){
            localStorage.setItem('poliyaVaqt',tuliqKun);
            console.log("yuq");
            return http.post("/authenticate", JSON.stringify(user), function(res1){
                let res = JSON.parse(res1);
                localStorage.setItem('token', res.token);
                location.reload();
            }, error);
        }
        else{
            if(localStorage.getItem("poliyaVaqt")!=tuliqKun){
                localStorage.removeItem("poliyaVaqt");
                localStorage.setItem("poliyaVaqt",tuliqKun);
                localStorage.removeItem("token");
                return http.post("/authenticate", JSON.stringify(user), function(res1){
                    let res = JSON.parse(res1);
                    localStorage.setItem('token', res.token);
                    location.reload();
                }, error);
            }
        }

    },
    getToken: function(){
        return localStorage.getItem('token');
    },
    getTokenInfo: function(){
        const token = this.getToken();
        if(token)
        return this.parseJwt(token);
        return null;
    },

    getRoles: function(){
        const info = this.getTokenInfo();
        if(info && info.roles){
            return info.roles;
        }
        else {
            return null;
        }

    },

    parseJwt: function  (token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
   
        return JSON.parse(jsonPayload);
    }
}