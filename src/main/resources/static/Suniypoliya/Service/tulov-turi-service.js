let tulov_turi_Api = "/api/tulov-turi/";
var tulov_turi_service = {
    getAll: function (success, error) {
        return http.get(tulov_turi_Api, success, error);
    },
    getById: function (id, success, error) {
        return http.get(tulov_turi_Api + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(tulov_turi_Api, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(tulov_turi_Api + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(tulov_turi_Api + id, success, error);
    }
}