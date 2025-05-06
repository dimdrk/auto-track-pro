package gr.dimitriosdrakopoulos.projects.auto_track_pro.rest;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotAuthorizedException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotFoundException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.DriverFilters;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.Paginated;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.service.DriverService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DriverRestController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRestController.class);
    private final DriverService driverService;

    
    @GetMapping("/admins")
    public ResponseEntity<Page<DriverReadOnlyDTO>> getAllDrivers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<DriverReadOnlyDTO> driversPage = driverService.getPaginatedDrivers(page, size);
        return new ResponseEntity<>(driversPage, HttpStatus.OK);
    }

    @PostMapping("/admins/all")
    public ResponseEntity<List<DriverReadOnlyDTO>> getDrivers(@Nullable @RequestBody DriverFilters filters, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {
        try {
            if (filters == null) filters = DriverFilters.builder().build();
            return ResponseEntity.ok(driverService.getDriversFiltered(filters));
        } catch (Exception e) {
            LOGGER.error("ERROR: Could not get drivers.", e);
            throw e;
        }
    }
    
    @PostMapping("/admins/all/paginated")
    public ResponseEntity<Paginated<DriverReadOnlyDTO>> getAdminsPaginated(@Nullable @RequestBody DriverFilters filters, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {
        try {
            if (filters == null) filters = DriverFilters.builder().build();
            return ResponseEntity.ok(driverService.getDriversFilteredPaginated(filters));
        } catch (Exception e) {
            LOGGER.error("ERROR: Could not get drivers.", e);
            throw e;
        }
    }
}
