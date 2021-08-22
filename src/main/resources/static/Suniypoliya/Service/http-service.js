var baseUrl = "http://localhost:8080";

var http = {
    /**
     * Bu oraqli ma'lumotlar RESTFULL api yordamida get qilinadi
     * @param {*} api - url manzilini bildiriad
     * @param {*} callBack - ajax da o'zgarish bo'lganda tutib olish callBack funksiya
     */
    get: function(api, success, error){
        let ajax = new XMLHttpRequest();
        
        ajax.open("GET", baseUrl + api);
        this.setToken(ajax);
        ajax.onreadystatechange = function (){
           if(this.readyState == 4){
               if(200 <= this.status && this.status < 300){
                   success(this.response);
               } else{
                   error(this.response);
              
                   console.log("boshqa xatoli ro'y berdi")
               }
           }
        }
        ajax.send();

    },
    /**
     *
     * @param {*} api
     * @param {*} data
     * @param {*} callBack
     */
    post: function(api, data, success, error){
        let ajax = new XMLHttpRequest()
        ajax.open("POST", baseUrl + api);
        this.setToken(ajax);
        ajax.onreadystatechange = function (){
            if(this.readyState == 4){
                if(200 <= this.status && this.status < 300){
                    success(this.response);
                } else{
                    error(this.response);
                }
            }
        }
        ajax.setRequestHeader("Content-Type", "application/json");
        ajax.send(data);
    },
    put: function(api, data, success, error){
        let ajax = new XMLHttpRequest()
        ajax.open("PUT", baseUrl + api);
        this.setToken(ajax);
        ajax.onreadystatechange = function (){
            if(this.readyState == 4){
                if(200 <= this.status && this.status < 300){
                    success(this.response);
                } else {
                    error(this.response);
                } 
            }
        }
        ajax.setRequestHeader("Content-Type", "application/json");
        ajax.send(data);
    },
    delete: function(api, success, error){
        let ajax = new XMLHttpRequest();
        ajax.open("DELETE", baseUrl + api);
        this.setToken(ajax);
        ajax.onreadystatechange = function (){
            if(this.readyState == 4){
                if(200 <= this.status && this.status < 300){
                    success(this.response);
                } else{
                    error(this.response);
                } 
            }
        }
        ajax.send();
    },
    setToken: function(ajax){
        const token = localStorage.getItem('token');
        if(token){
            ajax.setRequestHeader("Authorization", "Bearer " + token);
        }
    }
}
