package gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters;

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
public class VehicleFilters extends GenericFilters {

    @Nullable
    private String id;

    @Nullable
    private String vin;

    @Nullable
    private String licencePlate;

    @Nullable
    private String username;
    
}
