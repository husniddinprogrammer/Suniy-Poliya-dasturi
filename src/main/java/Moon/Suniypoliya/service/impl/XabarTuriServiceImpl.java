package Moon.Suniypoliya.service.impl;

import Moon.Suniypoliya.entity.XabarTuri;
import Moon.Suniypoliya.repository.XabarTuriRepo;
import Moon.Suniypoliya.service.XabarTuriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XabarTuriServiceImpl implements XabarTuriService {
    @Autowired
    private XabarTuriRepo xabarTuriRepo;

    @Override
    public List<XabarTuri> getAll() throws Exception {
        return xabarTuriRepo.findAllByOrderById();
    }

    @Override
    public XabarTuri add(XabarTuri xabarTuri) throws Exception {
        return xabarTuriRepo.save(xabarTuri);
    }

    @Override
    public void update(XabarTuri xabarTuri) throws Exception {
        xabarTuriRepo.save(xabarTuri);
    }

    @Override
    public void delete(XabarTuri xabarTuri) {
         deleteById(xabarTuri.getId());
    }

    @Override
    public void deleteById(Long aLong) {
        xabarTuriRepo.deleteById(aLong);
    }

    @Override
    public Optional<XabarTuri> getById(Long aLong) throws Exception {
        return xabarTuriRepo.findById(aLong);
    }
}
