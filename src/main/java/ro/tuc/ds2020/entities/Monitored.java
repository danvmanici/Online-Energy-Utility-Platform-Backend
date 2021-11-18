package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Monitored")
public class Monitored implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "sensor_id", nullable = false)
    private String sensor_id;

    @Column(name = "measurement_value", nullable = false)
    private Double measurement_value;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Device> devices;

    public Monitored() {

    }

    public Monitored(String sensor_id, Double measurement_value, String timestamp) {
        this.sensor_id = sensor_id;
        this.measurement_value = measurement_value;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(Double measurement_value) {
        this.measurement_value = measurement_value;
    }

}
