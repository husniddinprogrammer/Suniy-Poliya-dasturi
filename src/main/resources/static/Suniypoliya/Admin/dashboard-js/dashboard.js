mijoz_service.getAll(function (json1){
    poliya_service.getAll(function(json2){
        buyurtma_service.getAll(function(json3){
            boshla(JSON.parse(json1),JSON.parse(json2),JSON.parse(json3));
        },console.log('Xatolik yuz berdi'));
    },console.log('Xatolik yuz berdi'));
},console.log('xatolik yuz berdi'));

function boshla(odamlar, poliya,buyurtma) {
    document.getElementById("odamlar").innerHTML = odamlar.length;
    document.getElementById("buyurtmalar").innerHTML = buyurtma.length;
    document.getElementById("poliyalar").innerHTML = poliya.length;
    let aktiv=0;
    for(let i=0;i<buyurtma.length;i++){
        if(buyurtma[i].status==1){
           aktiv++;
        }
    }
    document.getElementById("aktivbuyurtmalar").innerHTML = aktiv;
    console.log(aniqLavozim);
    yuklashlavozim();
}
