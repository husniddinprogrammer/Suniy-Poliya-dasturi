let mijozApi = "/api/mijoz/";
var mijoz_service = {
    getAll: function (success, error) {
        return http.get(mijozApi, success, error);
    },
    getAllTodays: function (success, error) {
        return http.get(mijozApi+"todays", success, error);
    },
    getAllWeeks: function (success, error) {
        return http.get(mijozApi+"weeks", success, error);
    },
    getAllSana: function (sana,success, error) {
        return http.get(mijozApi+"sana/"+sana,  success, error);
    },
    getById: function (id, success, error) {
        return http.get(mijozApi + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(mijozApi, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(mijozApi + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(mijozApi + id, success, error);
    },
    getBekorQilish: function (id,success, error) {
        return http.get(mijozApi+"delete/"+id, success, error);
    },
}