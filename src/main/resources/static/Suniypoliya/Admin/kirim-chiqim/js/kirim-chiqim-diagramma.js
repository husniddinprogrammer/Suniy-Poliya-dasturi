/**
 * yuklash boshlandi
 */
let monthKutil = 0;
let weekKutil = 0;
let todayKutil = 0;
let monthTulan = 0;
let weekTulan = 0;
let todayTulan = 0;
let monthChiqimValue = 0;
let weekChiqimValue = 0;
let todayChiqimValue = 0;
let sanaKutil = 0;
let sanaTulan = 0;
let sanaChiqimValue = 0;

function yuklash() {
  poliya_service.getAll(poliyaQush, console.log("poliyalar qushilmadi"));
  buyurtma_service.getAllMonth(function (json1) {
    buyurtma_service.getAllWeek(function (json2) {
      buyurtma_service.getAllToday(function (json3) {
        chiqim_service.getAllMonth(function (json4) {
          chiqim_service.getAllWeek(function (json5) {
            chiqim_service.getAllToday(function (json6) {
              statistika(json1, json2, json3, json4, json5, json6);
            }, console.log("xato6"));
          }, console.log("xato5"));
        }, console.log("xato4"));
      }, console.log("xato3"));
    }, console.log("xato2"));
  }, console.log("xato1"));
}
/**
 * yuklash tugadi
 */

/**
 * date input boshlandi
 */
function dateinput() {
  tanlanganSana1 = document.getElementById('start1');
  tanlanganSana2 = document.getElementById('start2');
  if (tanlanganSana1.value == "" || tanlanganSana2.value == "") {
    console.log('');
  } else {
    let sana1;
    let sana2;
    sana1 = tanlanganSana1.value;
    sana2 = tanlanganSana2.value;
    buyurtma_service.getAllDanGacha(sana1, sana2, function (json1) {
      chiqim_service.getAllDanGacha(sana1, sana2, function (json2) {
        sanaBuyichaSaralash(json1, json2);
      }, console.log("xato"));
    }, console.log("xato"));
  }
}
/**
 * date input tugadi
 */
/**
 * sanaBuyichaSaralash boshlandi
 */
function sanaBuyichaSaralash(json1, json2) {
  sanaKutil = 0;
  sanaTulan = 0;
  sanaChiqimValue = 0;
  let buyurtmalar = JSON.parse(json1);
  let chiqimlar = JSON.parse(json2);
  for (let j = 0; j < buyurtmalar.length; j++) {
    if (buyurtmalar[j].status == 2) {
      sanaTulan += buyurtmalar[j].narxi;
    }
    if (buyurtmalar[j].status == 1) {
      sanaKutil += buyurtmalar[j].narxi;
    }
  }

  for (let j = 0; j < chiqimlar.length; j++) {
    sanaChiqimValue += chiqimlar[j].summa;
  }
  chiz()
}
/**
 * sanaBuyichaSaralash tugadi
 */

/**
 * Poliya select boshlandi
 */
let jami = 0;

function poliyaQush(json) {
  let poliyalarJson = JSON.parse(json);
  let poliyaSelect = document.getElementById('poliyalar11');
  let txt = ""
  jami = poliyalarJson.length;
  txt += `<option value="0">Hammasi</option>`;
  for (let i = 0; i < poliyalarJson.length; i++) {
    if (poliyalarJson[i].status == 1) {
      txt += `
        <option value="${poliyalarJson[i].id}">${poliyalarJson[i].nomi}</option>`
    }
  }
  poliyaSelect.innerHTML = txt;
}
/**
 * Poliya select tugadi
 */
/**
 * statistika boshlandi
 */
function statistika(json1, json2, json3, json4, json5, json6) {
  let monthBuyurtma = JSON.parse(json1);
  let weekBuyurtma = JSON.parse(json2);
  let todayBuyurtma = JSON.parse(json3);
  let monthChiqim = JSON.parse(json4);
  let weekChiqim = JSON.parse(json5);
  let todayChiqim = JSON.parse(json6);
  for (let j = 0; j < monthBuyurtma.length; j++) {
    if (monthBuyurtma[j].status == 2) {
      monthTulan += monthBuyurtma[j].narxi;
    }
    if (monthBuyurtma[j].status == 1) {
      monthKutil += monthBuyurtma[j].narxi;
    }
  }
  for (let j = 0; j < weekBuyurtma.length; j++) {
    if (weekBuyurtma[j].status == 2) {
      weekTulan += weekBuyurtma[j].narxi;
    }
    if (weekBuyurtma[j].status == 1) {
      weekKutil += weekBuyurtma[j].narxi;
    }
  }
  for (let j = 0; j < todayBuyurtma.length; j++) {
    if (todayBuyurtma[j].status == 2) {
      todayTulan += todayBuyurtma[j].narxi;
    }
    if (todayBuyurtma[j].status == 1) {
      todayKutil += todayBuyurtma[j].narxi;
    }
  }
  for (let j = 0; j < monthChiqim.length; j++) {
    monthChiqimValue += monthChiqim[j].summa;
  }
  for (let j = 0; j < weekChiqim.length; j++) {
    weekChiqimValue += weekChiqim[j].summa;
  }
  for (let j = 0; j < todayChiqim.length; j++) {
    todayChiqimValue += todayChiqim[j].summa;
  }
  chiz();
}
/**
 * statistika tugadi
 */
