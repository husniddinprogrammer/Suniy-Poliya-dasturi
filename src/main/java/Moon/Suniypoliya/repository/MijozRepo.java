package Moon.Suniypoliya.repository;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Mijoz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MijozRepo extends JpaRepository<Mijoz,Long> {
    public List<Mijoz> findAllByOrderByIdDesc();
    public List<Mijoz> findAllByQushilganVaqtBetweenOrderByIdDesc(LocalDateTime sana1, LocalDateTime sana2);
}
