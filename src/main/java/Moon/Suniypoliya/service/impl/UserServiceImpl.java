package Moon.Suniypoliya.service.impl;


import Moon.Suniypoliya.entity.Lavozim;
import Moon.Suniypoliya.entity.User;
import Moon.Suniypoliya.repository.UserRepository;
import Moon.Suniypoliya.security.SecurityUtil;
import Moon.Suniypoliya.service.UserSer;
import Moon.Suniypoliya.service.dto.UserDTO;
import Moon.Suniypoliya.service.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserSer {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        Page<User> userlar =  userRepository.findAll(pageable);

        return  userlar.map(UserDTO::new);
    }

    @Override
    public UserDTO getById(Long id) throws Exception {
        Optional<User> user =  userRepository.findById(id);

        if(user.isPresent()){
            User u =  user.get();

            UserDTO userDTO = new UserDTO(u);

            return userDTO;
        }
        throw new Exception("User topilmadi");
    }

    @Override
    public UserDTO create(UserVM data) throws Exception {

        UserDTO currentUser = getCurrentUser();

        User user = new User();
        user.setName(data.getName());
        user.setNumber(data.getNumber());
        user.setAktiv(1l);
        user.setQushilganVaqti(data.getQushilganVaqti());
        user.setUsername(data.getUsername());
        user.setLavozimlar(data.getLavozimlar());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setUsername(data.getUsername());
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserVM data) throws Exception {

        if (data.getId() != null){
            UserDTO currentUser = getCurrentUser();

            User user = new User();
            user.setId(data.getId());

            user.setName(data.getName());
            user.setNumber(data.getNumber());
            user.setAktiv(data.getAktiv());
            user.setQushilganVaqti(data.getQushilganVaqti());
            user.setUsername(data.getUsername());
            data.getLavozimlar().remove(Lavozim.ADMIN);


            user.setLavozimlar(data.getLavozimlar());

            user.setPassword(passwordEncoder.encode(data.getPassword()));
            user.setUsername(data.getUsername());
            return new UserDTO(userRepository.save(user));
        }else{
            throw new Exception("id null bo'lishi kerak");
        }
    }

    @Override
    public void delete(UserVM data) {
        deleteById(data.getId());
    }



    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getByLogin(String username) {
        return userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
    }

    @Override
    public UserDTO getCurrentUser(){
        String username = getPrincipal();
        if (username != null)
            return userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
        return null;
    }

    @Override
    public UserDTO getProfile(){
        String username = SecurityUtil.getCurrentUserLogin();
        Optional<User> user =userRepository.findByUsername(username);
        UserDTO user1=userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
        return user1;
    }

    @Override
    public UserDTO getProfileUpdate(UserVM data){
        String username = SecurityUtil.getCurrentUserLogin();
        Optional<User> user =userRepository.findByUsername(username);
        User u = new User();
        u.setName(data.getName());
        u.setPassword(passwordEncoder.encode(data.getPassword()));
        u.setNumber(data.getNumber());
        u.setAktiv(user.get().getAktiv());
        u.setId(user.get().getId());
        u.setUsername(user.get().getUsername());
        u.setQushilganVaqti(user.get().getQushilganVaqti());
        u.setLavozimlar(user.get().getLavozimlar());
        userRepository.save(u);
        UserDTO user1=userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
        return user1;
    }

    @Override
    public UserDTO getProfileUpdateCreator(User data) {
        userRepository.save(data);
        return null;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}