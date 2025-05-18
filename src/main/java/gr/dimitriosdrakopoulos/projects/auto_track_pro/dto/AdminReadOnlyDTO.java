package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminReadOnlyDTO {
    private Boolean isActive;
    private String driverLicence;
    private LocalDate licenceExpiration;
    private String licenceCategory;
    private String identityNumber;
    private String city;
    private UserReadOnlyDTO user;
}
