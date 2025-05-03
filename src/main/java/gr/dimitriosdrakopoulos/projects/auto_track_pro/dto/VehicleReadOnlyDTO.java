package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import java.time.LocalDate;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Color;
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

    private String vin;
    private String licencePlate;
    private String make;
    private String model;
    private VehicleType type;
    private Color color;
    private LocalDate productionDate;
    private Fuel fuel;
    private Gearbox gearbox;
    private String odometer;
}
