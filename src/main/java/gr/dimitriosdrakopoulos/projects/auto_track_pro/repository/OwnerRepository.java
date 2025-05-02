package gr.dimitriosdrakopoulos.projects.auto_track_pro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long>, JpaSpecificationExecutor<Owner> {
    
    Optional<Owner> findByDriverLicence(String driverLicence);
    Optional<Owner> findByIdentityNumber(String identityNumber);
    
}
