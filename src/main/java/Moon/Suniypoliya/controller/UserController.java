package Moon.Suniypoliya.controller;


import Moon.Suniypoliya.entity.User;
import Moon.Suniypoliya.error.ApiError;
import Moon.Suniypoliya.service.UserSer;
import Moon.Suniypoliya.service.impl.UserServiceImpl;
import Moon.Suniypoliya.service.dto.UserDTO;
import Moon.Suniypoliya.service.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {
    @Autowired
    private UserSer ts;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(ts.getAll(pageable).getContent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(ts.getById(id));
        } catch (Exception e) {
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(){
        try {
            return ResponseEntity.ok(ts.getProfile());
        } catch (Exception e) {
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserVM u) {
        u.setQushilganVaqti(LocalDateTime.now());
        try {
            return new ResponseEntity<>(ts.create(u), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), ""));
        }
    }

    @PostMapping("/profile/parol")
    public ResponseEntity<?> updates(@RequestBody UserVM u) {
        try {
            return new ResponseEntity<>(ts.getProfileUpdate(u), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), ""));
        }
    }
    @PostMapping("/profile/creator")
    public ResponseEntity<?> updateCreator(@RequestBody User u) {
        try {
            return new ResponseEntity<>(ts.getProfileUpdateCreator(u), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), ""));
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody UserVM User) throws Exception {
        return new ResponseEntity<>(ts.update(User), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ts.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}