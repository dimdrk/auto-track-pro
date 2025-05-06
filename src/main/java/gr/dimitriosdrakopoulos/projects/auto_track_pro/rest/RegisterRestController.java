package gr.dimitriosdrakopoulos.projects.auto_track_pro.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.service.AdminService;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.service.DriverService;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.service.OwnerSerive;
import jakarta.validation.Valid;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectAlreadyExists;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectInvalidArgumentException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppServerException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerReadOnlyDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterRestController {

    
    private final AdminService adminService;
    private final OwnerSerive ownerSerive;
    private final DriverService driverService;
    
    @PostMapping("/admins/save")
    public ResponseEntity<AdminReadOnlyDTO> saveAdmin(
            @Valid @RequestPart(name = "admin") AdminInsertDTO adminInsertDTO) throws AppObjectInvalidArgumentException, AppObjectAlreadyExists, AppServerException {

        AdminReadOnlyDTO adminReadOnlyDTO = adminService.saveAdmin(adminInsertDTO);
        return new ResponseEntity<>(adminReadOnlyDTO, HttpStatus.OK);
    }

    @PostMapping("owners/save")
    public ResponseEntity<OwnerReadOnlyDTO> saveOwner(
            @Valid @RequestPart(name = "owner") OwnerInsertDTO ownerInsertDTO) throws AppObjectInvalidArgumentException, AppObjectAlreadyExists, AppServerException {

        OwnerReadOnlyDTO ownerReadOnlyDTO = ownerSerive.saveOwner(ownerInsertDTO);
        return new ResponseEntity<>(ownerReadOnlyDTO, HttpStatus.OK);
    }

    @PostMapping("drivers/save")
    public ResponseEntity<DriverReadOnlyDTO> saveDriver(
            @Valid @RequestPart(name = "driver") DriverInsertDTO driverrInsertDTO) throws AppObjectInvalidArgumentException, AppObjectAlreadyExists, AppServerException {

        DriverReadOnlyDTO driverReadOnlyDTO = driverService.saveDriver(driverrInsertDTO);
        return new ResponseEntity<>(driverReadOnlyDTO, HttpStatus.OK);
    }
}
