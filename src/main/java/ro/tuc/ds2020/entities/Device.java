package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="SmartDevice")
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "maximum_energy_consumption", nullable = false)
    private int maximum_energy_consumption;

    @Column(name = "average_energy_consumption", nullable = false)
    private int average_energy_consumption;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smartDevice")
    private Sensor sensor;

    public Device() {
    }

    public Device(String description, String address, int maximum_energy_consumption, int average_energy_consumption, Client client, Sensor sensor) {
        this.description = description;
        this.address = address;
        this.maximum_energy_consumption = maximum_energy_consumption;
        this.average_energy_consumption = average_energy_consumption;
        this.client = client;
        this.sensor = sensor;
    }

    public Device(String description, String address, int maximum_energy_consumption, int average_energy_consumption) {
        this.description = description;
        this.address = address;
        this.maximum_energy_consumption = maximum_energy_consumption;
        this.average_energy_consumption = average_energy_consumption;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
