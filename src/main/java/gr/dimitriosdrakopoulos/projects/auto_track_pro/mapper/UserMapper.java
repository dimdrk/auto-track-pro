package gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper;

import org.springframework.stereotype.Component;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.UserInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.UserReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.UserUpdateDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    
    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        var dto = new UserReadOnlyDTO();

        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setIsActive(user.getIsActive());

        return dto;
    }

    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        User user = new User();

        user.setUsername(userInsertDTO.getUsername());
        user.setPassword(userInsertDTO.getPassword());
        user.setFirstname(userInsertDTO.getFirstname());
        user.setLastname(userInsertDTO.getLastname());
        user.setEmail(userInsertDTO.getEmail());
        user.setGender(userInsertDTO.getGender());
        user.setRole(userInsertDTO.getRole());
        user.setIsActive(userInsertDTO.getIsActive());

        return user;
    }

    public User mapToUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        User user = new User();

        user.setUsername(userUpdateDTO.getUsername());
        user.setPassword(userUpdateDTO.getPassword());
        user.setFirstname(userUpdateDTO.getFirstname());
        user.setLastname(userUpdateDTO.getLastname());
        user.setEmail(userUpdateDTO.getEmail());
        user.setGender(userUpdateDTO.getGender());
        user.setIsActive(userUpdateDTO.getIsActive());
        
        return user;
    }
}
