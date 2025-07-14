package gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper;

import org.springframework.stereotype.Component;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Owner;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OwnerMapper {
    
    private final UserMapper userMapper;

    public OwnerReadOnlyDTO mapToOwnerReadOnlyDTO(Owner owner) {
        var dto = new OwnerReadOnlyDTO();

        
        dto.setDriverLicence(owner.getDriverLicence());
        dto.setLicenceExpiration(owner.getLicenceExpiration());
        dto.setLicenceCategory(owner.getLicenceCategory());
        dto.setIdentityNumber(owner.getIdentityNumber());
        dto.setCity(owner.getCity());
        dto.setUser(userMapper.mapToUserReadOnlyDTO(owner.getUser()));

        return dto;
    }
    
    public Owner mapToOwnerEntity(OwnerInsertDTO ownerInsertDTO) {
        Owner owner = new Owner();

        owner.setDriverLicence(ownerInsertDTO.getDriverLicence());
        owner.setLicenceExpiration(ownerInsertDTO.getLicenceExpiration());
        owner.setLicenceCategory(ownerInsertDTO.getLicenceCategory());
        owner.setIdentityNumber(ownerInsertDTO.getIdentityNumber());
        owner.setCity(ownerInsertDTO.getCity());
        owner.setUser(userMapper.mapToUserEntity(ownerInsertDTO.getUser()));

        return owner;
    }
}
