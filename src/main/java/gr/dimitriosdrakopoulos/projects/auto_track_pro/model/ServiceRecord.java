package gr.dimitriosdrakopoulos.projects.auto_track_pro.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "service_records")
public class ServiceRecord extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private LocalDate dateOfService;

    @Column(nullable = false)
    private String serviceType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String odometer;

    @Column(nullable = false)
    private String parts;

    @Column(nullable = false)
    private String cost;

    @Column(nullable = false)
    private LocalDate nextService;

    @Column(nullable = false)
    private String recommentdations;

    @Column(nullable = false)
    private String warranty;
    
    @Getter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "vehicleServiceRecords")
    private Set<Vehicle> vehicles = new HashSet<>();
}
