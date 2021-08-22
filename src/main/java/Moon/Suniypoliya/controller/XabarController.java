package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.Poliya;
import Moon.Suniypoliya.entity.Xabar;
import Moon.Suniypoliya.service.PoliyaService;
import Moon.Suniypoliya.service.XabarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("api/xabar")
@CrossOrigin(origins = "*",maxAge = 3600)
public class XabarController {
    @Autowired
    private XabarService ps;
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception{
        return new ResponseEntity(ps.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/sana/{sana}")
    public ResponseEntity<?> getAllSana(@PathVariable String sana) throws Exception{
        return new ResponseEntity(ps.getAllByXabarVaqtiBetweenOrderByIdDesc(LocalDate.parse(sana)), HttpStatus.OK);
    }
    @GetMapping(value = "/today")
    public ResponseEntity<?> getAllToday() throws Exception{
        return new ResponseEntity(ps.getAllByXabarVaqtiBetweenOrderByIdDesc(LocalDate.now()), HttpStatus.OK);
    }
    @PostMapping(value = "/")
    public ResponseEntity save(@RequestBody Xabar xabar) throws Exception{
        xabar.setXabarVaqti(LocalDateTime.now());
        return  new ResponseEntity(ps.add(xabar),HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception{
        return new ResponseEntity( ps.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePoliya(@RequestBody Xabar xabar, @PathVariable long id) throws Exception {

        Optional<Xabar> XabarOptional = ps.getById(id);

        if (!XabarOptional.isPresent())
            return ResponseEntity.notFound().build();

        xabar.setId(id);
        ps.update(xabar);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/kirim-chiqim")
    public ResponseEntity<?> getByIdKirimChiqim() throws Exception{
        return new ResponseEntity( ps.getAllByXabarTuriIdGreaterThanEqualAndXabarTuriIdLessThanEqualOrderByIdDesc(),HttpStatus.OK);
    }
    @GetMapping(value = "/oqildi/{id}")
    public ResponseEntity<?> getByIdOqildi(@PathVariable Long id) throws Exception{
        return new ResponseEntity( ps.oqildi(id),HttpStatus.OK);
    }
}
