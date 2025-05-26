package gr.dimitriosdrakopoulos.projects.auto_track_pro.authentication;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotAuthorizedException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AuthenticationRequestDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AuthenticationResponseDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.repository.UserRepository;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto)
            throws AppObjectNotAuthorizedException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new AppObjectNotAuthorizedException("User", "User not authorized."));

        String token = jwtService.generateToken(authentication.getName(), user.getRoleType().name());
        return new AuthenticationResponseDTO(user.getFirstname(), user.getLastname(), token);
    }
}
