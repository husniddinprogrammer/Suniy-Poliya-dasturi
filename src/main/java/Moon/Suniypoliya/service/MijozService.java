package Moon.Suniypoliya.service;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Mijoz;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MijozService extends PublicService<Mijoz,Long> {
    public Mijoz getBekorQilish(Long id);
    public List<Mijoz> getAllByQushilganVaqtBetweenOrderByIdDesc(LocalDate sana1);
    public List<Mijoz> getAllByQushilganVaqtBetweenOrderByIdDescWeek(LocalDate sana1);
}
