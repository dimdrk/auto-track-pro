package gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OwnerFilters extends GenericFilters {
// TODO
    @Nullable
    private String uuid;

    @Nullable
    private String driverLicence;

    @Nullable
    private String identityNumber;

    @Nullable
    private String username;

    @Nullable
    private String userEmail;

    @Nullable
    private Boolean active;
}
