package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class SensorDTO {

    private UUID id;
    @NotNull
    private String description;
    @NotNull
    private int max_value;
    @NotNull
    private UUID smartDevice_id;


    public SensorDTO() {
    }

    public SensorDTO(UUID id, String description, int max_value, UUID smartDevice_id) {
        this.id = id;
        this.description = description;
        this.max_value = max_value;
        this.smartDevice_id = smartDevice_id;
    }

    public SensorDTO(String description, int max_value, UUID smartDevice_id) {
        this.description = description;
        this.max_value = max_value;
        this.smartDevice_id = smartDevice_id;
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

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public UUID getSmartDevice_id() {
        return smartDevice_id;
    }

    public void setSmartDevice_id(UUID smartDevice_id) {
        this.smartDevice_id = smartDevice_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorDTO)) return false;
        SensorDTO sensorDTO = (SensorDTO) o;
        return getMax_value() == sensorDTO.getMax_value() && Objects.equals(getId(), sensorDTO.getId()) && Objects.equals(getDescription(), sensorDTO.getDescription()) && Objects.equals(getSmartDevice_id(), sensorDTO.getSmartDevice_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getMax_value(), getSmartDevice_id());
    }
}
