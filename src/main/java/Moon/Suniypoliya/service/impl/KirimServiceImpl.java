package Moon.Suniypoliya.service.impl;

import Moon.Suniypoliya.entity.Kirim;
import Moon.Suniypoliya.repository.KirimRepo;
import Moon.Suniypoliya.service.KirimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KirimServiceImpl implements KirimService {
    @Autowired
    private KirimRepo kirimRepo;

    @Override
    public List<Kirim> getAll() throws Exception {
        return kirimRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Kirim add(Kirim kirim) throws Exception {
        return kirimRepo.save(kirim);
    }

    @Override
    public void update(Kirim kirim) throws Exception {
        kirimRepo.save(kirim);
    }

    @Override
    public void delete(Kirim kirim) {
        deleteById(kirim.getId());
    }

    @Override
    public void deleteById(Long aLong) {
        kirimRepo.deleteById(aLong);
    }

    @Override
    public Optional<Kirim> getById(Long aLong) throws Exception {
        return kirimRepo.findById(aLong);
    }
}
