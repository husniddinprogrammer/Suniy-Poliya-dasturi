let poliyaApi = "/api/poliya/";
var poliya_service = {
    getAll: function (success, error) {
        return http.get(poliyaApi, success, error);
    },
    getAllPeople: function (success, error) {
        return http.get(poliyaApi+"people", success, error);
    },
    getById: function (id, success, error) {
        return http.get(poliyaApi + id+"/", success, error);
    },
    create: function (data, success, error) {
        return http.post(poliyaApi, data, success, error);
    },
    update: function (data, id, success, error) {
        return http.put(poliyaApi + id, data, success, error);
    },
    delete: function (id, success, error) {
        return http.delete(poliyaApi + id, success, error);
    },
    getAllAdmin: function (success, error) {
        return http.get(poliyaApi + "admin", success, error);
    },
    getBekorQil: function (id, success, error) {
        return http.get(poliyaApi + "delete/" + id, success, error);
    },
}