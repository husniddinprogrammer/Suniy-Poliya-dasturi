package Moon.Suniypoliya.repository;

import Moon.Suniypoliya.entity.Chiqim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChiqimRepo extends JpaRepository<Chiqim,Long> {
    public List<Chiqim> findAllByOrderByIdDesc();
    public List<Chiqim> findAllBySanaBetween(LocalDate sana1, LocalDate sana2);
    public List<Chiqim> findAllBySanaBetweenAndPoliyaId(LocalDate sana1, LocalDate sana2,Long id);
}
