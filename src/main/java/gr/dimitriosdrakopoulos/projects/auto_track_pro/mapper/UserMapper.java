package gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.UserInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.UserReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    
    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        var dto = new UserReadOnlyDTO();

        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setPhonenumber(user.getPhonenumber());
        dto.setIsActive(user.getIsActive());

        return dto;
    }

    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        User user = new User();

        user.setUsername(userInsertDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));
        user.setFirstname(userInsertDTO.getFirstname());
        user.setLastname(userInsertDTO.getLastname());
        user.setEmail(userInsertDTO.getEmail());
        user.setPhonenumber(userInsertDTO.getPhonenumber());
        user.setGender(userInsertDTO.getGender());
        user.setRoleType(userInsertDTO.getRole());
        user.setIsActive(userInsertDTO.getIsActive());

        return user;
    }
}
