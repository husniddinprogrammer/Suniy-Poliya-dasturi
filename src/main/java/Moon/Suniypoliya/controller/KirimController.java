package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.Chiqim;
import Moon.Suniypoliya.entity.Poliya;
import Moon.Suniypoliya.service.ChiqimService;
import Moon.Suniypoliya.service.PoliyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/kirim")
@CrossOrigin(origins = "*",maxAge = 3600)
public class KirimController {
    @Autowired
    private ChiqimService ps;
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception{
        return new ResponseEntity(ps.getAll(), HttpStatus.OK);
    }
    @PostMapping(value = "/")
    public ResponseEntity save(@RequestBody Chiqim chiqim) throws Exception{

        return  new ResponseEntity(ps.add(chiqim),HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception{
        return new ResponseEntity( ps.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePoliya(@RequestBody Chiqim chiqim, @PathVariable long id) throws Exception {

        Optional<Chiqim> ChiqimOptional = ps.getById(id);

        if (!ChiqimOptional.isPresent())
            return ResponseEntity.notFound().build();

        chiqim.setId(id);
        ps.update(chiqim);
        return ResponseEntity.noContent().build();
    }
}
