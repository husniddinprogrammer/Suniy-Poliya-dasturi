let xabarApi = "/api/xabar/";
var xabar_service = {
    getAll: function (success, error) {
        return http.get(xabarApi, success, error);
    },
    getAllToday: function (success, error) {
        return http.get(xabarApi+"today", success, error);
    },
    getAllSana: function (sana,success, error) {
        return http.get(xabarApi+"sana/"+sana,  success, error);
    },
    getById: function (id, success, error) {
        return http.get(xabarApi + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(xabarApi, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(xabarApi + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(xabarApi + id, success, error);
    },
    getAllKirimChiqim: function (success, error) {
        return http.get(xabarApi+"kirim-chiqim", success, error);
    },
    getAllOqildi: function (id,success, error) {
        return http.get(xabarApi+"oqildi/"+id, success, error);
    }
}