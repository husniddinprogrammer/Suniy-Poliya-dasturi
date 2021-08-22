package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.Chiqim;
import Moon.Suniypoliya.entity.Poliya;
import Moon.Suniypoliya.service.ChiqimService;
import Moon.Suniypoliya.service.PoliyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("api/chiqim")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ChiqimController {
    @Autowired
    private ChiqimService ps;
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception{
        return new ResponseEntity(ps.getAll(), HttpStatus.OK);
    }
    @PostMapping(value = "/")
    public ResponseEntity save(@RequestBody Chiqim chiqim) throws Exception{
        chiqim.setSana(LocalDate.now());
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
    @GetMapping(value = "/month")
    public ResponseEntity<?> getAllMonth() throws Exception{
        return new ResponseEntity(ps.getAllBySanaBetween(), HttpStatus.OK);
    }
    @GetMapping(value = "/week")
    public ResponseEntity<?> getAllWeek() throws Exception{
        return new ResponseEntity(ps.getAllBySanaBetweenWeek(), HttpStatus.OK);
    }
    @GetMapping(value = "/today")
    public ResponseEntity<?> getAllToday() throws Exception{
        return new ResponseEntity(ps.getAllBySanaBetweenToday(), HttpStatus.OK);
    }
    @GetMapping(value = "/{sana1}/{sana2}")
    public ResponseEntity<?> getAllToday(@PathVariable String sana1,@PathVariable String sana2) throws Exception{
        return new ResponseEntity(ps.getAllBySanaBetween(sana1,sana2), HttpStatus.OK);
    }
    @GetMapping(value = "/{sana1}/{sana2}/{id}")
    public ResponseEntity<?> getAllTodayPoliya(@PathVariable String sana1,@PathVariable String sana2,@PathVariable Long id) throws Exception{
        return new ResponseEntity(ps.getAllBySanaBetweenAndPoliya(sana1,sana2,id), HttpStatus.OK);
    }
}
