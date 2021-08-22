package Moon.Suniypoliya.service.impl;

import Moon.Suniypoliya.entity.TulovTuri;
import Moon.Suniypoliya.repository.TulovTuriRepo;
import Moon.Suniypoliya.service.TulovTuriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TulovTuriServiceImpl implements TulovTuriService {
    @Autowired
    private TulovTuriRepo tulovTuriRepo;

    @Override
    public List<TulovTuri> getAll() throws Exception {
        return tulovTuriRepo.findAllByOrderById();
    }

    @Override
    public TulovTuri add(TulovTuri tulovTuri) throws Exception {
        return tulovTuriRepo.save(tulovTuri);
    }

    @Override
    public void update(TulovTuri tulovTuri) throws Exception {
        tulovTuriRepo.save(tulovTuri);
    }

    @Override
    public void delete(TulovTuri tulovTuri) {
        deleteById(tulovTuri.getId());
    }

    @Override
    public void deleteById(Long aLong) {
        tulovTuriRepo.deleteById(aLong);
    }

    @Override
    public Optional<TulovTuri> getById(Long aLong) throws Exception {
        return tulovTuriRepo.findById(aLong);
    }
}
