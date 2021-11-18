package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.repositories.SensorRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);
    private final SensorRepository sensorRepository;

    @Autowired
    private DeviceRepository smartDeviceRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<SensorDTO> findSensors() {
        List<Sensor> sensorList = sensorRepository.findAll();
        return sensorList.stream().map(SensorBuilder::toSensorDTO).collect(Collectors.toList());
    }

    public SensorDTO findSensorById(UUID id) {
        Optional<Sensor> prosumerOptional = sensorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Sensor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Sensor.class.getSimpleName() + " with id: " + id);
        }
        return SensorBuilder.toSensorDTO(prosumerOptional.get());
    }

    public int findValueById(UUID id) {
        Sensor prosumerOptional = sensorRepository.findById(id).get();
        return prosumerOptional.getMax_value();
    }

    public UUID insert(SensorDTO sensorDTO) {
        Sensor sensor = SensorBuilder.toEntity(sensorDTO);
        Device smartDevice = smartDeviceRepository.findById(sensorDTO.getSmartDevice_id()).get();
        sensor.setSmartDevice(smartDevice);
        sensor = sensorRepository.save(sensor);
        LOGGER.debug("Sensor with id {} was inserted in db", sensor.getId());
        return sensor.getId();
    }

    public UUID delete(UUID id) {
        sensorRepository.deleteById(id);
        LOGGER.debug("Sensor with id {} was deleted in db", id);
        return id;
    }

    public UUID update(SensorDTO sensor) {
        Sensor oldSensor = sensorRepository.findById(sensor.getId()).orElse(null);
        assert oldSensor != null;
        oldSensor.setMax_value(sensor.getMax_value());
        oldSensor.setDescription(sensor.getDescription());
        oldSensor = sensorRepository.save(oldSensor);
        return oldSensor.getId();
    }

    public SensorDTO findSensorValue(String name) {

        return SensorBuilder.toSensorDTO(sensorRepository.findSensorValue(name));
    }

}
