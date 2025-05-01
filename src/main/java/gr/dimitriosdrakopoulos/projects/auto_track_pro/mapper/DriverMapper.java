package gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper;

import org.springframework.stereotype.Component;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverUpdateDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Driver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DriverMapper {
    
    private final UserMapper userMapper;

    public DriverReadOnlyDTO mapToDriverReadOnlyDTO(Driver driver) {
        var dto = new DriverReadOnlyDTO();

        dto.setDriverLicence(driver.getDriverLicence());
        dto.setLicenceCategory(driver.getLicenceCategory());
        dto.setLicenceExpiration(driver.getLicenceExpiration());
        dto.setUser(userMapper.mapToUserReadOnlyDTO(driver.getUser()));

        return dto;
    }

    public Driver mapToDriverEntity(DriverInsertDTO driverInsertDTO) {
        Driver driver = new Driver();

        driver.setDriverLicence(driverInsertDTO.getDriverLicence());
        driver.setLicenceCategory(driverInsertDTO.getLicenceCategory());
        driver.setLicenceExpiration(driverInsertDTO.getLicenceExpiration());
        driver.setUser(userMapper.mapToUserEntity(driverInsertDTO.getUser()));

        return driver;
    }

    public Driver mapToDriverUpdateDTO(DriverUpdateDTO driverUpdateDTO) {
        Driver driver = new Driver();

        driver.setLicenceCategory(driverUpdateDTO.getLicenceCategory());
        driver.setLicenceExpiration(driverUpdateDTO.getLicenceExpiration());
        driver.setUser(userMapper.mapToUserUpdateDTO(driverUpdateDTO.getUser()));

        return driver;
    }
}
