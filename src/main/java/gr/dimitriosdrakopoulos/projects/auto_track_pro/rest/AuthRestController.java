package gr.dimitriosdrakopoulos.projects.auto_track_pro.rest;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.authentication.AuthenticationService;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotAuthorizedException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AuthenticationRequestDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestController.class);
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO)
            throws AppObjectNotAuthorizedException {
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticate(authenticationRequestDTO);
        LOGGER.info("User authenticated.");
        return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
    }
}
