package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.repositories.ClientRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);
    private final DeviceRepository smartDeviceRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    public DeviceService(DeviceRepository smartDeviceRepository) {
        this.smartDeviceRepository = smartDeviceRepository;
    }

    public List<DeviceDTO> findSmartDevice() {
        List<Device> smartDeviceList = smartDeviceRepository.findAll();
        return smartDeviceList.stream()
                .map(DeviceBuilder::toSmartDeviceDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO findSmartDeviceById(UUID id){
        Optional<Device> prosumerOptional = smartDeviceRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("SmartDevice with id {} was not found in db", id);
            throw new ResourceNotFoundException(Sensor.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toSmartDeviceDTO(prosumerOptional.get());
    }



    public UUID insert(DeviceDTO smartDeviceDTO) {
        Device smartDevice = DeviceBuilder.toEntity(smartDeviceDTO);
        Client client =clientRepository.findById(smartDeviceDTO.getClient_id()).get();
        smartDevice.setClient(client);
        smartDevice = smartDeviceRepository.save(smartDevice);
        LOGGER.debug("SmartDevice with id {} was inserted in db", smartDevice.getId());
        return smartDevice.getId();
    }

    public UUID delete(UUID id) {
        smartDeviceRepository.deleteById(id);
        LOGGER.debug("SmartDevice with id {} was deleted in db", id);
        return id;
    }

    public UUID update(DeviceDTO smartDevice){
        Device oldSmartDevice = smartDeviceRepository.findById(smartDevice.getId()).orElse(null);
        assert oldSmartDevice != null;
        oldSmartDevice.setAddress(smartDevice.getAddress());
        oldSmartDevice.setDescription(smartDevice.getDescription());
        oldSmartDevice.setAverage_energy_consumption(smartDevice.getAverage_energy_consumption());
        oldSmartDevice.setMaximum_energy_consumption(smartDevice.getMaximum_energy_consumption());
        oldSmartDevice=smartDeviceRepository.save(oldSmartDevice);
        return  oldSmartDevice.getId();
    }
}
