package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class DeviceDTO {

    private UUID id;
    @NotNull
    private String description;
    @NotNull
    private String address;
    @NotNull
    private int maximum_energy_consumption;
    @NotNull
    private int average_energy_consumption;
    @NotNull
    private UUID client_id;

    public DeviceDTO() {
    }

    public DeviceDTO(String description, String address, int maximum_energy_consumption, int average_energy_consumption, UUID client_id) {
        this.description = description;
        this.address = address;
        this.maximum_energy_consumption = maximum_energy_consumption;
        this.average_energy_consumption = average_energy_consumption;
        this.client_id = client_id;
    }

    public DeviceDTO(UUID id, String description, String address, int maximum_energy_consumption, int average_energy_consumption, UUID client_id) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maximum_energy_consumption = maximum_energy_consumption;
        this.average_energy_consumption = average_energy_consumption;
        this.client_id = client_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaximum_energy_consumption() {
        return maximum_energy_consumption;
    }

    public void setMaximum_energy_consumption(int maximum_energy_consumption) {
        this.maximum_energy_consumption = maximum_energy_consumption;
    }

    public int getAverage_energy_consumption() {
        return average_energy_consumption;
    }

    public void setAverage_energy_consumption(int average_energy_consumption) {
        this.average_energy_consumption = average_energy_consumption;
    }

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceDTO)) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return getMaximum_energy_consumption() == deviceDTO.getMaximum_energy_consumption() && getAverage_energy_consumption() == deviceDTO.getAverage_energy_consumption() && Objects.equals(getId(), deviceDTO.getId()) && Objects.equals(getDescription(), deviceDTO.getDescription()) && Objects.equals(getAddress(), deviceDTO.getAddress()) && Objects.equals(getClient_id(), deviceDTO.getClient_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getAddress(), getMaximum_energy_consumption(), getAverage_energy_consumption(), getClient_id());
    }
}
