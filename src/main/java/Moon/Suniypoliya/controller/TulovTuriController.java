package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.Chiqim;
import Moon.Suniypoliya.entity.TulovTuri;
import Moon.Suniypoliya.service.ChiqimService;
import Moon.Suniypoliya.service.TulovTuriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/tulov-turi")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TulovTuriController {
    @Autowired
    private TulovTuriService ps;
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception{
        return new ResponseEntity(ps.getAll(), HttpStatus.OK);
    }
    @PostMapping(value = "/")
    public ResponseEntity save(@RequestBody TulovTuri tulovTuri) throws Exception{
        return  new ResponseEntity(ps.add(tulovTuri),HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception{
        return new ResponseEntity( ps.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePoliya(@RequestBody TulovTuri tulovTuri, @PathVariable long id) throws Exception {

        Optional<TulovTuri> TulovTuriOptional = ps.getById(id);

        if (!TulovTuriOptional.isPresent())
            return ResponseEntity.notFound().build();

        tulovTuri.setId(id);
        ps.update(tulovTuri);
        return ResponseEntity.noContent().build();
    }
}
