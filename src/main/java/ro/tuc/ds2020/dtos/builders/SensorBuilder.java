package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.entities.Sensor;

public class SensorBuilder {

    private SensorBuilder() {
    }

    public static SensorDTO toSensorDTO(Sensor sensor) {
        return new SensorDTO(sensor.getId(), sensor.getDescription(), sensor.getMax_value(), sensor.getSmartDevice().getId());
    }


    public static Sensor toEntity(SensorDTO sensorDetailsDTO) {
        return new Sensor(sensorDetailsDTO.getDescription(),
                sensorDetailsDTO.getMax_value());
    }
}
