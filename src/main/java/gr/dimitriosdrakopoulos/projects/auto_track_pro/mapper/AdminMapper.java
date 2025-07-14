package gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper;

import org.springframework.stereotype.Component;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Admin;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    private final UserMapper userMapper;

    public AdminReadOnlyDTO mapToAdminReadOnlyDTO(Admin admin) {
        var dto = new AdminReadOnlyDTO();

        dto.setIsActive(admin.getIsActive());
        dto.setDriverLicence(admin.getDriverLicence());
        dto.setLicenceExpiration(admin.getLicenceExpiration());
        dto.setLicenceCategory(admin.getLicenceCategory());
        dto.setIdentityNumber(admin.getIdentityNumber());
        dto.setCity(admin.getCity());
        dto.setUser(userMapper.mapToUserReadOnlyDTO(admin.getUser()));

        return dto;
    }

    public Admin mapToAdminEntity(AdminInsertDTO adminInsertDTO) {
        Admin admin = new Admin();

        admin.setIsActive(adminInsertDTO.getIsActive());
        admin.setDriverLicence(adminInsertDTO.getDriverLicence());
        admin.setLicenceExpiration(adminInsertDTO.getLicenceExpiration());
        admin.setLicenceCategory(adminInsertDTO.getLicenceCategory());
        admin.setIdentityNumber(adminInsertDTO.getIdentityNumber());
        admin.setCity(adminInsertDTO.getCity());
        admin.setUser(userMapper.mapToUserEntity(adminInsertDTO.getUser()));

        return admin;
    }    
}
