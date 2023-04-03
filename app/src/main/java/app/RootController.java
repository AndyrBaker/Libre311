package app;

import app.dto.service.ServiceDTO;
import app.dto.service.ServiceList;
import app.dto.servicerequest.PostServiceRequestDTO;
import app.dto.servicerequest.ServiceRequestDTO;
import app.dto.servicerequest.ServiceRequestList;
import app.service.service.ServiceService;
import app.service.servicerequest.ServiceRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.validation.Valid;
import java.util.List;

@Controller("/api")
public class RootController {

    private final ServiceService serviceService;
    private final ServiceRequestService serviceRequestService;

    public RootController(ServiceService serviceService, ServiceRequestService serviceRequestService) {
        this.serviceService = serviceService;
        this.serviceRequestService = serviceRequestService;
    }

    @Get(uris = {"/services", "/services.json"})
    @Produces(MediaType.APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public List<ServiceDTO> indexJson(@Valid Pageable pageable) {
        return serviceService.findAll(pageable);
    }

    @Get("/services.xml")
    @Produces(MediaType.TEXT_XML)
    @ExecuteOn(TaskExecutors.IO)
    public String indexXml(@Valid Pageable pageable) throws JsonProcessingException {
        XmlMapper xmlMapper = XmlMapper.xmlBuilder().defaultUseWrapper(false).build();
        ServiceList serviceList = new ServiceList(serviceService.findAll(pageable));

        return xmlMapper.writeValueAsString(serviceList);
    }

    @Post(uris = {"/requests", "/requests.json"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ExecuteOn(TaskExecutors.IO)
    public ServiceRequestDTO createServiceRequestJson(@Valid @Body PostServiceRequestDTO requestDTO) {
        return serviceRequestService.createServiceRequest(requestDTO);
    }

    @Post("/requests.xml")
    @Produces(MediaType.TEXT_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ExecuteOn(TaskExecutors.IO)
    public String createServiceRequestXml(@Valid @Body PostServiceRequestDTO requestDTO) throws JsonProcessingException {
        XmlMapper xmlMapper = XmlMapper.xmlBuilder().defaultUseWrapper(false).build();
        ServiceRequestList serviceRequestList = new ServiceRequestList(List.of(serviceRequestService.createServiceRequest(requestDTO)));

        return xmlMapper.writeValueAsString(serviceRequestList);
    }
}
