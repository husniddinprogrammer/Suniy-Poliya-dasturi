let userApi = "/api/user";
var user_service = {
    getAll: function (success, error) {
        return http.get(userApi, success, error);
    },
    getProfile: function (success, error) {
        return http.get(userApi+"/profile/", success, error);
    },
    getProfileUpdate: function (data,success, error) {
        return http.post(userApi+"/profile/parol",data,success, error);
    },
    getById: function (id, success, error) {
        return http.get(userApi + "/"+id, success, error);
    },
    create: function (data, success, error){
        return    http.post(userApi, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(userApi +  "/"+id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(userApi +  "/"+id, success, error);
    }
}