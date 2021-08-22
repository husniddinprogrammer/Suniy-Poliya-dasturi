package Moon.Suniypoliya.service.impl;

import Moon.Suniypoliya.entity.*;
import Moon.Suniypoliya.repository.*;
import Moon.Suniypoliya.security.SecurityUtil;
import Moon.Suniypoliya.service.TulovQilishService;
import Moon.Suniypoliya.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TulovQilishServiceImpl implements TulovQilishService {
    @Autowired
    private TulovQilishRepo tulovQilishRepo;
    @Autowired
    private XabarRepo xabarRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private XabarTuriRepo xabarTuriRepo;
    @Autowired
    private BuyurmaRepo buyurmaRepo;
    @Autowired
    private PoliyaRepo poliyaRepo;
    @Autowired
    private MijozRepo mijozRepo;

    @Override
    public List<TulovQilish> getAll() throws Exception {
        String username = SecurityUtil.getCurrentUserLogin();
        UserDTO user1=userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
        List<TulovQilish> tulovQilish1=null;
        if(user1!=null){
            if(user1.getId()==1){
                tulovQilish1=tulovQilishRepo.findAllByOrderByIdDesc();
            }
            if(user1.getId()!=1){
                tulovQilish1=tulovQilishRepo.findAllByOrderByIdDesc();
            }
        }
        else {
            tulovQilish1=null;
        }
        return tulovQilish1;
    }

    @Override
    public TulovQilish add(TulovQilish tulovQilish) throws Exception {
        String username = SecurityUtil.getCurrentUserLogin();
        List<TulovQilish> tulovBor= tulovQilishRepo.findAllByBuyurtmaId(tulovQilish.getBuyurtma().getId());
        Buyurtma buyurtma2=buyurmaRepo.getById(tulovQilish.getBuyurtma().getId());
        User user=userRepository.getById(1l);
        Mijoz mijoz = mijozRepo.getById(buyurtma2.getMijoz().getId());
        Poliya poliya = poliyaRepo.getById(buyurtma2.getPoliya().getId());
        User poliyaUser=userRepository.getById(poliya.getUser().getId());
        UserDTO user1=userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
        TulovQilish tulovQilish1=null;
        if(user1!=null){
                XabarTuri xabarTuri=null;
                String qisqaXabar="";
                if(tulovBor.toArray().length!=0){
                    TulovQilish oldingiTulov=tulovBor.get(0);
                    Buyurtma buyurtma1=buyurmaRepo.getById(tulovQilish.getBuyurtma().getId());
                    Integer plastik=tulovQilish.getPlastik();
                    Integer naqd=tulovQilish.getNaqd();
                    Integer jami=plastik+naqd;
                    oldingiTulov.setNaqd(naqd);
                    oldingiTulov.setPlastik(plastik);
                    if(jami.equals(buyurtma1.getNarxi())){
                        Buyurtma buyurtmaOldingi=buyurmaRepo.getById(tulovQilish.getBuyurtma().getId());
                        buyurtmaOldingi.setStatus(2);
                        buyurmaRepo.save(buyurtmaOldingi);
                        xabarTuri=xabarTuriRepo.getById(9l);
                        Date date = new Date();
                        qisqaXabar="To'lov to'liq qabul qilindi";
                        String tuliqXabar1="<b>Admin nomi:</b> "+poliyaUser.getUsername()+
                                "*+<b>Poliya nomi:</b> "+poliya.getNomi()+
                                "*+<b>Mijoz ismi:</b> "+mijoz.getIsm()+
                                "*+<b>Admin raqami:</b> "+poliyaUser.getNumber();
                        String tuliqXabar2="*+<b>To'langan summa:</b> "+jami+
                                " so'm*+<b>Naqd:</b> "+naqd+
                                " so'm*+<b>Plastik:</b> "+plastik+
                                "*+<b>Tulov vaqti:</b> "+date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String tuliqXabar3 = "*+<b>Buyurtma haqida{</b> "+
                                "*+<b>Buyurtma sanasi:</b> "+buyurtmaOldingi.getSana()+
                                "*+<b>Boshlanish vaqti:</b> "+buyurtmaOldingi.getBoshlanishVaqti()+
                                ":00*+<b>Tugash vaqti:</b> "+buyurtmaOldingi.getTugashVaqti();
                        String tuliqXabar4 =":00*+<b>Buyurtma qabul qilingan vaqt:</b> "+buyurtmaOldingi.getBuyurtmaBerilganVaqti()+
                                "*+<b>Buyurtmaga izoh:</b> "+buyurtmaOldingi.getIzoh()+
                                "*+<b>Buyurtmaga bergan:</b> "+user1.getUsername();
                        xabarRepo.save(new Xabar(buyurtmaOldingi, qisqaXabar, user, xabarTuri, tuliqXabar1,tuliqXabar2,tuliqXabar3,tuliqXabar4, 1, LocalDateTime.now()));
                    }
                    else{
                        Buyurtma buyurtmaOldingi=buyurmaRepo.getById(tulovQilish.getBuyurtma().getId());
                        buyurmaRepo.save(buyurtmaOldingi);
                        xabarTuri=xabarTuriRepo.getById(10l);
                        Date date = new Date();
                        qisqaXabar="Buyurtmaga "+jami+" so'm qabul qilindi";
                        String tuliqXabar1="<b>Admin nomi:</b> "+poliyaUser.getUsername()+
                                "*+<b>Poliya nomi:</b> "+poliya.getNomi()+
                                "*+<b>Mijoz ismi:</b> "+mijoz.getIsm()+
                                "*+<b>Admin raqami:</b> "+poliyaUser.getNumber();
                        String tuliqXabar2="*+<b>To'langan summa:</b> "+jami+
                                " so'm*+<b>Naqd:</b> "+naqd+
                                " so'm*+<b>Plastik:</b> "+plastik+
                                "*+<b>Tulov vaqti:</b> "+date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String tuliqXabar3 = "*+<b>Buyurtma haqida{</b> "+
                                "*+<b>Buyurtma sanasi:</b> "+buyurtmaOldingi.getSana()+
                                "*+<b>Boshlanish vaqti:</b> "+buyurtmaOldingi.getBoshlanishVaqti()+
                                ":00*+<b>Tugash vaqti:</b> "+buyurtmaOldingi.getTugashVaqti();
                        String tuliqXabar4 =":00*+<b>Buyurtma qabul qilingan vaqt:</b> "+buyurtmaOldingi.getBuyurtmaBerilganVaqti()+
                                "*+<b>Buyurtmaga izoh:</b> "+buyurtmaOldingi.getIzoh()+
                                "*+<b>Buyurtmaga bergan:</b> "+user1.getUsername();
                        xabarRepo.save(new Xabar(buyurtmaOldingi, qisqaXabar, user, xabarTuri, tuliqXabar1,tuliqXabar2,tuliqXabar3,tuliqXabar4, 1,LocalDateTime.now()));
                    }
                    tulovQilish1= tulovQilishRepo.save(oldingiTulov);
                }
                else{
                    Buyurtma buyurtma1=buyurmaRepo.getById(tulovQilish.getBuyurtma().getId());
                    Integer jami=tulovQilish.getNaqd()+tulovQilish.getPlastik();
                    Integer plastik=tulovQilish.getPlastik();
                    Integer naqd=tulovQilish.getNaqd();
                    if(jami.equals(buyurtma1.getNarxi())){
                        Buyurtma buyurtmaOldingi=buyurmaRepo.getById(tulovQilish.getBuyurtma().getId());
                        buyurtmaOldingi.setStatus(2);
                        buyurmaRepo.save(buyurtmaOldingi);
                        xabarTuri=xabarTuriRepo.getById(9l);
                        Date date = new Date();
                        qisqaXabar="To'lov to'liq qabul qilindi";
                        String tuliqXabar1="<b>Admin nomi:</b> "+poliyaUser.getUsername()+
                                "*+<b>Poliya nomi:</b> "+poliya.getNomi()+
                                "*+<b>Mijoz ismi:</b> "+mijoz.getIsm()+
                                "*+<b>Admin raqami:</b> "+poliyaUser.getNumber();
                        String tuliqXabar2="*+<b>To'langan summa:</b> "+jami+
                                " so'm*+<b>Naqd:</b> "+naqd+
                                " so'm*+<b>Plastik:</b> "+plastik+
                                "*+<b>Tulov vaqti:</b> "+date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String tuliqXabar3 = "*+<b>Buyurtma haqida{:</b> "+
                                "*+<b>Buyurtma sanasi:</b> "+buyurtmaOldingi.getSana()+
                                 "*+<b>Boshlanish vaqti:</b> "+buyurtmaOldingi.getBoshlanishVaqti()+
                                ":00*+<b>Tugash vaqti:</b> "+buyurtmaOldingi.getTugashVaqti();
                        String tuliqXabar4 =":00*+<b>Buyurtma qabul qilingan vaqt:</b> "+buyurtmaOldingi.getBuyurtmaBerilganVaqti()+
                                "*+<b>Buyurtmaga izoh:</b> "+buyurtmaOldingi.getIzoh()+
                        "*+<b>Buyurtmaga bergan:</b> "+user1.getUsername();
                        ;
                        xabarRepo.save(new Xabar(buyurtmaOldingi, qisqaXabar, user, xabarTuri, tuliqXabar1,tuliqXabar2,tuliqXabar3,tuliqXabar4, 1, LocalDateTime.now()));
                    }
                    else{
                        Buyurtma buyurtmaOldingi=buyurmaRepo.getById(tulovQilish.getBuyurtma().getId());
                        buyurmaRepo.save(buyurtmaOldingi);
                        xabarTuri=xabarTuriRepo.getById(10l);
                        Date date = new Date();
                        qisqaXabar="Buyurtmaga "+jami+" so'm qabul qilindi";
                        String tuliqXabar1="<b>Admin nomi:</b> "+poliyaUser.getUsername()+
                                "*+<b>Poliya nomi:</b> "+poliya.getNomi()+
                                "*+<b>Mijoz ismi:</b> "+mijoz.getIsm()+
                                "*+<b>Admin raqami:</b> "+poliyaUser.getNumber();
                        String tuliqXabar2="*+<b>To'langan summa:</b> "+jami+
                                " so'm*+<b>Naqd:</b> "+naqd+
                                " so'm*+<b>Plastik:</b> "+plastik+
                                "*+<b>Tulov vaqti:</b> "+date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String tuliqXabar3= "*+<b>Buyurtma haqida{</b> "+
                                "*+<b>Buyurtma sanasi:</b> "+buyurtmaOldingi.getSana()+
                                "*+<b>Boshlanish vaqti:</b> "+buyurtmaOldingi.getBoshlanishVaqti()+
                                ":00*+<b>Tugash vaqti:</b> "+buyurtmaOldingi.getTugashVaqti();
                        String tuliqXabar4=":00*+<b>Buyurtma qabul qilingan vaqt:</b> "+buyurtmaOldingi.getBuyurtmaBerilganVaqti()+
                                "*+<b>Buyurtmaga izoh:</b> "+buyurtmaOldingi.getIzoh()+
                                "*+<b>Buyurtmaga bergan:</b> "+user1.getUsername();
                        xabarRepo.save(new Xabar(buyurtmaOldingi, qisqaXabar, user, xabarTuri, tuliqXabar1,tuliqXabar2,tuliqXabar3,tuliqXabar4, 1, LocalDateTime.now()));
                    }
                    tulovQilish1= tulovQilishRepo.save(tulovQilish);
                }
        }
        else {
            tulovQilish1=null;
        }

        return null;
    }

    @Override
    public void update(TulovQilish tulovQilish) throws Exception {

    }

    @Override
    public void delete(TulovQilish tulovQilish) {
        deleteById(tulovQilish.getId());
    }

    @Override
    public void deleteById(Long aLong) {
       tulovQilishRepo.deleteById(aLong);
    }

    @Override
    public Optional<TulovQilish> getById(Long aLong) throws Exception {
        String username = SecurityUtil.getCurrentUserLogin();
        UserDTO user1=userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
        Optional<TulovQilish> tulovQilish1=null;
        TulovQilish tulovQilish2=tulovQilishRepo.getById(aLong);
        Buyurtma buyurtma1=buyurmaRepo.getById(tulovQilish2.getBuyurtma().getId());
        Poliya poliya1=poliyaRepo.getById(buyurtma1.getPoliya().getId());
        if(user1!=null){
            if(user1.getId()==1 || user1.getId()==19){
                tulovQilish1=tulovQilishRepo.findById(aLong);
            }
            if(user1.getId().equals(poliya1.getUser().getId())){
                tulovQilish1=tulovQilishRepo.findById(aLong);
            }
        }
        else {
            tulovQilish1=null;
        }
        return tulovQilishRepo.findById(aLong);
    }

    @Override
    public List<TulovQilish> getAllByBuyurtmaId(Long id) {
        return tulovQilishRepo.findAllByBuyurtmaId(id);
    }
}
