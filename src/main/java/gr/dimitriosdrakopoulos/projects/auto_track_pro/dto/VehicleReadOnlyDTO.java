package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import java.time.LocalDate;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Fuel;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Gearbox;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleReadOnlyDTO {
    private String make;
    private String model;
    private LocalDate productionDate;
    private Fuel fuelType;
    private Gearbox gearbox;
    private String odometer;
    private VehicleType type;
    private String VIN;
    private String color;
}
