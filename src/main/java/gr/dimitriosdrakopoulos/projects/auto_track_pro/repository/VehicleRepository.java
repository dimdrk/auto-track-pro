package gr.dimitriosdrakopoulos.projects.auto_track_pro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle>  {
    
    Optional<Vehicle> findByVin(String vin);
    Optional< Vehicle> findByLicencePlate(String licencePlate);
}
