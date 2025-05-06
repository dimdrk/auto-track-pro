package gr.dimitriosdrakopoulos.projects.auto_track_pro.rest;



import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.*;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.Paginated;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.AdminFilters;
// import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.service.AdminService;
// import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRestController.class);
    private final AdminService adminService;

    // TODO
    @GetMapping("/admins")
    public ResponseEntity<Page<AdminReadOnlyDTO>> getAllAdmins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<AdminReadOnlyDTO> adminsPage = adminService.getPaginatedAdmins(page, size);
        return new ResponseEntity<>(adminsPage, HttpStatus.OK);
    }

    // @PostMapping("/admins/save")
    // public ResponseEntity<AdminReadOnlyDTO> saveAdmin(
    //         @Valid @RequestPart(name = "admin") AdminInsertDTO adminInsertDTO) throws AppObjectInvalidArgumentException, AppObjectAlreadyExists, AppServerException {

    //     AdminReadOnlyDTO adminReadOnlyDTO = adminService.saveAdmin(adminInsertDTO);
    //     return new ResponseEntity<>(adminReadOnlyDTO, HttpStatus.OK);
    // }

    @PostMapping("/admins/all")
    public ResponseEntity<List<AdminReadOnlyDTO>> getAdmins(@Nullable @RequestBody AdminFilters filters, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {
        try {
            if (filters == null) filters = AdminFilters.builder().build();
            return ResponseEntity.ok(adminService.getAdminsFiltered(filters));
        } catch (Exception e) {
            LOGGER.error("ERROR: Could not get admins.", e);
            throw e;
        }
    }
    
    @PostMapping("/admins/all/paginated")
    public ResponseEntity<Paginated<AdminReadOnlyDTO>> getAdminsPaginated(@Nullable @RequestBody AdminFilters filters, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {
        try {
            if (filters == null) filters = AdminFilters.builder().build();
            return ResponseEntity.ok(adminService.getAdminsFilteredPaginated(filters));
        } catch (Exception e) {
            LOGGER.error("ERROR: Could not get admins.", e);
            throw e;
        }
    }
}
