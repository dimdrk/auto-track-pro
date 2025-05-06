package gr.dimitriosdrakopoulos.projects.auto_track_pro.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectAlreadyExists;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.exceptions.AppObjectNotFoundException;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.DriverFilters;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.filters.Paginated;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.specifications.DriverSpecification;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.DriverUpdateDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper.DriverMapper;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Driver;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.repository.DriverRepository;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverMapper driverMapper;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    public DriverReadOnlyDTO saveDriver(DriverInsertDTO driverInsertDTO) throws AppObjectAlreadyExists {

        if (userRepository.findByUsername(driverInsertDTO.getUser().getUsername()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with username: " + driverInsertDTO.getUser().getUsername() + " already exist.");
        }

        if (userRepository.findByEmail(driverInsertDTO.getUser().getEmail()).isPresent()) {
            throw new AppObjectAlreadyExists("User", "User with email: " + driverInsertDTO.getUser().getEmail() + " already exist.");
        }

        if (driverRepository.findByDriverLicence(driverInsertDTO.getDriverLicence()).isPresent()) {
            throw new AppObjectAlreadyExists("Driver", "Driver with driver licence: " + driverInsertDTO.getDriverLicence() + " already exist.");
        }

        Driver driver = driverMapper.mapToDriverEntity(driverInsertDTO);

        Driver savedDriver = driverRepository.save(driver);

        return driverMapper.mapToDriverReadOnlyDTO(savedDriver);
    }

    @Transactional(rollbackOn = Exception.class)
    public DriverReadOnlyDTO updateDriver(Long id, DriverUpdateDTO driverUpdateDTO) throws AppObjectNotFoundException {
        
        if (userRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Driver", "Driver with id: " + id + " not found.");
        }
        
        Driver driver = driverMapper.mapToDriverUpdateDTO(driverUpdateDTO);

        Driver updatedDriver = driverRepository.save(driver);

        return driverMapper.mapToDriverReadOnlyDTO(updatedDriver);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteDriver(Long id) throws AppObjectNotFoundException {

        if (driverRepository.findById(id).isEmpty()) {
            throw new AppObjectNotFoundException("Driver", "Driver with id: " + id + " not found.");
        }

        driverRepository.deleteById(id);
    }

    public Page<DriverReadOnlyDTO> getPaginatedDrivers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Driver> driverPage = driverRepository.findAll(pageable);

        return driverPage.map(driverMapper::mapToDriverReadOnlyDTO);
    }
    
    public Page<DriverReadOnlyDTO> getPaginatedSortedDrivers(int page, int size, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return driverRepository.findAll(pageable).map(driverMapper::mapToDriverReadOnlyDTO);
    }

    @org.springframework.transaction.annotation.Transactional
    public Paginated<DriverReadOnlyDTO> getDriversFilteredPaginated(DriverFilters filters) {
        var filtered = driverRepository.findAll(getSpecsFromFilters(filters), filters.getPageable());
        return new Paginated<>(filtered.map(driverMapper::mapToDriverReadOnlyDTO));
    }

    @org.springframework.transaction.annotation.Transactional
    public List<DriverReadOnlyDTO> getDriversFiltered(DriverFilters filters) {
        return driverRepository.findAll(getSpecsFromFilters(filters))
                .stream().map(driverMapper::mapToDriverReadOnlyDTO).toList();
    }

    private Specification<Driver> getSpecsFromFilters(DriverFilters filters) {
        return Specification
                .where(DriverSpecification.driverStringFieldLike("uuid", filters.getUuid()))
                .and(DriverSpecification.driverUsernameIs(filters.getUsername()))
                .and(DriverSpecification.driverUserEmailIs(filters.getUserEmail()))
                .and(DriverSpecification.driverUserIsActive(filters.getActive()));
    }

}
