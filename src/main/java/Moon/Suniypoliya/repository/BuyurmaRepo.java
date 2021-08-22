package Moon.Suniypoliya.repository;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Xabar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BuyurmaRepo extends JpaRepository<Buyurtma, Long> {
    public List<Buyurtma> findAllByOrderByIdDesc();
    public List<Buyurtma> findAllByPoliyaIdAndStatusGreaterThanEqualAndStatusLessThanEqualAndSanaBetween(Long poliyaId, Integer aktiv1,Integer aktiv2,LocalDate sana, LocalDate sana7Next);
    public List<Buyurtma> findAllBySanaBetween(LocalDate sana1,LocalDate sana2);
    public List<Buyurtma> findAllBySanaBetweenAndPoliyaId(LocalDate sana1,LocalDate sana2,Long id);
    public List<Buyurtma> findAllByPoliyaUserIdOrderByIdDesc(Long id);
    public List<Buyurtma> findAllByBuyurtmaBerilganVaqtiBetweenOrderByIdDesc(LocalDateTime sana1, LocalDateTime sana2);
    public List<Buyurtma> findAllByBuyurtmaBerilganVaqtiBetweenAndPoliyaUserIdOrderByIdDesc(LocalDateTime sana1, LocalDateTime sana2,Long id);
}