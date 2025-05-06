package gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DriverFilters extends GenericFilters {

    @Nullable
    private String uuid;

    @Nullable
    private String driverLicence;

    @Nullable
    private String username;

    @Nullable
    private String userEmail;

    @Nullable
    private Boolean active;
}
