package Moon.Suniypoliya.repository;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.TulovTuri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TulovTuriRepo extends JpaRepository<TulovTuri,Long> {
    public List<TulovTuri> findAllByOrderById();
}
