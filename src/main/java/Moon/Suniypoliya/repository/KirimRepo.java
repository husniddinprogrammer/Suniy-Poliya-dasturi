package Moon.Suniypoliya.repository;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Kirim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KirimRepo extends JpaRepository<Kirim,Long> {
    public List<Kirim> findAllByOrderByIdDesc();
}
