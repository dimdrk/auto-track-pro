package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import java.time.LocalDate;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Color;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Fuel;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Gearbox;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.VehicleType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleInsertDTO {

    @NotNull(message = "VIN must not be null.")
    private String vin;
    
    @NotNull(message = "Licence plate must not be null.")
    private String licencePlate;

    @NotEmpty(message = "Make must not be empty.")
    private String make;

    @NotEmpty(message = "Model must not be empty.")
    private String model;

    @NotNull(message = "Vehicle type must not be null.")
    private VehicleType type;

    @NotEmpty(message = "Color must not be empty.")
    private Color color;

    @NotEmpty(message = "Production date must not be empty.")
    private LocalDate productionDate;

    @NotNull(message = "Fuel type must not be null.")
    private Fuel fuel;

    @NotNull(message = "Gearbox must not be null.")
    private Gearbox gearbox;

    @NotEmpty(message = "Odometer must not be empty.")
    private String odometer;
}
