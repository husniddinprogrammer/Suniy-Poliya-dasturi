package Moon.Suniypoliya.repository;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Xabar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface XabarRepo extends JpaRepository<Xabar,Long> {
    public List<Xabar> findAllByOrderByIdDesc();
    public List<Xabar> findAllByXabarTuriIdGreaterThanEqualAndXabarTuriIdLessThanEqualOrderByIdDesc(Long id1,Long id2);
    public List<Xabar> findAllByXabarVaqtiBetweenOrderByIdDesc(LocalDateTime sana1, LocalDateTime sana2);
}
