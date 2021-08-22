package Moon.Suniypoliya.service;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Chiqim;

import java.util.List;

public interface ChiqimService extends PublicService<Chiqim,Long>{
    public List<Chiqim> getAllBySanaBetween();
    public List<Chiqim> getAllBySanaBetween(String sana1,String sana2);
    public List<Chiqim> getAllBySanaBetweenAndPoliya(String sana1,String sana2,Long id);
    public List<Chiqim> getAllBySanaBetweenWeek();
    public List<Chiqim> getAllBySanaBetweenToday();

}