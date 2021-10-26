package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="Sensor")
public class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "max_value", nullable = false)
    private int max_value;


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name="smartDevice_id")
    private Device smartDevice;

    public Sensor() {
    }

    public Sensor(String description, int max_value, Device smartDevice) {
        this.description = description;
        this.max_value = max_value;
        this.smartDevice = smartDevice;
    }

    public Sensor(String description, int max_value) {
        this.description = description;
        this.max_value = max_value;
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

    public Device getSmartDevice() {
        return smartDevice;
    }

    public void setSmartDevice(Device smartDevice) {
        this.smartDevice = smartDevice;
    }
}
