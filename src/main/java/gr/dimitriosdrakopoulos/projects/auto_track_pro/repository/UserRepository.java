package gr.dimitriosdrakopoulos.projects.auto_track_pro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
