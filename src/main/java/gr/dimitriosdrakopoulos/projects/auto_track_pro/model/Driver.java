package gr.dimitriosdrakopoulos.projects.auto_track_pro.model;

import java.time.LocalDate;
// import java.util.HashSet;
// import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drivers")
public class Driver extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String uuid;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(nullable = false, unique = true)
    private String driverLicence;

    @Column(nullable = false)
    private LocalDate licenceExpiration;

    @Column(nullable = false)
    private String licenceCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    // @ManyToMany
    // @JoinTable(name = "drivers_vehicles")
    // private Set<Vehicle> driverVehicles = new HashSet<>();

    // public void addVehicle(Vehicle vehicle) {
    //     if (driverVehicles == null) driverVehicles = new HashSet<>();
    //     driverVehicles.add(vehicle);
    // }

    // public  boolean hasVehicles(Vehicle vehicle) {
    //     return driverVehicles != null && !driverVehicles.isEmpty();
    // }

    @PrePersist
    public  void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }
    
}
