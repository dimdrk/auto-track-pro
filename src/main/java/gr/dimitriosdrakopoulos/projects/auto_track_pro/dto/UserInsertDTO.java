package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Gender;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @NotNull(message = "Is active must not be null.")
    private Boolean isActive;

    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).{5,}$", message = "Invalid username.")
    @NotEmpty(message = "Username must not be empty.")
    private String username;

    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[@#$%!^&*]).{8,}$", message = "Invalid password.")
    @NotEmpty(message = "Password must not be empty.")
    private String password;

    @NotEmpty(message = "Firstname must not be empty.")
    private String firstname;

    @NotEmpty(message = "Lastname must not be empty.")
    private String lastname;

    @Email(message = "Invalid email.")
    private String email;

    @NotNull(message = "Gender must not be null.")
    private Gender gender;

    @NotNull(message = "Role must not be null.")
    private RoleType role;
}
