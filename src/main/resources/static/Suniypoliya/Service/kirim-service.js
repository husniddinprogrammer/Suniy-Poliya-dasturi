let kirimApi = "/api/kirim/";
var kirim_service = {
    getAll: function (success, error) {
        return http.get(kirimApi, success, error);
    },
    getById: function (id, success, error) {
        return http.get(kirimApi + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(kirimApi, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(kirimApi + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(kirimApi + id, success, error);
    }
}