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
public class ServiceRecordReadOnlyDTO {
    private LocalDate dateOfService;
    private String serviceType;
    private String description;
    private String parts;
    private String cost;
    private LocalDate nextService;
    private String recommentdations;
    private String warranty;
    private String odometer;    
}
