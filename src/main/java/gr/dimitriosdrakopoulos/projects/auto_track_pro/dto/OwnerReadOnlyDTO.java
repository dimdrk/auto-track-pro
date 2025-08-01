package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OwnerReadOnlyDTO {
    private String driverLicence;
    private LocalDate licenceExpiration;
    private String licenceCategory;
    private String identityNumber;
    private String city;
    private UserReadOnlyDTO user;
    private List<VehicleReadOnlyDTO> vehicles;
}
