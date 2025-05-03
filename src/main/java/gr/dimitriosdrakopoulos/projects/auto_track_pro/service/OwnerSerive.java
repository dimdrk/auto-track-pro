package gr.dimitriosdrakopoulos.projects.auto_track_pro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectAlreadyExists;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotFoundException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.OwnerUpdateDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper.OwnerMapper;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Owner;
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

    public OwnerReadOnlyDTO saveOwner(OwnerInsertDTO ownerInsertDTO) throws AppObjectAlreadyExists {

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

        Owner owner = ownerMapper.mapToOwnerEntity(ownerInsertDTO);
        Owner savedOwner = ownerRepository.save(owner);
        return ownerMapper.mapToOwnerReadOnlyDTO(savedOwner);
    }

    @Transactional(rollbackOn = Exception.class)
    public OwnerReadOnlyDTO updateOwner(Long id, OwnerUpdateDTO ownerUpdateDTO) throws AppObjectNotFoundException {
        
        if (userRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Owner", "Owner with id: " + id + " not found.");
        }
        
        Owner owner = ownerMapper.mapToOwnerUpdateDTO(ownerUpdateDTO);
        Owner updatedOwner = ownerRepository.save(owner);
        return ownerMapper.mapToOwnerReadOnlyDTO(updatedOwner);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteAdmin(Long id) throws AppObjectNotFoundException {

        if (ownerRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Owner", "Owner with id: " + id + " not found.");
        }

        ownerRepository.deleteById(id);
    }

    public Page<OwnerReadOnlyDTO> getPaginatedAdmins(int page, int size) {

        String defaultSort = "id";
        Pageable pageable = PageRequest.of(page, size, Sort.by(defaultSort).ascending());

        return ownerRepository.findAll(pageable).map(ownerMapper::mapToOwnerReadOnlyDTO);
    }

    public Page<OwnerReadOnlyDTO> getPaginatedSortedAdmins(int page, int size, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return ownerRepository.findAll(pageable).map(ownerMapper::mapToOwnerReadOnlyDTO);
    }

}
