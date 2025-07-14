package gr.dimitriosdrakopoulos.projects.auto_track_pro.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectAlreadyExists;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectInvalidArgumentException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotFoundException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.OwnerFilters;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.Paginated;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.specifications.OwnerSpecification;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper.OwnerMapper;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Owner;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.repository.OwnerRepository;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerSerive {
    
    private final OwnerMapper ownerMapper;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public OwnerReadOnlyDTO saveOwner(OwnerInsertDTO ownerInsertDTO) throws AppObjectAlreadyExists, AppObjectInvalidArgumentException {

        if (userRepository.findByUsername(ownerInsertDTO.getUser().getUsername()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with username: " + ownerInsertDTO.getUser().getUsername() + " already exist.");
        }

        if (userRepository.findByEmail(ownerInsertDTO.getUser().getEmail()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with email: " + ownerInsertDTO.getUser().getEmail() + " already exist.");
        }

        if (ownerRepository.findByDriverLicence(ownerInsertDTO.getDriverLicence()).isPresent()) {
            throw new AppObjectAlreadyExists("Owner", "Owner with driver licence: " + ownerInsertDTO.getDriverLicence() + " already exist.");
        }

        if (ownerRepository.findByIdentityNumber(ownerInsertDTO.getIdentityNumber()).isPresent()) {
            throw new AppObjectAlreadyExists("Owner", "Owner with identity number: " + ownerInsertDTO.getIdentityNumber() + " already exist.");
        }

        if (ownerInsertDTO.getUser().getRole().toString() != "OWNER") {
            throw new AppObjectInvalidArgumentException("User", "User with role: " + ownerInsertDTO.getUser().getRole() + " is valid.");
        }

        Owner owner = ownerMapper.mapToOwnerEntity(ownerInsertDTO);
        Owner savedOwner = ownerRepository.save(owner);
        return ownerMapper.mapToOwnerReadOnlyDTO(savedOwner);
    }

    // ToDo
    @Transactional(rollbackOn = Exception.class)
    public OwnerReadOnlyDTO updateOwner(Long id, OwnerInsertDTO ownerUpdateDTO) throws AppObjectNotFoundException {
        
        if (userRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Owner", "Owner with id: " + id + " not found.");
        }
        
        Owner owner = ownerRepository.findById(id).orElseThrow();
        owner.setDriverLicence(ownerUpdateDTO.getDriverLicence());
        owner.setLicenceExpiration(ownerUpdateDTO.getLicenceExpiration());
        owner.setLicenceCategory(ownerUpdateDTO.getLicenceCategory());
        owner.setIdentityNumber(ownerUpdateDTO.getIdentityNumber());
        owner.setCity(ownerUpdateDTO.getCity());

        User user = owner.getUser();
        user.setUsername(ownerUpdateDTO.getUser().getUsername());
        user.setPassword(ownerUpdateDTO.getUser().getPassword());
        user.setFirstname(ownerUpdateDTO.getUser().getFirstname());
        user.setLastname(ownerUpdateDTO.getUser().getLastname());
        user.setEmail(ownerUpdateDTO.getUser().getEmail());
        user.setGender(ownerUpdateDTO.getUser().getGender());
        user.setIsActive(ownerUpdateDTO.getUser().getIsActive());
        owner.setUser(user);

        Owner updatedOwner = ownerRepository.save(owner);
        return ownerMapper.mapToOwnerReadOnlyDTO(updatedOwner);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteOwner(Long id) throws AppObjectNotFoundException {

        if (ownerRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Owner", "Owner with id: " + id + " not found.");
        }

        ownerRepository.deleteById(id);
    }

    // Usefull for delete
    public OwnerReadOnlyDTO getOwnerById(Long id) throws AppObjectNotFoundException {

        if (userRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Owner", "Owner with id: " + id + " not found.");
        }

        Owner owner = ownerRepository.findById(id).get();
        OwnerReadOnlyDTO ownerToReturn = ownerMapper.mapToOwnerReadOnlyDTO(owner);
        return ownerToReturn;
    }

    public Page<OwnerReadOnlyDTO> getPaginatedOwners(int page, int size) {

        String defaultSort = "id";
        Pageable pageable = PageRequest.of(page, size, Sort.by(defaultSort).ascending());

        return ownerRepository.findAll(pageable).map(ownerMapper::mapToOwnerReadOnlyDTO);
    }

    public Page<OwnerReadOnlyDTO> getPaginatedSortedOwners(int page, int size, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return ownerRepository.findAll(pageable).map(ownerMapper::mapToOwnerReadOnlyDTO);
    }

    @org.springframework.transaction.annotation.Transactional
    public Paginated<OwnerReadOnlyDTO> getOwnersFilteredPaginated(OwnerFilters filters) {
        var filtered = ownerRepository.findAll(getSpecsFromFilters(filters), filters.getPageable());
        return new Paginated<>(filtered.map(ownerMapper::mapToOwnerReadOnlyDTO));
    }

    @org.springframework.transaction.annotation.Transactional
    public List<OwnerReadOnlyDTO> getOwnersFiltered(OwnerFilters filters) {
        return ownerRepository.findAll(getSpecsFromFilters(filters))
                .stream().map(ownerMapper::mapToOwnerReadOnlyDTO).toList();
    }

    private Specification<Owner> getSpecsFromFilters(OwnerFilters filters) {
        return Specification
                .where(OwnerSpecification.ownerStringFieldLike("uuid", filters.getUuid()))
                .and(OwnerSpecification.ownerUsernameIs(filters.getUsername()))
                .and(OwnerSpecification.ownerUserEmailIs(filters.getUserEmail()))
                .and(OwnerSpecification.ownerUserIsActive(filters.getActive()));
    }

}
