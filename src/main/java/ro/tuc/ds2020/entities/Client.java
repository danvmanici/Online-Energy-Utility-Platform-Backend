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
@Table(name="Client")
public class Client implements Serializable{

    private static final long serialVersionUID = 1L;
//
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Device> devices;

    public Client() {
    }

    public Client(String name, String address, Date birthdate) {
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public Client(String name, String address, Date birthdate, Set<Device> devices) {
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
        this.devices = devices;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
