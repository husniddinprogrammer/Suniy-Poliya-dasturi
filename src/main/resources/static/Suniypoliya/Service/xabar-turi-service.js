let xabar_turi_Api = "/api/xabar-turi/";
var xabar_turi_service = {
    getAll: function (success, error) {
        return http.get(xabar_turi_Api, success, error);
    },
    getById: function (id, success, error) {
        return http.get(xabar_turi_Api + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(xabar_turi_Api, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(xabar_turi_Api + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(xabar_turi_Api + id, success, error);
    }
}