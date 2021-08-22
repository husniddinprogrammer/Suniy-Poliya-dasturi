package Moon.Suniypoliya.service;

import Moon.Suniypoliya.entity.User;
import Moon.Suniypoliya.service.PublicService;
import Moon.Suniypoliya.service.dto.UserDTO;
import Moon.Suniypoliya.service.vm.UserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserSer {
    public Page<UserDTO> getAll(Pageable pageable);
    public UserDTO getById(Long id) throws Exception;
    public UserDTO create(UserVM data) throws Exception;
    public UserDTO update(UserVM data) throws Exception;
    public void delete(UserVM data);
    public void deleteById(Long id);
    UserDTO getByLogin(String username);
    UserDTO getCurrentUser();
    public UserDTO getProfile();
    public UserDTO getProfileUpdate(UserVM data);
    public UserDTO getProfileUpdateCreator(User data);
}
