package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.Mijoz;
import Moon.Suniypoliya.service.MijozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("api/mijoz")
@CrossOrigin(origins = "*",maxAge = 3600)
public class MijozController {
    @Autowired
    private MijozService ps;
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception{
        return new ResponseEntity(ps.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/sana/{sana}")
    public ResponseEntity<?> getAllSana(@PathVariable String sana) throws Exception{
        return new ResponseEntity(ps.getAllByQushilganVaqtBetweenOrderByIdDesc(LocalDate.parse(sana)), HttpStatus.OK);
    }
    @GetMapping(value = "/todays")
    public ResponseEntity<?> getAllTodays() throws Exception{
        return new ResponseEntity(ps.getAllByQushilganVaqtBetweenOrderByIdDesc(LocalDate.now()), HttpStatus.OK);
    }
    @GetMapping(value = "/weeks")
    public ResponseEntity<?> getAllWeeks() throws Exception{
        return new ResponseEntity(ps.getAllByQushilganVaqtBetweenOrderByIdDescWeek(LocalDate.now()), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> save(@RequestBody Mijoz mijoz) throws Exception{
        return  new ResponseEntity(ps.add(mijoz),HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception{
        return new ResponseEntity(ps.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePeople(@RequestBody Mijoz mijoz, @PathVariable long id) throws Exception {

        Optional<Mijoz> PeopleOptional = ps.getById(id);

        if (!PeopleOptional.isPresent())
            return ResponseEntity.notFound().build();

        mijoz.setId(id);
        ps.update(mijoz);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<?> getBekor(@PathVariable Long id) throws Exception{
        return new ResponseEntity(ps.getBekorQilish(id), HttpStatus.OK);
    }

}
