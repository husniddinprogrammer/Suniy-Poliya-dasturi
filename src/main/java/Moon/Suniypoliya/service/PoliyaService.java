package Moon.Suniypoliya.service;

import Moon.Suniypoliya.entity.Mijoz;
import Moon.Suniypoliya.entity.Poliya;

import java.util.List;
import java.util.Optional;

public interface PoliyaService extends PublicService<Poliya,Long> {
    public Poliya getBekorQilish(Long id);
    public List<Poliya> getAllPeople() throws Exception;
}
