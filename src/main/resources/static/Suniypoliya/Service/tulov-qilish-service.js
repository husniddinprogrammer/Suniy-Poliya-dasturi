let tulovQilishApi = "/api/tulov-qilish/";
var tulovQilish_service = {
    getAll: function (success, error) {
        return http.get(tulovQilishApi, success, error);
    },
    getById: function (id, success, error) {
        return http.get(tulovQilishApi + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(tulovQilishApi, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(tulovQilishApi + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(tulovQilishApi + id, success, error);
    },
    getAllBuyurtmaId: function (id,success, error) {
        return http.get(tulovQilishApi+"buyurtma/"+id, success, error);
    }
}