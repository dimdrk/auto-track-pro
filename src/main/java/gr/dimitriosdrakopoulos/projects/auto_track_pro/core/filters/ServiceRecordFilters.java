package gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters;

import java.time.LocalDate;


import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceRecordFilters extends GenericFilters {

    @Nullable
    private String id;

    @Nullable
    private LocalDate dateOfService;

    @Nullable
    private String licencePlate;
    
}
