package Moon.Suniypoliya.controller;

import Moon.Suniypoliya.entity.Buyurtma;
import Moon.Suniypoliya.repository.UserRepository;
import Moon.Suniypoliya.security.JwtFilter;
import Moon.Suniypoliya.security.JwtUtil;
import Moon.Suniypoliya.security.UserProvider;
import Moon.Suniypoliya.service.BuyurtmaService;
import Moon.Suniypoliya.service.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/buyurtma")
@CrossOrigin(origins = "*",maxAge = 3600)
public class BuyurtmaController {
    @Autowired
    private BuyurtmaService bs;
    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() throws Exception {
        return ResponseEntity.ok(bs.getAll());
    }
    @PostMapping(value = "/")
    public ResponseEntity<?> save(@RequestBody Buyurtma buyurtma) throws Exception{
        buyurtma.setBuyurtmaBerilganVaqti(LocalDateTime.now());
        return new ResponseEntity(bs.add(buyurtma),HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/sana/{sana}")
    public ResponseEntity<?> getAllSana(@PathVariable String sana) throws Exception{
        return new ResponseEntity(bs.getAllByBuyurtmaBerilganVaqtiBetweenOrderByIdDesc(LocalDate.parse(sana)), HttpStatus.OK);
    }
    @GetMapping(value = "/todays")
    public ResponseEntity<?> getAllTodays() throws Exception{
        return new ResponseEntity(bs.getAllByBuyurtmaBerilganVaqtiBetweenOrderByIdDesc(LocalDate.now()), HttpStatus.OK);
    }
    @GetMapping(value = "/kuntartibi/{id}")
    public ResponseEntity<Buyurtma> getAllByPoliyaAndAktivAndSana(@PathVariable  Long id){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,7);
        return new ResponseEntity(bs.getAllByPoliyaAndAktivAndSana(id,LocalDate.parse(formatter.format(date)),LocalDate.parse(formatter.format(c.getTime()))),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception{

        return  new ResponseEntity(bs.getById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBuyurtma(@RequestBody Buyurtma buyurtma, @PathVariable long id) throws Exception {

        Optional<Buyurtma> BuyurtmaOptional = bs.getById(id);

        if (!BuyurtmaOptional.isPresent())
            return ResponseEntity.notFound().build();

        buyurtma.setId(id);
        bs.update(buyurtma);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/month")
    public ResponseEntity<?> getAllMonth() throws Exception{
        return new ResponseEntity(bs.getAllBySanaBetween(), HttpStatus.OK);
    }
    @GetMapping(value = "/week")
    public ResponseEntity<?> getAllWeek() throws Exception{
        return new ResponseEntity(bs.getAllBySanaBetweenWeek(), HttpStatus.OK);
    }
    @GetMapping(value = "/today")
    public ResponseEntity<?> getAllToday() throws Exception{
        return new ResponseEntity(bs.getAllBySanaBetweenToday(), HttpStatus.OK);
    }

    @GetMapping(value = "/{sana1}/{sana2}")
    public ResponseEntity<?> getAllToday(@PathVariable String sana1,@PathVariable String sana2) throws Exception{
        return new ResponseEntity(bs.getAllBySanaBetween(sana1,sana2), HttpStatus.OK);
    }
    @GetMapping(value = "/{sana1}/{sana2}/{id}")
    public ResponseEntity<?> getAllTodayPoliya(@PathVariable String sana1,@PathVariable String sana2,@PathVariable Long id) throws Exception{
        return new ResponseEntity(bs.getAllBySanaBetweenAndPoliyaId(sana1,sana2,id), HttpStatus.OK);
    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<?> getBekor(@PathVariable Long id) throws Exception{
        return new ResponseEntity(bs.getBekorQilish(id), HttpStatus.OK);
    }
    @GetMapping(value = "/people/{sana1}/{sana2}")
    public ResponseEntity<?> getAllPeople(@PathVariable String sana1,@PathVariable String sana2) throws Exception{
        return new ResponseEntity(bs.getAllBySanaBetween(sana1,sana2), HttpStatus.OK);
    }

    @GetMapping(value = "/people/{id}")
    public ResponseEntity<?> getByIdPeople(@PathVariable Long id) throws Exception{
        return  new ResponseEntity(bs.getById(id),HttpStatus.OK);
    }


}
