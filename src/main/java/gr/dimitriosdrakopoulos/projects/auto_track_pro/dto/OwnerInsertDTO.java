package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OwnerInsertDTO {
    
    @NotNull(message = "Driver's licence must not be null.")
    private String driverLicence;

    @NotNull(message = "Licence expiration must not be null.")
    private LocalDate licenceExpiration;

    @NotNull(message = "Licence category must not be null.")
    private String licenceCategory;

    @NotNull(message = "Identity number must not be null.")
    private String identityNumber;
    
    @NotNull(message = "City must not be null.")
    private String city;

    @NotNull(message = "User details must not be null.")
    private UserInsertDTO user;

    @Nullable
    private List<VehicleInsertDTO> vehicles;
}
