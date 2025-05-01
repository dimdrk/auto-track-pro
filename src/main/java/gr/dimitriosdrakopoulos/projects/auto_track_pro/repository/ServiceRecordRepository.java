package gr.dimitriosdrakopoulos.projects.auto_track_pro.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.ServiceRecord;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long>, JpaSpecificationExecutor<ServiceRecord>  {
    
    Optional<ServiceRecord> findByDateOfService(LocalDate dateOfService);
}
