package gr.dimitriosdrakopoulos.projects.auto_track_pro.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectAlreadyExists;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectInvalidArgumentException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotFoundException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.AdminFilters;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.Paginated;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.specifications.AdminSpecification;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.AdminUpdateDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper.AdminMapper;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Admin;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;
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
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException {

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

        if (adminInsertDTO.getUser().getRole().toString() != "SUPER_ADMIN") {
            throw new AppObjectInvalidArgumentException("User", "User with role: " + adminInsertDTO.getUser().getRole() + " is valid.");
        }

        Admin admin = adminMapper.mapToAdminEntity(adminInsertDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.mapToAdminReadOnlyDTO(savedAdmin);        
    }

    // Working
    @Transactional(rollbackOn = Exception.class)
    public AdminReadOnlyDTO updateAdmin(Long id, AdminUpdateDTO adminUpdateDTO) throws AppObjectNotFoundException {
        
        if (userRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Admin", "Admin with id: " + id + " not found.");
        }
        
        Admin admin = adminRepository.findById(id).orElseThrow();
        admin.setIsActive(adminUpdateDTO.getIsActive());
        admin.setDriverLicence(adminUpdateDTO.getDriverLicence());
        admin.setLicenceExpiration(adminUpdateDTO.getLicenceExpiration());
        admin.setLicenceCategory(adminUpdateDTO.getLicenceCategory());
        admin.setIdentityNumber(adminUpdateDTO.getIdentityNumber());
        admin.setCity(adminUpdateDTO.getCity());
        
        User user = admin.getUser();
        user.setUsername(adminUpdateDTO.getUser().getUsername());
        user.setPassword(adminUpdateDTO.getUser().getPassword());
        user.setFirstname(adminUpdateDTO.getUser().getFirstname());
        user.setLastname(adminUpdateDTO.getUser().getLastname());
        user.setEmail(adminUpdateDTO.getUser().getEmail());
        user.setGender(adminUpdateDTO.getUser().getGender());
        user.setIsActive(adminUpdateDTO.getUser().getIsActive());
        admin.setUser(user);

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

    // Usefull for delete
    public AdminReadOnlyDTO getAdminById(Long id) throws AppObjectNotFoundException {

        if (userRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Admin", "Admin with id: " + id + " not found.");
        }

        Admin admin = adminRepository.findById(id).get();
        AdminReadOnlyDTO adminToReturn = adminMapper.mapToAdminReadOnlyDTO(admin);
        return adminToReturn;
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

    @org.springframework.transaction.annotation.Transactional
    public Paginated<AdminReadOnlyDTO> getAdminsFilteredPaginated(AdminFilters filters) {
        var filtered = adminRepository.findAll(getSpecsFromFilters(filters), filters.getPageable());
        return new Paginated<>(filtered.map(adminMapper::mapToAdminReadOnlyDTO));
    }

    @org.springframework.transaction.annotation.Transactional
    public List<AdminReadOnlyDTO> getAdminsFiltered(AdminFilters filters) {
        return adminRepository.findAll(getSpecsFromFilters(filters))
                .stream().map(adminMapper::mapToAdminReadOnlyDTO).toList();
    }

    private Specification<Admin> getSpecsFromFilters(AdminFilters filters) {
        return Specification
                .where(AdminSpecification.adminStringFieldLike("uuid", filters.getUuid()))
                .and(AdminSpecification.adminUsernameIs(filters.getUsername()))
                .and(AdminSpecification.adminUserEmailIs(filters.getUserEmail()))
                .and(AdminSpecification.adminUserIsActive(filters.getActive()));
    }

}
