package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {
    private Boolean isActive;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
}