/**
 * date input boshlandi
 */
function dateinput() {
  tanlanganSana1 = document.getElementById('start1');
  tanlanganSana2 = document.getElementById('start2');
  let poliyaSelect = document.getElementById("poliyalar11");
  if (tanlanganSana1.value == "" || tanlanganSana2.value == "") {
    console.log('');
  } else {
    let sana1;
    let sana2;
    sana1 = tanlanganSana1.value;
    sana2 = tanlanganSana2.value;
    if (poliyaSelect.value == 0) {
      buyurtma_service.getAllDanGacha(sana1, sana2, function (json1) {
        chiqim_service.getAllDanGacha(sana1, sana2, function (json2) {
          sanaBuyichaSaralash(json1, json2);
        }, console.log("xato"));
      }, console.log("xato"));
    }
    else{
      buyurtma_service.getAllDanGachaPoliyaId(sana1, sana2,poliyaSelect.value,function (json1) {
        chiqim_service.getAllDanGachaPoliyaId(sana1, sana2,poliyaSelect.value,function (json2) {
          sanaBuyichaSaralash(json1, json2);
        }, console.log("xato"));
      }, console.log("xato"));
    }
  }
}
/**
 * date input tugadi
 */
/**
 * sanaBuyichaSaralash boshlandi
 */
function sanaBuyichaSaralash(json1, json2) {
  sanaKutil = 0;
  sanaTulan = 0;
  sanaChiqimValue = 0;
  let buyurtmalar = JSON.parse(json1);
  let chiqimlar = JSON.parse(json2);
  for (let j = 0; j < buyurtmalar.length; j++) {
    if (buyurtmalar[j].status == 2) {
      sanaTulan += buyurtmalar[j].narxi;
    }
    if (buyurtmalar[j].status == 1) {
      sanaKutil += buyurtmalar[j].narxi;
    }
  }

  for (let j = 0; j < chiqimlar.length; j++) {
    sanaChiqimValue += chiqimlar[j].summa;
  }
  chiz()
}
/**
 * sanaBuyichaSaralash tugadi
 */
function chiz() {
  todayFoyda = todayTulan - todayChiqimValue;
  weekFoyda = weekTulan - weekChiqimValue;
  monthFoyda = monthTulan - monthChiqimValue;
  sanaFoyda = sanaTulan - sanaChiqimValue;
  document.getElementById("todayKutilyotgan").innerHTML = "Kutilyotgan summa: " + todayKutil + " so'm";
  document.getElementById("todayKirim").innerHTML = "Kirim summa: " + todayTulan + " so'm";
  document.getElementById("todayChiqim").innerHTML = "Chiqim summa: " + todayChiqimValue + " so'm";
  document.getElementById("todayFoyda").innerHTML = "Sof foyda summa: " + todayFoyda + " so'm";
  document.getElementById("weekKutilyotgan").innerHTML = "Kutilyotgan summa: " + weekKutil + " so'm";
  document.getElementById("weekKirim").innerHTML = "Kirim summa: " + weekTulan + " so'm";
  document.getElementById("weekChiqim").innerHTML = "Chiqim summa: " + weekChiqimValue + " so'm";
  document.getElementById("weekFoyda").innerHTML = "Sof foyda summa: " + weekFoyda + " so'm";
  document.getElementById("monthKutilyotgan").innerHTML = "Kutilyotgan summa: " + monthKutil + " so'm";
  document.getElementById("monthKirim").innerHTML = "Kirim summa: " + monthTulan + " so'm";
  document.getElementById("monthChiqim").innerHTML = "Chiqim summa: " + monthChiqimValue + " so'm";
  document.getElementById("monthFoyda").innerHTML = "Sof foyda summa: " + monthFoyda + " so'm";
  document.getElementById("sanaKutilyotgan").innerHTML = "Kutilyotgan summa: " + sanaKutil + " so'm";
  document.getElementById("sanaKirim").innerHTML = "Kirim summa: " + sanaTulan + " so'm";
  document.getElementById("sanaChiqim").innerHTML = "Chiqim summa: " + sanaChiqimValue + " so'm";
  document.getElementById("sanaFoyda").innerHTML = "Sof foyda summa: " + sanaFoyda + " so'm";
}