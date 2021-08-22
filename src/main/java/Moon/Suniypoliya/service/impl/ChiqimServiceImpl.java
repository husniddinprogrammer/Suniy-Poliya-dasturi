package Moon.Suniypoliya.service.impl;

import Moon.Suniypoliya.entity.*;
import Moon.Suniypoliya.repository.*;
import Moon.Suniypoliya.service.ChiqimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChiqimServiceImpl implements ChiqimService {
    @Autowired
    private ChiqimRepo chiqimRepo;
    @Autowired
    private XabarRepo xabarRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private XabarTuriRepo xabarTuriRepo;
    @Autowired
    private PoliyaRepo poliyaRepo;
    @Override
    public List<Chiqim> getAll() throws Exception {
        return chiqimRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Chiqim add(Chiqim chiqim) throws Exception {
        Chiqim  chiqim1=chiqimRepo.save(chiqim);
        Chiqim  chiqim2=chiqimRepo.getById(chiqim1.getId());
        User user =userRepository.getById(1l);
        Date date = new Date();
        XabarTuri xabarTuri=null;
        String qisqaXabar="";
        if(chiqim2.getTulovTuri().getId()==11l){
            xabarTuri=xabarTuriRepo.getById(6l);
            qisqaXabar="Xodimga "+chiqim2.getSumma()+" so'm to'landi.";
        }
        if(chiqim2.getTulovTuri().getId()==12l){
            xabarTuri=xabarTuriRepo.getById(7l);
            qisqaXabar="Soliqqa "+chiqim2.getSumma()+" so'm to'landi.";
        }
        if(chiqim2.getTulovTuri().getId()==13l){
            xabarTuri=xabarTuriRepo.getById(8l);
            qisqaXabar="Boshqa harajatlarga "+chiqim2.getSumma()+" so'm to'landi";
        }
        String tuliqXabar1="<b>To'langan summa:</b> "+chiqim2.getSumma()+
                " so'm*+<b>To'langan sana:</b> "+chiqim2.getSana()+
                "*+<b>To'langan haqida izoh:</b> "+chiqim2.getIzoh();
        String tuliqXabar2 ="*+<b>Poliya nomi:</b> "+poliyaRepo.getById(chiqim2.getPoliya().getId()).getNomi()+
                        "*+<b>Xabar vaqti:</b> "+date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String tuliqXabar3="";
        String tuliqXabar4="";
        xabarRepo.save(new Xabar(null, qisqaXabar,user , xabarTuri, tuliqXabar1,tuliqXabar2,tuliqXabar3,tuliqXabar4, 1, LocalDateTime.now()));
        return chiqim1;
    }

    @Override
    public void update(Chiqim chiqim) throws Exception {
        chiqimRepo.save(chiqim);
    }

    @Override
    public void delete(Chiqim chiqim) {
        deleteById(chiqim.getId());
    }

    @Override
    public void deleteById(Long aLong) {
        chiqimRepo.deleteById(aLong);
    }

    @Override
    public Optional<Chiqim> getById(Long aLong) throws Exception {
        return chiqimRepo.findById(aLong);
    }

    @Override
    public List<Chiqim> getAllBySanaBetween() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.setTime(date);
        c1.setTime(date);
        c.add(Calendar.MONTH,-1);
        return  chiqimRepo.findAllBySanaBetween(LocalDate.parse(formatter.format(c.getTime())),LocalDate.parse(formatter.format(c1.getTime())));
    }

    @Override
    public List<Chiqim> getAllBySanaBetween(String sana1, String sana2) {
        return  chiqimRepo.findAllBySanaBetween(LocalDate.parse(sana1),LocalDate.parse(sana2));
    }

    @Override
    public List<Chiqim> getAllBySanaBetweenAndPoliya(String sana1, String sana2, Long id) {
        return chiqimRepo.findAllBySanaBetweenAndPoliyaId(LocalDate.parse(sana1),LocalDate.parse(sana2),id);
    }


    @Override
    public List<Chiqim> getAllBySanaBetweenWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.setTime(date);
        c1.setTime(date);
        c.add(Calendar.DATE,-7);
        return  chiqimRepo.findAllBySanaBetween(LocalDate.parse(formatter.format(c.getTime())),LocalDate.parse(formatter.format(c1.getTime())));
    }

    @Override
    public List<Chiqim> getAllBySanaBetweenToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c.setTime(date);
        c1.setTime(date);
        return  chiqimRepo.findAllBySanaBetween(LocalDate.parse(formatter.format(c.getTime())),LocalDate.parse(formatter.format(c1.getTime())));
    }

}
