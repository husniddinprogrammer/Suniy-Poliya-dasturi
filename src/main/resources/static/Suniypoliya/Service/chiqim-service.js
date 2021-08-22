let chiqimApi = "/api/chiqim/";
var chiqim_service = {
    getAll: function (success, error) {
        return http.get(chiqimApi, success, error);
    },
    getById: function (id, success, error) {
        return http.get(chiqimApi + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(chiqimApi, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(chiqimApi + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(chiqimApi + id, success, error);
    },
    getAllMonth: function (success, error) {
        return http.get(chiqimApi+"month", success, error);
    },
    getAllWeek: function (success, error) {
        return http.get(chiqimApi+"week", success, error);
    },
    getAllToday: function (success, error) {
        return http.get(chiqimApi+"today", success, error);
    },
    getAllDanGacha: function (sana1,sana2,success, error) {
        return http.get(chiqimApi+sana1+"/"+sana2, success, error);
    },
    getAllDanGachaPoliyaId: function (sana1,sana2,id,success, error) {
        return http.get(chiqimApi+sana1+"/"+sana2+"/"+id, success, error);
    }
}