package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.Device;

public class DeviceBuilder {

    private DeviceBuilder() {
    }

    public static DeviceDTO toSmartDeviceDTO(Device smartDevice) {
        return new DeviceDTO(smartDevice.getId(), smartDevice.getDescription(), smartDevice.getAddress(), smartDevice.getMaximum_energy_consumption(), smartDevice.getAverage_energy_consumption(), smartDevice.getClient().getId());
    }


    public static Device toEntity(DeviceDTO smartDeviceDTO) {
        return new Device(smartDeviceDTO.getDescription(),
                smartDeviceDTO.getAddress(),
                smartDeviceDTO.getMaximum_energy_consumption(),
                smartDeviceDTO.getAverage_energy_consumption());
    }
}
