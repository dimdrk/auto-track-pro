package gr.dimitriosdrakopoulos.projects.auto_track_pro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {
    
    Optional<Admin> findByUserId(Long userId);
    Optional<Admin> findByUuid(String uuid);
    Optional<Admin> findByDriverLicence(String driverLicence);
    Optional<Admin> findByIdentityNumber(String identityNumber);
}
