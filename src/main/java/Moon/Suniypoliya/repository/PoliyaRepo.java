package Moon.Suniypoliya.repository;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Poliya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliyaRepo extends JpaRepository<Poliya,Long> {
    public List<Poliya> findAllByOrderById();
    public List<Poliya> findAllByUserId(Long id);
}
