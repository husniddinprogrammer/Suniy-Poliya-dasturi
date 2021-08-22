package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.XabarTuri;
import Moon.Suniypoliya.service.XabarTuriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/xabar-turi")
@CrossOrigin(origins = "*",maxAge = 3600)
public class XabarTuriController {
    @Autowired
    private XabarTuriService ps;
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception{
        return new ResponseEntity(ps.getAll(), HttpStatus.OK);
    }
    @PostMapping(value = "/")
    public ResponseEntity save(@RequestBody XabarTuri xabarTuri) throws Exception{
        return  new ResponseEntity(ps.add(xabarTuri),HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception{
        return new ResponseEntity( ps.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePoliya(@RequestBody XabarTuri xabarTuri, @PathVariable long id) throws Exception {

        Optional<XabarTuri> XabarTuriOptional = ps.getById(id);

        if (!XabarTuriOptional.isPresent())
            return ResponseEntity.notFound().build();

        xabarTuri.setId(id);
        ps.update(xabarTuri);
        return ResponseEntity.noContent().build();
    }
}
