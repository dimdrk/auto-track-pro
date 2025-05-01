package gr.dimitriosdrakopoulos.projects.auto_track_pro.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Color;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Fuel;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.Gearbox;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
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
@Table(name = "vehicles")
public class Vehicle extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String VIN;

    @Column(unique=true)
    private String licencePlate;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(nullable = false)
    private LocalDate productionDate;

    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    @Column(nullable = false)
    private String odometer;

    @Getter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "ownerVehicles")
    private Set<Owner> owners = new HashSet<>();
    
    @Getter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "driverVehicles")
    private Set<Driver> drivers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "vehicles_service_records")
    private Set<ServiceRecord> vehicleServiceRecords = new HashSet<>();

    public void addServiceRecord(ServiceRecord serviceRecord) {
        if (vehicleServiceRecords == null) vehicleServiceRecords = new HashSet<>();
        vehicleServiceRecords.add(serviceRecord);
    }

    public  boolean hasServiceRecords(ServiceRecord serviceRecord) {
        return vehicleServiceRecords != null && !vehicleServiceRecords.isEmpty();
    }
}
