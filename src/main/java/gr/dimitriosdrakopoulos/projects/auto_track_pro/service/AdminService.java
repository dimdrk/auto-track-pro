package gr.dimitriosdrakopoulos.projects.auto_track_pro.service;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectAlreadyExists;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotFoundException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminUpdateDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper.AdminMapper;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Admin;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.repository.AdminRepository;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    
    @Transactional(rollbackOn = Exception.class)
    public AdminReadOnlyDTO saveAdmin(AdminInsertDTO adminInsertDTO) 
            throws AppObjectAlreadyExists {

        if (userRepository.findByUsername(adminInsertDTO.getUser().getUsername()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with username: " + adminInsertDTO.getUser().getUsername() + " already exist.");
        }

        if (userRepository.findByEmail(adminInsertDTO.getUser().getEmail()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with email: " + adminInsertDTO.getUser().getEmail() + " already exist.");
        }

        if (adminRepository.findByDriverLicence(adminInsertDTO.getDriverLicence()).isPresent()) {
            throw new AppObjectAlreadyExists("Admin", "Admin with driver licence: " + adminInsertDTO.getDriverLicence() + " already exist.");
        }

        if (adminRepository.findByIdentityNumber(adminInsertDTO.getIdentityNumber()).isPresent()) {
            throw new AppObjectAlreadyExists("Admin", "Admin with identity number: " + adminInsertDTO.getIdentityNumber() + " already exist.");
        }

        Admin admin = adminMapper.mapToAdminEntity(adminInsertDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.mapToAdminReadOnlyDTO(savedAdmin);        
    }

    @Transactional(rollbackOn = Exception.class)
    public AdminReadOnlyDTO updateAdmin(Long id, AdminUpdateDTO adminUpdateDTO) throws AppObjectNotFoundException {
        
        if (userRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Admin", "Admin with id: " + id + " not found.");
        }
        
        Admin admin = adminMapper.mapToAdminUpdateDTO(adminUpdateDTO);
        Admin updatedAdmin = adminRepository.save(admin);
        return adminMapper.mapToAdminReadOnlyDTO(updatedAdmin);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteAdmin(Long id) throws AppObjectNotFoundException {

        if (adminRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Admin", "Admin with id: " + id + " not found.");
        }

        adminRepository.deleteById(id);
    }

    public Page<AdminReadOnlyDTO> getPaginatedAdmins(int page, int size) {

        String defaultSort = "id";
        Pageable pageable = PageRequest.of(page, size, Sort.by(defaultSort).ascending());

        return adminRepository.findAll(pageable).map(adminMapper::mapToAdminReadOnlyDTO);
    }

    public Page<AdminReadOnlyDTO> getPaginatedSortedAdmins(int page, int size, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return adminRepository.findAll(pageable).map(adminMapper::mapToAdminReadOnlyDTO);
    }

}
