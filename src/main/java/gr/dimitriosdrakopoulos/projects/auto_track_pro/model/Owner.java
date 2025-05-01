package gr.dimitriosdrakopoulos.projects.auto_track_pro.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "owners")
public class Owner extends AbstractEntity {

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

    @Column(nullable = false, unique = true)
    private String identityNumber;

    @Column(nullable = false)
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public  void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }
    
}
