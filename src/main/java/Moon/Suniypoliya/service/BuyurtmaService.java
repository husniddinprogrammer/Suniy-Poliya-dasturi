package Moon.Suniypoliya.service;


import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Mijoz;
import Moon.Suniypoliya.entity.Xabar;

import java.security.cert.Extension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BuyurtmaService extends PublicService<Buyurtma,Long> {
    public List<Buyurtma> getAllByPoliyaAndAktivAndSana(Long poliyaId, LocalDate sana, LocalDate sana7Next);
    public List<Buyurtma> getAllBySanaBetween();
    public List<Buyurtma> getAllBySanaBetween(String sana1,String sana2);
    public List<Buyurtma> getAllBySanaBetweenAndPoliyaId(String sana1,String sana2,Long id);
    public List<Buyurtma> getAllBySanaBetweenWeek();
    public List<Buyurtma> getAllBySanaBetweenToday();
    public Buyurtma getBekorQilish(Long id);
    public List<Buyurtma> getAllByBuyurtmaBerilganVaqtiBetweenOrderByIdDesc(LocalDate sana1);
}
