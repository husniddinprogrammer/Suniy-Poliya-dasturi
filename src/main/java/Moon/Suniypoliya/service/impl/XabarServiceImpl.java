package Moon.Suniypoliya.service.impl;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.entity.Chiqim;
import Moon.Suniypoliya.entity.Xabar;
import Moon.Suniypoliya.repository.XabarRepo;
import Moon.Suniypoliya.service.XabarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class XabarServiceImpl implements XabarService {
    @Autowired
    private XabarRepo xabarRepo;

    @Override
    public List<Xabar> getAll() throws Exception {
        return xabarRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Xabar add(Xabar xabar) throws Exception {
        return xabarRepo.save(xabar);
    }

    @Override
    public void update(Xabar xabar) throws Exception {
        xabarRepo.save(xabar);
    }

    @Override
    public void delete(Xabar xabar) {
        deleteById(xabar.getId());
    }

    @Override
    public void deleteById(Long aLong) {
        xabarRepo.deleteById(aLong);
    }

    @Override
    public Optional<Xabar> getById(Long aLong) throws Exception {
        return xabarRepo.findById(aLong);
    }

    @Override
    public List<Xabar> getAllByXabarTuriIdGreaterThanEqualAndXabarTuriIdLessThanEqualOrderByIdDesc() {
        return xabarRepo.findAllByXabarTuriIdGreaterThanEqualAndXabarTuriIdLessThanEqualOrderByIdDesc(5l,9l);
    }

    @Override
    public List<Xabar> getAllByXabarVaqtiBetweenOrderByIdDesc(LocalDate sana1) {
        LocalDateTime kun1=sana1.atTime(0,0,0,0);
        return xabarRepo.findAllByXabarVaqtiBetweenOrderByIdDesc(kun1,kun1.plusDays(1));
    }

    @Override
    public Xabar oqildi(Long id) {
        Xabar xabar1= xabarRepo.getById(id);
        xabar1.setStatus(2);
        return xabarRepo.save(xabar1);
    }
}
