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
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.OwnerFilters;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.Paginated;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.service.OwnerSerive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OwnerRestController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRestController.class);
    private final OwnerSerive ownerSerive;
    
    @GetMapping("/owners")
    public ResponseEntity<Page<OwnerReadOnlyDTO>> getAllOwners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<OwnerReadOnlyDTO> ownersPage = ownerSerive.getPaginatedOwners(page, size);
        return new ResponseEntity<>(ownersPage, HttpStatus.OK);
    }

    @PostMapping("/owners/all")
    public ResponseEntity<List<OwnerReadOnlyDTO>> getOwners(@Nullable @RequestBody OwnerFilters filters, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {
        try {
            if (filters == null) filters = OwnerFilters.builder().build();
            return ResponseEntity.ok(ownerSerive.getOwnersFiltered(filters));
        } catch (Exception e) {
            LOGGER.error("ERROR: Could not get owners.", e);
            throw e;
        }
    }
    
    @PostMapping("/owners/all/paginated")
    public ResponseEntity<Paginated<OwnerReadOnlyDTO>> getOwnersPaginated(@Nullable @RequestBody OwnerFilters filters, Principal principal)
            throws AppObjectNotFoundException, AppObjectNotAuthorizedException {
        try {
            if (filters == null) filters = OwnerFilters.builder().build();
            return ResponseEntity.ok(ownerSerive.getOwnersFilteredPaginated(filters));
        } catch (Exception e) {
            LOGGER.error("ERROR: Could not get owners.", e);
            throw e;
        }
    }
}
