let buyurtmaApi = "/api/buyurtma/";
var buyurtma_service = {
    getAll: function (success, error){
        return http.get(buyurtmaApi, success, error);
    },
    getAllKuntartibi: function (id,success, error) {
        return http.get(buyurtmaApi+"kuntartibi/"+id+"/", success, error);
    },
    getAllTodays: function (success, error) {
        return http.get(buyurtmaApi+"todays", success, error);
    },
    getAllSana: function (sana,success, error) {
        return http.get(buyurtmaApi+"sana/"+sana,  success, error);
    },
    getById: function (id, success, error) {
        return http.get(buyurtmaApi + id, success, error);
    },
    create: function (data, success, error){
        return    http.post(buyurtmaApi, data, success, error);
    },
    update: function (data,id, success, error){
        return    http.put(buyurtmaApi + id, data, success, error);
    },
    delete: function (id, success, error){
        return    http.delete(buyurtmaApi + id, success, error);
    },
    getAllBuyurtmaVaqt: function (vaqt,success, error) {
        return http.get(buyurtmaApi+"tulov/"+vaqt, success, error);
    },
    getAllMonth: function (success, error) {
        return http.get(buyurtmaApi+"month", success, error);
    },
    getAllWeek: function (success, error) {
        return http.get(buyurtmaApi+"week", success, error);
    },
    getAllToday: function (success, error) {
        return http.get(buyurtmaApi+"today", success, error);
    },
    getAllDanGacha: function (sana1,sana2,success, error) {
        return http.get(buyurtmaApi+sana1+"/"+sana2, success, error);
    },
    getAllDanGachaPoliyaId: function (sana1,sana2,id,success, error) {
        return http.get(buyurtmaApi+sana1+"/"+sana2+"/"+id, success, error);
    },
    getAllPeopleDanGacha: function (sana1,sana2,success, error) {
        return http.get(buyurtmaApi+"people/"+sana1+"/"+sana2, success, error);
    },
    getBekorQilish: function (id,success, error) {
        return http.get(buyurtmaApi+"delete/"+id, success, error);
    }
}