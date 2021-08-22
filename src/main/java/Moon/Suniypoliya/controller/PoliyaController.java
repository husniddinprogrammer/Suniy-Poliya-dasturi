package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.Poliya;
import Moon.Suniypoliya.repository.UserRepository;
import Moon.Suniypoliya.security.JwtFilter;
import Moon.Suniypoliya.service.PoliyaService;
import Moon.Suniypoliya.service.impl.PoliyaServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("api/poliya")
@CrossOrigin(origins = "*",maxAge = 3600)
public class PoliyaController {
    @Autowired
    private PoliyaService ps;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity(ps.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity save(@RequestBody Poliya poliya) throws Exception {
        return new ResponseEntity(ps.add(poliya), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity(ps.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePoliya(@RequestBody Poliya poliya, @PathVariable Long id) throws Exception {
        poliya.setId(id);
        return new ResponseEntity(ps.add(poliya), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<?> getBekor(@PathVariable Long id) throws Exception {
        return new ResponseEntity(ps.getBekorQilish(id), HttpStatus.OK);
    }
    @GetMapping(value = "/people")
    public ResponseEntity<?> getPeople() throws Exception {
        return new ResponseEntity(ps.getAllPeople(), HttpStatus.OK);
    }
}