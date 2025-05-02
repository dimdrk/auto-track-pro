package gr.dimitriosdrakopoulos.projects.auto_track_pro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver>  {
    
    Optional<Driver> findByDriverLicence(String driverLicence);
}
