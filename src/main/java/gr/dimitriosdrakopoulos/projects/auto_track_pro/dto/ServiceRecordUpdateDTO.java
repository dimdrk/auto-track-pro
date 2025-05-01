package gr.dimitriosdrakopoulos.projects.auto_track_pro.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceRecordUpdateDTO {

    @NotNull(message = "Date of Service must not be null.")
    private LocalDate dateOfService;

    @NotNull(message = "Service type must not be null.")
    private String serviceType;

    @NotNull(message = "Description must not be null.")
    private String description;

    @NotNull(message = "Parts must not be null.")
    private String parts;

    @NotNull(message = "Cost must not be null.")
    private String cost;

    @NotNull(message = "Next serice must not be null.")
    private LocalDate nextService;

    @NotNull(message = "Recommentdations must not be null.")
    private String recommentdations;

    @NotNull(message = "Warranty must not be null.")
    private String warranty;

    @NotNull(message = "Odometer must not be null.")
    private String odometer;    
    
}
