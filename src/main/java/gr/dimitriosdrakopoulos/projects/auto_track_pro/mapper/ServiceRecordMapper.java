package gr.dimitriosdrakopoulos.projects.auto_track_pro.mapper;

import org.springframework.stereotype.Component;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.ServiceRecordInsertDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.dto.ServiceRecordReadOnlyDTO;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.ServiceRecord;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceRecordMapper {
    
    public ServiceRecordReadOnlyDTO mapServiceRecordReadOnlyDTO(ServiceRecord serviceRecord) {
        var dto = new ServiceRecordReadOnlyDTO();

        dto.setDateOfService(serviceRecord.getDateOfService());
        dto.setServiceType(serviceRecord.getServiceType());
        dto.setDescription(serviceRecord.getDescription());
        dto.setOdometer(serviceRecord.getOdometer());
        dto.setParts(serviceRecord.getParts());
        dto.setCost(serviceRecord.getCost());
        dto.setNextService(serviceRecord.getNextService());
        dto.setRecommentdations(serviceRecord.getRecommentdations());
        dto.setWarranty(serviceRecord.getWarranty());

        return dto;
    }

    public ServiceRecord mapToServiceRecordEntity(ServiceRecordInsertDTO serviceRecordInsertDTO) {
        ServiceRecord serviceRecord = new ServiceRecord();

        serviceRecord.setDateOfService(serviceRecordInsertDTO.getDateOfService());
        serviceRecord.setServiceType(serviceRecordInsertDTO.getServiceType());
        serviceRecord.setDescription(serviceRecordInsertDTO.getDescription());
        serviceRecord.setOdometer(serviceRecordInsertDTO.getOdometer());
        serviceRecord.setParts(serviceRecordInsertDTO.getParts());
        serviceRecord.setCost(serviceRecordInsertDTO.getCost());
        serviceRecord.setNextService(serviceRecordInsertDTO.getNextService());
        serviceRecord.setRecommentdations(serviceRecordInsertDTO.getRecommentdations());
        serviceRecord.setWarranty(serviceRecordInsertDTO.getWarranty());

        return serviceRecord;
    }
}
