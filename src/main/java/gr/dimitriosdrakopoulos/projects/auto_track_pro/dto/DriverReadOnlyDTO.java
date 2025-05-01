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
public class DriverReadOnlyDTO {
    private String driverLicence;
    private LocalDate licenceExpiration;
    private String licenceCategory;
    private UserReadOnlyDTO user;
}
